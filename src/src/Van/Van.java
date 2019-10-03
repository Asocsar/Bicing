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

    public double move (int x, int y) {
        double cost = Math.abs(this.CordX-x) + Math.abs(this.CordY-y);
        this.CordX = x;
        this.CordY = y;
        return  cost;
    }

    public void pickUp(int number) {

    }

    public void leave(int number) {
        visited += 1;
        carga -= number;
    }



}


