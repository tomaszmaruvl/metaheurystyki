import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Problem pr = new Problem("./in_files/A-n48-k7.vrp");
        EvolutionAlgorithm ev = new EvolutionAlgorithm(200, 1000, 0.8, 0.02, false, pr, 100);
        System.out.println((ev.populationQuantity + " " + ev.generationQuantity + " " + ev.mutationProp + " " + ev.crossoverProp + " " + ev.tournamentSize));
        for (int i = 0; i < 10; i++) {
        ev.Calculate(i);
        }



        // String[] files = { "A-n32-k5.vrp", "A-n37-k6.vrp", "A-n39-k5.vrp", "A-n45-k6.vrp", "A-n48-k7.vrp", "test.vrp" };
        // for (String file : files) {
        //     Problem pr = new Problem("./in_files/" + file);
        //     RandomAlgorithm rand = new RandomAlgorithm(10000, pr);
        //     rand.calculate();

        // String[] files = { "A-n32-k5.vrp", "A-n37-k6.vrp", "A-n39-k5.vrp", "A-n45-k6.vrp", "A-n48-k7.vrp", "test.vrp" };
        // for (String file : files) {
        //      Problem pr2 = new Problem("./in_files/" + file);
        //      GreedyAlgorithm rand = new GreedyAlgorithm(pr);
        //      Population pop = rand.calculate();
        // }
    }
}
