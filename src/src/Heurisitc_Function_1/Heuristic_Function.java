package Heurisitc_Function_1;

import Estado.Estado;
import IA.Bicing.Estaciones;
import aima.search.framework.HeuristicFunction;

public class Heuristic_Function implements HeuristicFunction {

    public Heuristic_Function() {
    }

    public boolean equals(Object obj) {
        boolean retValue = super.equals(obj);
        return retValue;
    }


    public double getHeuristicValue(Object state) {
        double coste = 0;
        Estado E = (Estado) state;
        for (int i = 0; i < E.getNum_est(); ++i) {
            int n = E.getEstacion(i).getNumBicicletasNext() - E.getEstacion(i).getDemanda();
            if (n < 0)
                coste -= n;
            else
                coste += n;
        }
        return (coste);
    }
}
