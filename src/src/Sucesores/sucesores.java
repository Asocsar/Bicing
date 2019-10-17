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
        int n_int = 2;
        System.out.println(Est.getganancia());
        int n_acc = 2;
        int interval = n_int;
        int veces = n_furgo/interval;
        if (n_furgo%interval != 0) ++veces;
        for (int i = 0; i < veces; ++i) {
            int [] accion = new int [n_int];
            Arrays.fill(accion, 0);
            int suma = 0;
            while (suma <= interval*n_acc) {
                boolean [] visited = new boolean[n_furgo];
                Arrays.fill(visited, false);
                suma = 0;
                Estado next_Est = Est.clonar();
                double ganancia = next_Est.getganancia();
                double aux = 0;
                for (int j = i*interval; j < interval*(i+1) && j < n_furgo; ++j) {
                    if (accion[j%n_int] == 0) {
                        ganancia += next_Est.Coger(j,visited);

                    }
                    else if (accion[j%n_int] == 1) {
                        ganancia += next_Est.Dejar(j);
                    }
                    else if (accion[j%n_int] == 2 ) {
                        ganancia += next_Est.Dejar(j);
                        ganancia += next_Est.Dejar(j);
                    }
                }
                next_Est.setganancia(ganancia);
                retVal.add(new Successor("Beneficio de " + next_Est.getganancia(), next_Est));
                int k = 0;
                accion[k] += 1;
                suma += accion[k];
                while(k < accion.length) {
                    if (accion[k] >= n_acc+1) {
                       // System.out.println("HE ENTRADO");
                        accion[k] = 0;
                        if (k+1 < accion.length) accion[k+1] += 1;
                    }
                    suma = suma + accion[k];
                    k++;
                }

            }

        }
        return retVal;
    }
}

