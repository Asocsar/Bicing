/*
package Heuristic_Function_2;


import Estado.Estado;
import IA.Bicing.Estacion;
import Van.Van;
import aima.search.framework.HeuristicFunction;
import aima.util.Pair;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Heuristic_Function implements HeuristicFunction {

    public Heuristic_Function() {
    }

    public boolean equals(Object obj) {
        boolean retValue = super.equals(obj);
        return retValue;
    }
    //combinations = [[0,1], [0,2], [0,3],...]
    //Para crear todos posibles conjuntos (solo con 2 estaciones, no con 1 estacion)
    public List<int[]> generate(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        int[] combination = new int[r];
        for (int i = 0; i < r; ++i) {
            combination[i] = i;
        }
        while (combination[r - 1] < n) {
            combinations.add(combination.clone());
            int t = r - 1;
            while (t != 0 && combination[t] == n - r + t) {
                t--;
            }
            combination[t]++;
            for (int i = t + 1; i < r; ++i) {
                combination[i] = combination[i - 1] + 1;
            }
        }
        return combinations;
    }

    public double getHeuristicValue(Object state) {
        Estado E = (Estado) state;

        //Las furgonetas con más bicicletas ordenado en la lista F
        int num_est = E.getNum_est();
        int max = 0;
        Pair[] X = new Pair[E.getN_furgo()];
        int [] F = new int[E.getN_furgo()];
        int sum = 0;
        for (int i = 0; i < E.getN_furgo(); ++i) {
            int charge = E.getIFurgo(i).carga();
            X[i] = new Pair(i, charge);
            if (charge > max) max = charge;
            sum += charge;
        }
        int[] L = new int[max + 1];
        Arrays.fill(L, 0);
        for (int i = 0; i < E.getN_furgo(); ++i) {
            L[X[i].getSecond()] += 1;
        }
        for (int i = max-1; i >= 0 ; --i) {
            L[i] = L[i] + L[i + 1];
        }
        for (int i = E.getN_furgo()- 1; i >= 0; --i) {
            F[L[X[i].getSecond()]-1] = X[i].getFirst();
            L[X[i].getSecond()] -= 1;
        }

        //Para cada furgoneta (empezamos con ella que tiene más bicicletas) buscaremos todos los conjuntos de estaciones
        double heuristicValue = 0;
        for (int j = 0; j < F.length; ++j) {
            //double heuristicValue = 0.0;
            Van furgoneta = E.getIFurgo(F[j]);
            /*if (furgoneta.carga() > 0) {
                //Buscaremos cada estacion solo {e1, e2, ..., eN)
                for (int k = 0; k < E.getNum_est(); ++k) {
                    Estacion estacion = E.getEstacion(k);
                    double distancia = ((Math.abs(furgoneta.getCordX() - estacion.getCoordX()) + Math.abs(furgoneta.getCordY() - estacion.getCoordY())) / 1000.0);
                    //double coste = Math.floorDiv(E.getIFurgo(F[j]).carga()+9, 10)*distancia;
                    double ingresoDejar = Math.min(furgoneta.carga(), E.getEstacion(k).getDemanda()-E.getEstacion(k).getNumBicicletasNext());
                    if (ingresoDejar < 0) {
                        ingresoDejar = 0;
                    }
                    heuristicValue += heuristicValue - ingresoDejar * 0.6 + distancia * 0.4;
                }

             */
                //Buscaremos solo los conjuntos {(e1, e2), (e1, e3), (e2, e3), ... }
/*                List<int[]> conjuntos = generate(E.getNum_est(), 2);
                for (int l = 0; l < conjuntos.size(); ++l) {

                    Estacion estacion1 = E.getEstacion(conjuntos.get(l)[0]);
                    Estacion estacion2 = E.getEstacion(conjuntos.get(l)[1]);
                    double distancia = ((Math.abs(furgoneta.getCordX() - estacion1.getCoordX()) + Math.abs(furgoneta.getCordY() - estacion1.getCoordY())) / 1000.0)
                            + (Math.abs(estacion1.getCoordX() - estacion2.getCoordX()) + Math.abs(estacion1.getCoordY() - estacion2.getCoordY()) / 1000.0);
                    double ingresoDejar = Math.min(E.getIFurgo(F[j]).carga(), E.getEstacion(conjuntos.get(l)[0]).getDemanda()-E.getEstacion(conjuntos.get(l)[0]).getNumBicicletasNext())
                            + Math.min(E.getIFurgo(F[j]).carga(), E.getEstacion(conjuntos.get(l)[1]).getDemanda()-E.getEstacion(conjuntos.get(l)[1]).getNumBicicletasNext());
                    //heuristicValue_opt += heuristicValue_opt - ingresoDejar * 0.6 + distancia * 0.4;
                }
                //double heuristicValue_min += heuristicValue_opt;
            }
            //Las furgonetas vacías irán a las estaciones con
            // más bicicletas no usadas para cogerlas y ser listo para dejar más bicicletas la hora que viene
            //else {
                for (int m = 0; m < E.getNum_est(); ++m) {
                    heuristicValue += E.getEstacion(m).getNumBicicletasNoUsadas(); }
            //}
       // }
        //return heuristicValue - E.getganancia();
        //}
//}
// La función heurística ahora (más o menos): min(carga, demanda)/distancia + bicicletasRecogidas/10 + ganancia*/