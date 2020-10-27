import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Population {
    
    List<Solution> population;
    int populationQuantity;
    double bestSolution = 0;
    double worstSolution = Double.MAX_VALUE;
    double averageSolution = 0;

    public Population(int populationQuantity){
        this.populationQuantity = populationQuantity;
        this.population = new ArrayList<Solution>(populationQuantity);
    }

    public void generatePopulation(ArrayList<City> listOfCities){
        // int citiesQuantity = listOfCities.size();
        // for (int i = 0 ; i < citiesQuantity ; i++){
        //     listOfCities.add(new City(0,0,0,0));
        // }
        listOfCities.remove(0);
        for (int i = 0 ; i < populationQuantity ; i++ ){
            Collections.shuffle(listOfCities);
            ArrayList<City> path = (ArrayList<City>) listOfCities.clone();
            population.add(i, new Solution(path));
        }
    }

    public void checkPopulation(int capacity){

    }

    public void printPopulation(){
        for(int i = 0 ; i < populationQuantity ; i++){
            System.out.println(population.get(i).toString());
        }
    }


}

