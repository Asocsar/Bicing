package Heurisitc_Function_1;
import Estado.Estado_g;
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
        Estado_g E = (Estado_g) state;
        for (int i = 0; i < E.getNum_est(); ++i) {
            int n = E.getEstacion(i).getNumBicicletasNext() - E.getEstacion(i).getDemanda();
            int ncog = E.getEstacion(i).getNumBicicletasNoUsadas();
            if (n < 0)
                coste -= n*0.3;

            coste += ncog*0.2;
        }
        if (E.getganancia() != 0) coste -=  E.getganancia()*2;

        return (coste);
    }
}
