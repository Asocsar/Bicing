package Heuristic_Function_2;

import Estado.Estado;
import IA.Bicing.Estacion;
import Van.Van;
import aima.search.framework.HeuristicFunction;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;


public class Heuristic_Function implements HeuristicFunction{

    public Heuristic_Function() {
    }

    public boolean equals(Object obj) {
        boolean retValue = super.equals(obj);
        return retValue;
    }

    public double getHeuristicValue(Object state) {
        Estado E = (Estado) state;
        int earnings = 0;
        Van[] listFurgonetas = new Van[E.getN_furgo()];
        for (int i = 0; i < E.getN_furgo(); ++i) {
            listFurgonetas[i] = E.getIFurgo(i);
        }
        int n = E.getN_furgo();
        for (int j = 0; j < n-1; ++j) {
            int min_idx = j;
            for (int k = j+1; k < n; ++k) {
                if (listFurgonetas[k].carga() < listFurgonetas[min_idx].carga())
                    min_idx = k;
                }
            int temp = listFurgonetas[min_idx].carga();
            listFurgonetas[min_idx] = listFurgonetas[j];
            listFurgonetas[j].setCharge(temp);
            //Collections.reverse(Van listFurgonetas);
        }
        for (int i = 0; i < listFurgonetas.length; ++i) {
            for (int j = 0; j < E.getNum_est(); ++j) {
                Estacion Est = E.getEstacion(j);
                Van V =  listFurgonetas[i];
                int distancia = ((Math.abs(V.getCordX() - Est.getCoordX()) + Math.abs(V.getCordY()-Est.getCoordY())))/1000;
                int suply = Math.min(Est.getDemanda(), V.carga());
                int cost = Math.floorDiv(V.carga() + 9, 10)*distancia;
                earnings = suply - cost;
            }
        }
        return -earnings;
    }
}


        //for (int i = 0; i < E.getNum_est(); ++i) {
        //    coste += E.getEstacion(i).getNumBicicletasNoUsadas();
        //}
//
  //      int [] suply = new int [E.getNum_est()];
    //    Arrays.fill(suply, 0);
      //  for (int i = 0; i < E.getN_furgo(); ++i) {
        //    Van V = E.getIFurgo(i);
          //  int carga = V.carga();
            //for (int j = 0; j < E.getNum_est(); ++j) {
              //  Estacion Est = E.getEstacion(j);
                //int distancia = ((Math.abs(V.getCordX() - Est.getCoordX()) + Math.abs(V.getCordY()-Est.getCoordY())))/1000;
                //if (Est.getNumBicicletasNext() + suply[j] < Est.getDemanda() && carga > 0) {
                  //  suply[j] += Math.min(carga, Est.getNumBicicletasNext()-Est.getDemanda());
                    //if (distancia != 0) coste += ((carga+9)/10)/distancia;
                    //coste -= suply[j];
                    //carga -= suply[j];
                //}
                //else coste -= distancia;
            //}
        //}
        //if (E.getganancia() > 0) coste = coste - (int) E.getganancia() * 2;
        //return coste;
    //}
//}
