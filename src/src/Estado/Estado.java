package Estado;

import IA.Bicing.Estaciones;
import IA.Bicing.Estacion;
import Van.Van;
import aima.util.Pair;


import java.util.Arrays;
import java.util.Random;

public class Estado {

    private Estaciones Est;
    private static int num_ests;
    private static int nbiciss;
    private static int demandas;
    private static int seeds;
    private double cdesp;
    private static int n_furgo;
    private boolean [] visited;
    private Van [] Furgonetas;
    private int caso;

    public Estado () {

    }

    public Estado (int num_est, int nbicis, int nfurgo, int demanda, int seed, int cas) {
        this.caso = cas;
        if (cas == 0) generate_o(num_est, nbicis, nfurgo, demanda, seed);
        else if (cas == 1) generate_g(num_est,nbicis,nfurgo,demanda,seed);
        else if (cas == 2) generate_r(num_est,nbicis,nfurgo,demanda,seed);
    }

    private void generate_r (int num_est, int nbicis, int nfurgo, int demanda, int seed) {
        this.Est = new Estaciones(num_est, nbicis, demanda, seed);
        this.cdesp = 0;
        this.visited = new boolean[num_est];
        Arrays.fill(this.visited, false);
        num_ests = num_est;
        n_furgo = nfurgo;
        nbiciss = nbicis;
        demandas = demanda;
        seeds = seed;
        this.Furgonetas = new Van [n_furgo];
        int j_fur = 0;
        Random R = new Random();
        int parte = 1;
        while (j_fur < n_furgo) {
            int i_est = R.nextInt(num_est);
            Estacion E = this.Est.get(i_est);
            int x = E.getCoordX();
            int y = E.getCoordY();
            this.Furgonetas[j_fur] = new Van(x,y);
            int needed =  E.getNumBicicletasNext() - E.getDemanda();
            needed = Math.min(needed, E.getNumBicicletasNoUsadas()/parte);
            needed = Math.min(needed, (this.Furgonetas[j_fur].carga_max() - this.Furgonetas[j_fur].carga()));
            if (needed > 0 ) this.Furgonetas[j_fur].pickUp(needed,this.Est, i_est);
            ++j_fur;
        }
    }

    private void generate_g (int num_est, int nbicis, int nfurgo, int demanda, int seed) {
        this.Est = new Estaciones(num_est, nbicis, demanda, seed);
        this.cdesp = 0;
        this.visited = new boolean[num_est];
        Arrays.fill(this.visited, false);
        num_ests = num_est;
        n_furgo = nfurgo;
        nbiciss = nbicis;
        demandas = demanda;
        seeds = seed;
        this.Furgonetas = new Van [n_furgo];
        int parte = 1;
        int max = 0;
        Pair[] X = new Pair [num_est];
        Pair [] F = new Pair [num_est];

        for (int i = 0; i < num_est; ++i) {
            int needed =  Est.get(i).getNumBicicletasNext() - Est.get(i).getDemanda();
            needed = Math.min(needed, Est.get(i).getNumBicicletasNoUsadas());
            if (needed > 0) X[i] = new Pair(i, needed);
            if (needed > max) max = needed;
        }

        int [] L = new int [max+1];
        Arrays.fill(L, 0);

        for (int i = 0; i < num_est; ++i) {
            if (X[i] != null) L[X[i].getSecond()] += 1;
        }

        for (int i = 1; i <= max; ++i) {
            L[i] = L[i] + L[i-1];
        }

        for (int i = num_est-1; i >= 0; --i) {
            if (X[i] != null) {
                F[L[X[i].getSecond()]-1] = new Pair(X[i].getFirst(), X[i].getSecond());
                L[X[i].getSecond()] -= 1;
            }
        }

        int j_fur = 0;
        int i_sig = num_est-1;
        while (j_fur < n_furgo && i_sig >= 0) {
            if (F[i_sig] != null) {
                int i_est = F[i_sig].getFirst();
                int cant = F[i_sig].getSecond();
                Estacion E = this.Est.get(i_est);
                int x = E.getCoordX();
                int y = E.getCoordY();
                this.Furgonetas[j_fur] = new Van(x, y);
                int needed = Math.min(cant, (this.Furgonetas[j_fur].carga_max() - this.Furgonetas[j_fur].carga()));
                if (needed > 0) this.Furgonetas[j_fur].pickUp(needed, this.Est, i_est);
                ++j_fur;
            }
            --i_sig;
        }

        while (j_fur < n_furgo) {
            if (i_sig < 0) i_sig = num_est - 1;
            if (F[i_sig] != null) {
                int i_est = F[i_sig].getFirst();
                int cant = F[i_sig].getSecond();
                Estacion E = this.Est.get(i_est);
                int x = E.getCoordX();
                int y = E.getCoordY();
                this.Furgonetas[j_fur] = new Van(x, y);
                int needed = Math.min(cant, (this.Furgonetas[j_fur].carga_max() - this.Furgonetas[j_fur].carga()));
                if (needed > 0) this.Furgonetas[j_fur].pickUp(needed, this.Est, i_est);
                ++j_fur;
            }
            --i_sig;
        }
    }

    private void generate_o (int num_est, int nbicis, int nfurgo, int demanda, int seed) {
        this.Est = new Estaciones(num_est, nbicis, demanda, seed);
        this.cdesp = 0;
        this.visited = new boolean[num_est];
        Arrays.fill(this.visited, false);
        num_ests = num_est;
        n_furgo = nfurgo;
        nbiciss = nbicis;
        demandas = demanda;
        seeds = seed;
        this.Furgonetas = new Van [n_furgo];
        int j_fur = 0;
        int i_est = 0;
        int parte = 1;
        while (j_fur < n_furgo && i_est < num_ests) {
            Estacion E = this.Est.get(i_est);
            int x = E.getCoordX();
            int y = E.getCoordY();
            this.Furgonetas[j_fur] = new Van(x,y);
            int needed =  E.getNumBicicletasNext() - E.getDemanda();
            needed = Math.min(needed, E.getNumBicicletasNoUsadas()/parte);
            needed = Math.min(needed, (this.Furgonetas[j_fur].carga_max() - this.Furgonetas[j_fur].carga()));
            if (needed > 0 ) this.Furgonetas[j_fur].pickUp(needed,this.Est, i_est);
            ++i_est;
            ++j_fur;
        }

        while (j_fur < n_furgo) {
            i_est = i_est % num_est;
            Estacion E = this.Est.get(i_est);
            int x = E.getCoordX();
            int y = E.getCoordY();
            this.Furgonetas[j_fur] = new Van(x,y);
            int needed =  E.getNumBicicletasNext() - E.getDemanda();
            needed = Math.min(needed, E.getNumBicicletasNoUsadas()/parte);
            needed = Math.min(needed, (this.Furgonetas[j_fur].carga_max() - this.Furgonetas[j_fur].carga()));
            if (needed > 0) this.Furgonetas[j_fur].pickUp(needed,this.Est, i_est);
            ++i_est;
            ++j_fur;
        }
    }

    /*public double Coger (int i_furgo) {
        double cost = 20000;
        int x_van = Furgonetas[i_furgo].getCordX();
        int y_van = Furgonetas[i_furgo].getCordY();
        int max_get = 0;
        int i_est = -1;
        for (int i = 0; i < num_ests; ++i) {
            int cant = Est.get(i).getNumBicicletasNext() - Est.get(i).getDemanda();
            cant = Math.min(Est.get(i).getNumBicicletasNoUsadas(), cant) ;
            cant = Math.min(cant, Furgonetas[i_furgo].carga_max() - Furgonetas[i_furgo].carga());
            if (cant > max_get && !this.visited[i]) {max_get = cant; i_est = i;}
        }
        if (i_est == -1) return 0;
        double coste_mov = this.Furgonetas[i_furgo].move(Est.get(i_est).getCoordX(), Est.get(i_est).getCoordY());
        Furgonetas[i_furgo].pickUp(max_get, Est, i_est);
        this.visited[i_est] = true;
        return coste_mov;
    }*/


    public double Dejar (int i_furgo) {
        int max_ben = 0;
        int i_est = 0;
        Van V = Furgonetas[i_furgo];
        for (int i = 0; i < Est.size(); ++i) {
            Estacion E = Est.get(i);
            int cost = ((V.carga()*9)/10)*((Math.abs(V.getCordX()-E.getCoordX()) + Math.abs(V.getCordY()-E.getCoordY()))/1000);
            int Dif = E.getDemanda() - E.getNumBicicletasNext();
            int ganancia = 0;
            if (Dif > V.carga()) ganancia =  V.carga();
            else ganancia = Dif;
            int total =  ganancia - cost;
            if (total > max_ben) {max_ben = total; i_est = i;}
        }
        max_ben = Math.min(max_ben, V.carga());
        double cost = Furgonetas[i_furgo].move(Est.get(i_est).getCoordX(), Est.get(i_est).getCoordY());
        Furgonetas[i_furgo].leave(max_ben, Est, i_est);
        return max_ben - cost;
    }



    public void setganancia (double gan) { this.cdesp = gan; }

    public double getganancia () {return this.cdesp;}

    public int getNum_est () {return num_ests;}

    private void setNum_est (int n, boolean [] v) {
        num_ests = n;
        this.visited = new boolean[num_ests];
        for (int i = 0; i < v.length; ++i) {
            boolean a = v[i];
            this.visited[i] = a;
        }
    }

    public int getN_furgo () {return n_furgo;}

    public void setFurgo (Van V, int i) {this.Furgonetas[i] = V; }

    public Van getIFurgo (int i_furgo) {return this.Furgonetas[i_furgo];}

    public void setEstaciones (Estaciones E) { this.Est = E;}

    private Estaciones getEst () {return this.Est;}

    public Estacion getEstacion (int i) { return this.Est.get(i); }

    private Van [] getFurgonetas () {return this.Furgonetas;}

    private void setNbiciss(int n) {nbiciss = n;}

    private void setN_furgo(int n) {
        n_furgo = n;
        this.Furgonetas = new Van[n_furgo];
    }

    private void setDemandas(int n) {demandas = n;}

    private void setSeeds (int n) {seeds = n;}

    public Estaciones getEstaciones () {return this.Est;}

    public Estado clonar ()  {
        Estado E = new Estado();
        E.setNum_est(num_ests, this.visited);
        E.setNbiciss(nbiciss);
        E.setN_furgo(n_furgo);
        E.setDemandas(demandas);
        E.setSeeds(seeds);
        E.setganancia(this.cdesp);
        Estaciones aux = new Estaciones(num_ests, nbiciss, demandas, seeds);
        for (int i = 0; i < n_furgo || i < num_ests; ++i) {
            if (i < n_furgo)
                E.setFurgo((Van) this.Furgonetas[i].clone(), i);
            else if (i < num_ests) {
                aux.get(i).setNumBicicletasNoUsadas(this.Est.get(i).getNumBicicletasNoUsadas());
                aux.get(i).setNumBicicletasNext(this.Est.get(i).getNumBicicletasNext());
                aux.get(i).setDemanda(this.Est.get(i).getDemanda());
            }
        }
        E.setEstaciones(aux);
        return E;
    }
}
