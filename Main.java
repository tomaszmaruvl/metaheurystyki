import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Loader loader = new Loader("./in_files/test.vrp");
        // try{
        // loader.load();
        // }
        // catch (Exception e){
        // System.out.println("Byl blad");
        // }

        Problem pr = new Problem("./in_files/A-n48-k7.vrp");
        EvolutionAlgorithm ev = new EvolutionAlgorithm(100, 100, 0.1, 0.7, 0, false, pr, 5);
        for (int i = 0; i < 10; i++) {
            ev.Calculate(i);
        }
        // Population pop = new Population(100);
        // pop.generatePopulation(pr.loader.citiesList);
        // pr.calculateFitness(pop);

        // Logger log_pop = new Logger("Populations.txt", true);
        // Logger log_ind = new Logger("Individuals.txt", true);
        // log_pop.logPopulation(pop);
        // log_ind.logIndividuals(pop);

        // pop.RunRoulette(40);
        // pop.RunTournament(40);
        // var zmienna = pop.population.get(2).CrossPMX(pop.population.get(3));

        // Greedy dupa = new Greedy (pr.distanceMatrix, pr.loader.citiesList);
        // dupa.calculate();
    }
}
