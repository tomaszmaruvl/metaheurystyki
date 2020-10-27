import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Loader loader = new Loader("./in_files/test.vrp");
        // try{
        // loader.load();
        // }
        // catch (Exception e){
        //     System.out.println("Byl blad");
        // }
 
        Problem pr = new Problem("./in_files/test.vrp");
        Population pop = new Population(100);
        pop.generatePopulation(pr.loader.citiesList);
        Solution tempSol = pop.population.get(3);
        System.out.println(tempSol.toString());
        pr.calculateFitness(tempSol);
        System.out.print(tempSol.getSilutionWithReturnString() + " : " + tempSol.fitness);
        
    }
}
