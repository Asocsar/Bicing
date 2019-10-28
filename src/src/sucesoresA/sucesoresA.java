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
        System.out.println(Est.getganancia());
        ArrayList retVal = new ArrayList();
        int n_furgo = Est.getN_furgo();
        int n_est = Est.getNum_est();
        Random myRandom = new Random();
        Estado next_Est = Est.clonar();
        double gan = Est.getganancia();
        double ingres = 0;
        int i = myRandom.nextInt(n_furgo);
        int sig = myRandom.nextInt(n_est);
        ingres += next_Est.Dejar(i, sig);
        double ben = gan + ingres;
        next_Est.setganancia(ben);
        next_Est.addList_cdesp(ben);
        Est.addList_cdesp(Est.getganancia());
        if (ben >= 0) retVal.add(new Successor("Beneficio de " + next_Est.getganancia(), next_Est));
        else {
            retVal.add(new Successor("Beneficio de " + Est.getganancia(), Est));
        }
        return retVal;
    }
}
