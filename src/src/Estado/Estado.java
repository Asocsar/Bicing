package Estado;

import IA.Bicing.Estaciones;
import IA.Bicing.Estacion;
import Van.Van;

import java.util.ArrayList;

public class Estado {

    private Estaciones Est;
    private int num_est;
    private Van [] Furgonetas;
    private double cdesp;
    private static int n_furgo = 30;

    public Estado (int num_est, int nbicis, int nfurgo, int demanda, int seed) {
        this.Est = new Estaciones(num_est, nbicis, demanda, seed);
        this.cdesp = 0;
        this.num_est = num_est;
        this.Furgonetas = new Van[n_furgo];
        int j_fur = 0;
        int i_est = 0;
        while (j_fur < n_furgo && i_est < this.num_est) {
            Estacion E = this.Est.get(i_est);
            int x = E.getCoordX();
            int y = E.getCoordY();
            this.Furgonetas[j_fur] = new Van(x,y);
            ++i_est;
            ++j_fur;
        }

        while (j_fur < n_furgo) {
            i_est = i_est % this.num_est;
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
        for (int i = 0; i < num_est; ++i) {
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

    public int getNum_est () {return num_est;}

    public int getN_furgo () {return n_furgo;}

    public Van getIFurgo (int i_furgo) {return this.Furgonetas[i_furgo];}


}
