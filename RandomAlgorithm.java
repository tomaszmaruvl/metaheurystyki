public class RandomAlgorithm{

    int repeats;
    Problem problem;

    public RandomAlgorithm(int reps, Problem pr){
        this.repeats = reps;
        this.problem = pr;        
    }

    public void calculate(){
        Population pop = new Population(repeats);
        pop.generatePopulation(problem.loader.citiesList);
        problem.calculateFitness(pop);
        pop.calculateStandardDeviation();
        System.out.println(problem.loader.fileLocation + "\t" + pop.averageSolution + "\t" + pop.bestSolution.fitness + "\t" + pop.worstSolution.fitness + "\t" + pop.stanDev);
    }
}

