//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import Estado.Estado;
import Check.isGoal;
import Heurisitc_Function_1.Heuristic_Function;
import IA.Bicing.Estaciones;
import IA.Bicing.Estacion;
import Sucesores.sucesores;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Estado Bicing = new Estado(20,1250,30,1,255);
        BicingHillClimbingSearch(Bicing);
        //BicingsimulatedAnnealingSearch(Bicing);
    }

    private static void BicingHillClimbingSearch(Estado TSPB) {
        System.out.println("\nTSP HillClimbing  -->");

        try {
            Problem problem = new Problem(TSPB, new sucesores(), new isGoal(), new Heuristic_Function());
            Search search = new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem, search);
            System.out.println();
            Estado E = (Estado) search.getGoalState();



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




            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
/*
    private static void TSPSimulatedAnnealingSearch(ProbTSPBoard TSPB) {
        System.out.println("\nTSP Simulated Annealing  -->");

        try {
            Problem problem = new Problem(TSPB, new ProbTSPSuccessorFunctionSA(), new ProbTSPGoalTest(), new ProbTSPHeuristicFunction());
            SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(2000, 100, 5, 0.001D);
            SearchAgent agent = new SearchAgent(problem, search);
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
*/
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
}
