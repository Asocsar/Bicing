package Van;

import aima.util.Pair;

public class Van {
    private static int cap_max = 30;
    private int CordX;
    private int CordY;
    private double long_t;
    private int carga;
    private int visited;

    public Van (int x, int y) {
        this.CordX = x*100;
        this.CordY = y*100;
        this.visited = 0;
        this.carga = 0;
        this.long_t = 0;

    }

    public int move (int x, int y) {

    }

}


