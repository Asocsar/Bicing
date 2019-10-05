package Van;
import IA.Bicing.Estacion;

public class Van {
    private static int cap_max = 30;
    private int CordX;
    private int CordY;
    private double long_t;
    private int charge;
    private int visited;

    public Van (int x, int y) {
        this.CordX = x*100;
        this.CordY = y*100;
        this.visited = 0;
        this.charge = 0;
        this.long_t = 0;

    }

    public double move (int x, int y) {
        double cost = Math.abs(this.CordX-x) + Math.abs(this.CordY-y);
        this.CordX = x;
        this.CordY = y;
        return cost;
    }

    public void pickUp(int number, Estacion e) {
        if (this.charge + number > cap_max)  throw new IllegalStateException("The Van has exceed its maximum charge");
        this.charge += number;
        e.setNumBicicletasNoUsadas(e.getNumBicicletasNoUsadas()-number);
        e.setNumBicicletasNext(e.getNumBicicletasNext()-number);
    }

    public void leave(int number, Estacion e) {
        if (this.charge < number)  throw new IllegalStateException("The Van doesn't have this number");
        if (this.visited >= 2) throw new IllegalStateException("The Van has exceed the number of visited stations in 1 hour");
        this.charge -= number;
        this.visited += 1;
        e.setNumBicicletasNext(e.getNumBicicletasNext()+number);
        e.setNumBicicletasNoUsadas(e.getNumBicicletasNoUsadas()+number);
    }

    public void reset () {
        this.visited = 0;
    }



}


