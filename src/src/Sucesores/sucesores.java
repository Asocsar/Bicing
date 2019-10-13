package Sucesores;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import Estado.Estado;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sucesores implements SuccessorFunction{

    public List getSuccessors(Object state){
        Estado Est = (Estado) state;
        ArrayList retVal = new ArrayList();
        int n_furgo = Est.getN_furgo();
        int n_est = Est.getNum_est();
        boolean [] visited = new boolean[n_est];
        Arrays.fill(visited, false);
        for (int i = 0; i < n_furgo; ++i) {
            //Estado nex_Est =
            if (Est.getIFurgo(i).carga() == 0) {
                int i_est = Est.Coger(i, visited);
                visited[i] = true;
                retVal.add(new Successor("Furgoneta " + i + " coger bicis", Est));
            }
            else {
                for (int j = 0; j < n_est; ++j) {
                    double coste = Est.Dejar(j,i);
                    double gan = Est.getganancia();
                    gan += coste;
                    Est.setganancia(gan);
                    if (gan >= 0) retVal.add(new Successor("Furgoneta " + i + " a estacion " + j, Est));
                    for (int j_aux = 0; j_aux < n_est; ++j_aux) {
                        if (j_aux != j) {
                            gan += Est.Dejar(j_aux,i);
                            Est.setganancia(gan);
                            if (gan >= 0) retVal.add(new Successor("Furgoneta " + i + " a estacion " + j, Est));
                        }
                    }
                }
            }
        }
        return retVal;
    }
}
