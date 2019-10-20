package Heuristic_Function_2;

import Estado.Estado;
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
        return 0;
    }
}
