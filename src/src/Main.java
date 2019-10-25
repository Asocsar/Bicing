//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import Estado.Estado;
import Check.isGoal;
//import Heurisitc_Function_1.Heuristic_Function;
import Heuristic_Function_2.Heuristic_Function;
import IA.Bicing.Estaciones;
import IA.Bicing.Estacion;
import Sucesores.sucesores;


import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import sucesoresA.sucesoresA;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        pruebaG();
    }

    public static void pruebaO () throws IOException {
        File file = new File("Sedds.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        for (int i = 0; i < 100; ++i) {
            System.out.println(i);
            int s = Integer.parseInt(br.readLine());
            Estado Bicing = new Estado(25,1250,5,1,s,0);
            BicingHillClimbingSearch(Bicing, 1);
        }

        //Estado Bicing = new Estado(25,1250,5,0,1234);
        /*long StartTime = System.nanoTime();
        BicingsimulatedAnnealingSearch(Bicing);
        long EndTime = System.nanoTime();
        System.out.println("Execution in Miliseconds " + (EndTime-StartTime)/1000000);*/
    }

    public static void pruebaG () throws IOException {
        File file = new File("Sedds.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        for (int i = 0; i < 100; ++i) {
            System.out.println(i);
            int s = Integer.parseInt(br.readLine());
            Estado Bicing = new Estado(25,1250,5,1,s,1);
            BicingHillClimbingSearch(Bicing, 1);
        }

        //Estado Bicing = new Estado(25,1250,5,0,1234);
        /*long StartTime = System.nanoTime();
        BicingsimulatedAnnealingSearch(Bicing);
        long EndTime = System.nanoTime();
        System.out.println("Execution in Miliseconds " + (EndTime-StartTime)/1000000);*/
    }

    public static void pruebaR () throws IOException {
        File file = new File("Sedds.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        for (int i = 0; i < 100; ++i) {
            int s = Integer.parseInt(br.readLine());
            Estado Bicing = new Estado(25, 1250, 5, 1, s, 2);
            BicingHillClimbingSearch(Bicing, 20);
        }
        /*
        System.out.println("Execution in Miliseconds " + (EndTime-StartTime)/1000000);
        StartTime = System.nanoTime();
        //BicingsimulatedAnnealingSearch(Bicing);
        EndTime = System.nanoTime();
        System.out.println("Execution in Miliseconds " + (EndTime-StartTime)/1000000);*/
    }

    private static void BicingHillClimbingSearch(Estado Bicing, int num) {
        //System.out.println("\nTSP HillClimbing  -->");


        try {
            double MedT = 0;
            double MedN = 0;
            double MedB = 0;
            for (int i = 0; i < num; ++i) {
                Estado TSPB = Bicing.clonar();
                long StartTime = System.nanoTime();
                Problem problem = new Problem(TSPB, new sucesores(), new isGoal(), new Heuristic_Function());
                Search search = new HillClimbingSearch();
                SearchAgent agent = new SearchAgent(problem, search);
                Properties properties = agent.getInstrumentation();
                long EndTime = System.nanoTime();
                Estado E = (Estado) search.getGoalState();
                long time = ((EndTime - StartTime) / 1000000);
                MedT += time;
                MedB += E.getganancia();
                MedN += Integer.parseInt(properties.getProperty((String)properties.keySet().iterator().next()));
            }
            MedB /= num;
            MedN /= num;
            MedT /= num;
            Writer output;
            output = new BufferedWriter(new FileWriter("Estadisticas_Rnd_D1_H2.txt", true));
            String sep = ",";
            String S = MedB + sep + MedT + sep + MedN;
            S = S + '\n';
            output.append(S);
            output.close();

            /*
            for (int i = 0; i < E.getN_furgo(); ++i) {
                System.out.println("Recorrido por furgoneta " + i + "  " + E.getIFurgo(i).getLong_t());
            }

            //printEstado(E);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());*/
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    private static void BicingsimulatedAnnealingSearch(Estado Bicing) {
        System.out.println("\nTSP Simulated Annealing  -->");

        try {
            Problem problem = new Problem(Bicing, new sucesoresA(), new isGoal(), new Heuristic_Function());
            SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(2000, 200, 10, 0.001D);
            SearchAgent agent = new SearchAgent(problem, search);
            Estado E = (Estado) search.getGoalState();

            for (int i = 0; i < E.getN_furgo(); ++i) {
                System.out.println("Recorrido por furgoneta " + i + "  " + E.getIFurgo(i).getLong_t());
            }
            //printEstado(E);
            System.out.println();
            System.out.println(E.getganancia());
            //printActions(agent.getActions());
            //printInstrumentation(agent.getInstrumentation());
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();

        while(keys.hasNext()) {
            String key = (String)keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }

    }

    private static void printActions(List actions) {
        for(int i = 0; i < actions.size(); ++i) {
            String action = (String)actions.get(i);
            System.out.println(action);
        }

    }


    public static  void printEstado (Estado E) {
        System.out.println("----INFO GENERAL----");
        int numStay ;
        int numCurr ;
        int numDem ;
        int sumBic = 0;
        int sumDem = 0;
        int sumAvai = 0;
        int sumNeed = 0;
        Estaciones b = E.getEstaciones();
        double[] stdDem = new double[b.size()];
        double[] stdStay = new double[b.size()];
        double[] stdCurr = new double[b.size()];
        System.out.println("Sta Cur Dem Dif Exc ");

        for(int i = 0; i < b.size(); ++i) {
            numStay = ((Estacion)b.get(i)).getNumBicicletasNoUsadas();
            numCurr = ((Estacion)b.get(i)).getNumBicicletasNext();
            numDem = ((Estacion)b.get(i)).getDemanda();
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

                sumAvai += mover;
            } else {
                mover = 0;
                sumNeed -= balance;
            }

            System.out.format("est %2d = %2d %2d\n", i, ((Estacion)b.get(i)).getCoordX(), ((Estacion)b.get(i)).getCoordY());
            System.out.format("%3d %3d %3d %3d %3d\n", numStay, numCurr, numDem, balance, mover);
        }

        System.out.format("\nBicis= %3d Demanda= %3d Disponibles= %3d Necesitan= %3d\n\n", sumBic, sumDem, sumAvai, sumNeed);

    }
}
