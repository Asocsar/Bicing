package Estado;

import IA.Bicing.Estaciones;
import Van.Van;

import java.util.ArrayList;

public class Estado {

    private Estaciones Est;
    private ArrayList<Van> V = new ArrayList<Van>();
    private int cdesp;

    public Estado (int num_est, int nbicis, int nfurgo, int demanda, int seed) {
        this.Est = new Estaciones(num_est, nbicis, demanda, seed);
        this.cdesp = 0;



    }
}
