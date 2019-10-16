package Sucesores;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import Estado.Estado;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sucesores implements SuccessorFunction {

    public List getSuccessors(Object state) {
        Estado Est = (Estado) state;
        ArrayList retVal = new ArrayList();
        int n_furgo = Est.getN_furgo();
        int n_est = Est.getNum_est();
        int interval = 5;
        int veces = n_furgo/interval;
        if (n_furgo%interval != 0) ++veces;
        for (int i = 0; i < veces; ++i) {
            int [] accion = new int [5];
            Arrays.fill(accion, 0);
            int suma = 0;
            while (suma <= interval*3) {
                suma = 0;
                Estado next_Est = Est.clonar();
                double ganancia = next_Est.getganancia();
                boolean [] visited = new boolean[n_est];
                Arrays.fill(visited, false);
                for (int j = i*interval; j < interval*(i+1) && j < n_furgo; ++j) {
                    if (accion[j%5] == 0) ganancia += next_Est.Coger(j,visited);
                    else if (accion[j%5] == 1) ganancia += next_Est.Dejar(j);
                    else if (accion[j%5] == 2) {
                        ganancia += next_Est.Dejar(j);
                        ganancia += next_Est.Dejar(j);
                    }
                }
                next_Est.setganancia(ganancia);
                retVal.add(new Successor("Beneficio de " + ganancia, next_Est));
                int k = 0;
                accion[k] += 1;
                suma += accion[k];++k;
                while(k < accion.length) {
                    if (accion[k] == 4) {
                        accion[k] = 0;
                        accion[k+1] += 1;
                    }
                    k = k+1;
                    suma = suma + accion[k];
                }

            }

        }
        return retVal;
    }
}

