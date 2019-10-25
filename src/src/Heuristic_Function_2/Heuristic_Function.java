package Heuristic_Function_2;

import Estado.Estado;
import IA.Bicing.Estacion;
import Van.Van;
import aima.search.framework.HeuristicFunction;


public class Heuristic_Function implements HeuristicFunction {

    public Heuristic_Function() {
    }

    public boolean equals(Object obj) {
        boolean retValue = super.equals(obj);
        return retValue;
    }

    public double getHeuristicValue(Object state) {
        Estado E = (Estado) state;
        double heuristic_value = 0;
        for (int i = 0; i < E.getN_furgo(); ++i) {
            heuristic_value += E.getIFurgo(i).getLast_cost();
        }
        heuristic_value -= E.getganancia();
        return heuristic_value;
    }
}
