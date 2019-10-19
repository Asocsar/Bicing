package Estado;

import IA.Bicing.Estaciones;
import IA.Bicing.Estacion;
import Van.Van;


import java.util.Arrays;

public class Estado {

    private Estaciones Est;
    private static int num_ests;
    private static int nbiciss;
    private static int demandas;
    private static int seeds;
    private double cdesp;
    private static int n_furgo;
    private boolean [] action;
    private int [] quedan;
    private boolean [] visited;
    private Van [] Furgonetas;

    public Estado (int num_est, int nbicis, int nfurgo, int demanda, int seed) {
        this.Est = new Estaciones(num_est, nbicis, demanda, seed);
        this.cdesp = 0;
        this.visited = new boolean[num_est];
        this.action = new boolean[nfurgo];
        this.quedan = new int[nfurgo];
        Arrays.fill(this.action, true);
        Arrays.fill(this.visited, false);
        Arrays.fill(quedan, 2);
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

    public double Coger (int i_furgo) {
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
    }


    public int Dejar (int i_furgo) {
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
        int cost = Furgonetas[i_furgo].move(Est.get(i_est).getCoordX(), Est.get(i_est).getCoordY());
        Furgonetas[i_furgo].leave(max_ben, Est, i_est);
        return max_ben - cost;
    }



    public void setganancia (double gan) {
        this.cdesp = gan;
    }

    public double getganancia () {return this.cdesp;}

    public int getNum_est () {return num_ests;}

    public void restAction (int j, int n) {
        this.quedan[j] = this.quedan[j] - n;
        if (this.quedan[j] == 0) this.action[j] = false;
    }

    public boolean [] getAllAction () {return this.action;}

    public void setAction (boolean [] a) {
        for (int i = 0; i < a.length; ++i) {
            boolean aux = a[i];
            this.action[i] = aux;
        }
    }

    public int getrest (int j) {return this.quedan[j];}

    public boolean getaction (int j) {return this.action[j];}

    public void setNum_est (int n) {num_ests = n;}

    public int getN_furgo () {return n_furgo;}

    public void setFurgo (Van V, int i) {this.Furgonetas[i] = V; }

    public Van getIFurgo (int i_furgo) {return this.Furgonetas[i_furgo];}

    public void setEstaciones (Estaciones E) { this.Est = E;}

    public void setQuedan (int[] q) {
        for (int i = 0; i < q.length; ++i) {
            int aux = q[i];
            this.quedan[i] = aux;
        }
    }

    public int [] getQuedan () {return this.quedan;}

    public Estacion getEstacion (int i) { return this.Est.get(i); }

    public Estaciones getEstaciones () {return this.Est;}

    public Estado clonar () {
        Estado E = new Estado(num_ests, nbiciss, n_furgo, demandas, seeds);
        E.setQuedan(this.quedan);
        E.setAction(this.action);
        E.setganancia(this.cdesp);
        for (int i = 0; i < n_furgo; ++i) {
            Van V = new Van(getIFurgo(i).getCordX(), getIFurgo(i).getCordY());
            V.setCharge(getIFurgo(i).carga());
            V.setLong_t(getIFurgo(i).getLong_t());
            E.setFurgo(V, i);
        }


        Estaciones aux = new Estaciones (num_ests, nbiciss, demandas, seeds);
        for (int i = 0; i < num_ests; ++i) {
            aux.get(i).setNumBicicletasNoUsadas(Est.get(i).getNumBicicletasNoUsadas());
            aux.get(i).setNumBicicletasNext(Est.get(i).getNumBicicletasNext());
            aux.get(i).setDemanda(Est.get(i).getDemanda());
        }

        E.setEstaciones(aux);
        return E;
    }


}
