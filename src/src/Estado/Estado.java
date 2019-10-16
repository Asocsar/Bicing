package Estado;

import IA.Bicing.Estaciones;
import IA.Bicing.Estacion;
import Van.Van;
import aima.util.Pair;

import java.awt.color.ICC_Profile;
import java.util.ArrayList;
import java.util.Arrays;

public class Estado {

    private Estaciones Est;
    private static int num_ests;
    private static int nbiciss;
    private static int demandas;
    private static int seeds;
    private double cdesp;
    private static int n_furgo;
    boolean [] visited;
    //private double demanda_total;
    private Van [] Furgonetas = new Van [n_furgo];

    public Estado (int num_est, int nbicis, int nfurgo, int demanda, int seed) {
        this.Est = new Estaciones(num_est, nbicis, demanda, seed);
        this.cdesp = 0;
        Arrays.fill(this.visited, false);
        num_ests = num_est;
        n_furgo = nfurgo;
        nbiciss = nbicis;
        demandas = demanda;
        seeds = seed;
        //this.demanda_total = 0;
        int j_fur = 0;
        int i_est = 0;
        while (j_fur < n_furgo && i_est < num_ests) {
            Estacion E = this.Est.get(i_est);
            int x = E.getCoordX();
            int y = E.getCoordY();
            int needed = E.getDemanda() - E.getNumBicicletasNext();
            //if (needed > 0) this.demanda_total += needed;
            this.Furgonetas[j_fur] = new Van(x,y);
            ++i_est;
            ++j_fur;
        }

        while (j_fur < n_furgo) {
            i_est = i_est % num_est;
            Estacion E = this.Est.get(i_est);
            int x = E.getCoordX();
            int y = E.getCoordY();
            this.Furgonetas[j_fur] = new Van(x,y);
            ++i_est;
            ++j_fur;
        }
    }

    public double Coger (int i_furgo, boolean[] visited) {
        double cost = 20000;
        int x_van = Furgonetas[i_furgo].getCordX();
        int y_van = Furgonetas[i_furgo].getCordY();
        int max_get = 0;
        int i_est = -1;
        for (int i = 0; i < num_ests; ++i) {
            int cant = Est.get(i).getNumBicicletasNext() - Est.get(i).getDemanda();
            if (cant > max_get && !visited[i]) {max_get = cant; i_est = i;}
        }
        double coste_mov = this.Furgonetas[i_furgo].move(Est.get(i_est).getCoordX(), Est.get(i_est).getCoordY());
        int rest = this.Furgonetas[i_est].carga_max() - this.Furgonetas[i_est].carga();
        if (rest > max_get) rest = max_get;
        if (Est.get(i_est).getNumBicicletasNoUsadas() < rest)
            rest = Est.get(i_est).getNumBicicletasNoUsadas();
        Furgonetas[i_furgo].pickUp(rest, Est.get(i_est));
        visited[i_est] = true;
        return coste_mov;
    }


    public double Dejar (int i_est, int i_furgo) {
        double coste_max = 0;
        double max_gan = 0;
        for (int j = 0; j < num_ests; ++j) {
            Estacion E = (Estacion) this.Est.get(j);
            double coste = Furgonetas[i_furgo].move(E.getCoordX(), E.getCoordY());
            int Dem = E.getDemanda();
            int Curr = E.getNumBicicletasNext();
            int dif = Dem - Curr;
            double ganancia;
            if (dif < Furgonetas[i_furgo].carga()) {
                Furgonetas[i_furgo].leave(dif, E);
                ganancia = (double) dif;
            } else {
                Furgonetas[i_furgo].leave(Furgonetas[i_furgo].carga(), E);
                ganancia = (double) Furgonetas[i_furgo].carga();
            }
            if (coste_max + max_gan < coste + ganancia) {}
        }
        return coste_max + max_gan;
    }


   // public double getDemanda_total () {return this.demanda_total;}

    public void setganancia (double gan) {
        this.cdesp = gan;
    }

    public double getganancia () {return this.cdesp;}

    public int getNum_est () {return num_ests;}

    public void setNum_est (int n) {num_ests = n;}

    public int getN_furgo () {return n_furgo;}

    public void setFurgo (Van V, int i) {this.Furgonetas[i] = V; }

    public boolean getVisited (int i) {return this.visited[i];}

    public void setVisited (boolean value, int i, int tots) {
        if (tots == 1) {
            for (int j = 0; j < this.visited.length; ++i) visited[j] = value;
        }
    }

    public Van getIFurgo (int i_furgo) {return this.Furgonetas[i_furgo];}

    public void setEstaciones (Estaciones E) { this.Est = E;}

    public Estado clonar () {
        Estado E = new Estado(num_ests, nbiciss, n_furgo, demandas, seeds);
        E.setganancia(this.cdesp);
        Van [] F = new Van[n_furgo];
        for (int i = 0; i < n_furgo; ++i) {
            Van V = new Van(getIFurgo(i).getCordX(), getIFurgo(i).getCordY());
            V.setCharge(getIFurgo(i).carga());
            V.setLong_t(getIFurgo(i).getLong_t());
            F[i] = V;
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
