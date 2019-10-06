import IA.Bicing.Estaciones;
import IA.Bicing.Estacion;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int sumBic = 0;
        int sumDem = 0;
        int sumAvai = 0;
        int sumNeed = 0;
        int cantENeed = 0;
        int cantEDisp = 0;

        Estaciones b = new Estaciones(25, 1250, 1, 233);
        int [] dispo = new int [b.size()];
        int [] faltan = new int [b.size()];
        Arrays.fill(dispo, 0);
        Arrays.fill(faltan, 0);
        double[] stdDem = new double[b.size()];
        double[] stdStay = new double[b.size()];
        double[] stdCurr = new double[b.size()];
        System.out.println("Sta Cur Dem Dif Exc ");

        for(int i = 0; i < b.size(); ++i) {
            int numStay = ((Estacion)b.get(i)).getNumBicicletasNoUsadas();
            int numCurr = ((Estacion)b.get(i)).getNumBicicletasNext();
            int numDem = ((Estacion)b.get(i)).getDemanda();
            stdStay[i] = (double)numStay;
            stdCurr[i] = (double)numCurr;
            stdDem[i] = (double)numDem;
            sumBic += numCurr;
            sumDem += numDem;
            int balance = numCurr - numDem;
            int mover;
            if (balance > 0) {
                if (balance > numStay) {
                    mover = numStay;

                } else {
                    mover = balance;
                }
                dispo[i] = mover;
                ++cantEDisp;
                sumAvai += mover;
            } else {
                mover = 0;
                faltan[i] = -(balance);
                sumNeed -= balance;
                ++cantENeed;
            }

            System.out.format("est %2d = %2d %2d\n", i, ((Estacion)b.get(i)).getCoordX(), ((Estacion)b.get(i)).getCoordY());
            System.out.format("%3d %3d %3d %3d %3d\n", numStay, numCurr, numDem, balance, mover);
        }

        System.out.format("\nBicis= %3d Demanda= %3d Disponibles= %3d Necesitan= %3d  NumeroESt.Disp = %3d NumeroEst.Neces = %d\n\n", sumBic, sumDem, sumAvai, sumNeed, cantEDisp, cantENeed);
        System.out.println("Estaciones con sus bicis sobrantes");
       /* for (int i = 0; i < b.size(); ++i) {
            if (dispo[i] > 0) System.out.format("\n Estacion numero= %d con= %d bicis disponibles\n", i, dispo[i]);

        }*/

        for (int i = 0; i < b.size(); ++i) {
            if (faltan[i] > 0) System.out.format("\n Estacion numero= %d con= %d bicis faltantes\n", i, faltan[i]);

        }

    }
}

