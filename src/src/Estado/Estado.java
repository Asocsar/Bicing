package Estado;

import IA.Bicing.Estaciones;
import IA.Bicing.Estacion;
import Van.Van;

import java.awt.color.ICC_Profile;
import java.util.ArrayList;

public class Estado {

    private Estaciones Est;
    private static int num_ests;
    private static int nbiciss;
    private static int demandas;
    private static int seeds;
    private double cdesp;
    private static int n_furgo;
    private Van [] Furgonetas = new Van [n_furgo];

    public Estado (int num_est, int nbicis, int nfurgo, int demanda, int seed) {
        this.Est = new Estaciones(num_est, nbicis, demanda, seed);
        this.cdesp = 0;
        num_ests = num_est;
        n_furgo = nfurgo;
        nbiciss = nbicis;
        demandas = demanda;
        seeds = seed;
        int j_fur = 0;
        int i_est = 0;
        while (j_fur < n_furgo && i_est < num_ests) {
            Estacion E = this.Est.get(i_est);
            int x = E.getCoordX();
            int y = E.getCoordY();
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

    public int Coger (int i_furgo, boolean[] visited) {
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
        return i_est;
    }


    public double Dejar (int i_est, int i_furgo) {
        double coste = 0;
        Estacion E = (Estacion)this.Est.get(i_est);
        coste += Furgonetas[i_furgo].move(E.getCoordX(), E.getCoordY());
        int Dem = E.getDemanda();
        int Curr = E.getNumBicicletasNext();
        int dif = Dem - Curr;
        double ganancia;
        if (dif < Furgonetas[i_furgo].carga()) {
            Furgonetas[i_furgo].leave(dif,E);
            ganancia = (double) dif;
        }
        else {
            Furgonetas[i_furgo].leave(Furgonetas[i_furgo].carga(), E);
            ganancia = (double) Furgonetas[i_furgo].carga();
        }
        return coste + ganancia;
    }



    public void setganancia (double gan) {
        this.cdesp = gan;
    }

    public double getganancia () {return this.cdesp;}

    public int getNum_est () {return num_ests;}

    public void setNum_est (int n) {num_ests = n;}

    public int getN_furgo () {return n_furgo;}

    public void setFurgo (Van V, int i) {this.Furgonetas[i] = V; }

    public Van getIFurgo (int i_furgo) {return this.Furgonetas[i_furgo];}

    public void setEstaciones (Estaciones E) { this.Est = E;}

    public Estado clonar () throws CloneNotSupportedException {
        Estado E = new Estado(num_ests, nbiciss, n_furgo, demandas, seeds);
        E.setganancia(this.cdesp);
        for (int i = 0; i < n_furgo; ++i) {
            E.setFurgo((Van) this.Furgonetas[i].clone(), i);
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
