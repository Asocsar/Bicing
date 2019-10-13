package Van;
import IA.Bicing.Estacion;

public class Van {
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

    public double move (int x, int y) {
        double cost = Math.abs(this.CordX-x) + Math.abs(this.CordY-y);
        this.CordX = x;
        this.CordY = y;
        return (cost/1000)*((this.charge+9)/10);
    }

    public void pickUp(int number, Estacion e) {
        this.charge += number;
        e.setNumBicicletasNoUsadas(e.getNumBicicletasNoUsadas()-number);
        e.setNumBicicletasNext(e.getNumBicicletasNext()-number);
    }

    public void leave(int number, Estacion e) {
        this.charge -= number;
        e.setNumBicicletasNext(e.getNumBicicletasNext()+number);
        e.setNumBicicletasNoUsadas(e.getNumBicicletasNoUsadas()+number);
    }



    public int carga() { return this.charge; }

    public int carga_max () { return cap_max; }

    public int getCordX () { return this.CordX; }

    public int getCordY () { return this.CordY; }


}
