package Implemented;

import IA.Bicing.Estacion;

public class Estacion_clonable extends Estacion {

    public Estacion_clonable(int cx, int cy) {
        super(cx, cy);
    }

    protected Object clone () throws CloneNotSupportedException
    {
        return super.clone();
    }
}
