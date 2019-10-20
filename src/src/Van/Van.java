package Van;
import IA.Bicing.Estaciones;

public class Van  {
    private static int cap_max = 30;
    private int CordX;
    private int CordY;
    private double long_t;
    private int charge;

    public Van (int x, int y) {
        this.CordX = x;
        this.CordY = y;
        this.charge = 0;
        this.long_t = 0;

    }

    public int move (int x, int y) {
        int cost = Math.abs(this.CordX-x) + Math.abs(this.CordY-y);
        this.long_t += cost;
        this.CordX = x;
        this.CordY = y;
        return (cost/1000)*((this.charge+9)/10);
    }

    public void pickUp(int number, Estaciones e, int i) {
        this.charge = this.charge + number;
        e.get(i).setNumBicicletasNoUsadas(e.get(i).getNumBicicletasNoUsadas()-number);
        e.get(i).setNumBicicletasNext(e.get(i).getNumBicicletasNext()-number);
    }

    public void leave(int number, Estaciones e, int i) {
        this.charge = this.charge - number;
        e.get(i).setNumBicicletasNext(e.get(i).getNumBicicletasNext()+number);
        e.get(i).setNumBicicletasNoUsadas(e.get(i).getNumBicicletasNoUsadas()+number);
    }

    public void setCharge (int n) {this.charge = n;}

    public void setLong_t (double d) {this.long_t = d;}

    public double getLong_t () {return this.long_t;}

    public int carga() { return this.charge; }

    public int carga_max () { return cap_max; }

    public int getCordX () { return this.CordX; }

    public int getCordY () { return this.CordY; }



}
