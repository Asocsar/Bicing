package sucesoresA;
import Estado.Estado;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class sucesoresA implements SuccessorFunction {

    public List getSuccessors(Object state) {
        Estado Est = (Estado) state;
        ArrayList retVal = new ArrayList();
        int n_furgo = Est.getN_furgo();
        int n_est = Est.getNum_est();
        Random myRandom = new Random();
        Estado next_Est = Est.clonar();
        double gan = Est.getganancia();
        double ingres = 0;
        for (int i = 0; i < n_furgo; ++i) {
            int decision = myRandom.nextInt(4);
            if (decision == 0 ) {
                ingres -= next_Est.Coger(i);
                next_Est.restAction(i, 2);
            } else if (decision == 1 ) {
                ingres += next_Est.Dejar(i);
                next_Est.restAction(i, 2);
            } else if (decision == 2 ) {
                ingres += next_Est.Dejar(i);
                ingres += next_Est.Dejar(i);
                next_Est.restAction(i, 2);
            }
        }
        next_Est.setganancia(gan+ingres);
        if (next_Est.getganancia() >= 0) retVal.add(new Successor("Beneficio de " + next_Est.getganancia(), next_Est));
        else retVal.add(new Successor("Beneficio de " + Est.getganancia(), Est));
        return retVal;
    }
}
