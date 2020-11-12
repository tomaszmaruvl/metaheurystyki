import java.util.ArrayList;

public class GreedyAlgorithm {

    Problem problem;
    double[][] distances;
    ArrayList<City> cities;

    public GreedyAlgorithm(Problem pr) {
        this.problem = pr;
        this.cities = (ArrayList<City>) pr.loader.citiesList.clone();
        // cities.remove(0);
        this.distances = pr.distanceMatrix;
    }

    public Population calculate() {
        Population pop = new Population(cities.size());
        for (int i = 0; i < cities.size(); i++) {
            pop.population.add(calculateStartingWith(cities.get(i)));
        }
        problem.calculateFitness(pop);
        pop.calculateStandardDeviation();
        System.out.println(problem.loader.fileLocation + "\t" + pop.averageSolution + "\t" + pop.bestSolution.fitness + "\t" + pop.worstSolution.fitness + "\t" + pop.stanDev);
        return pop;
    }

    public Solution calculateStartingWith(City city) {
        ArrayList<City> sol = new ArrayList<City>();
        sol.add(city);
        for (int i = 1 ; i < cities.size() ; i++){
            sol.add(getClosestCity(sol.get(i-1), sol));
        }
        return new Solution(sol);
    }

    public City getClosestCity(City city, ArrayList<City> used) {
        double closest = Double.MAX_VALUE;
        int closestIndex = -1;
        for (int j = 0; j < distances[city.index].length; j++) {
            if (distances[city.index][j] < closest && distances[city.index][j] > 0 && !used.contains(cities.get(j)) ) {
                closest = distances[city.index][j];
                closestIndex = j;
            }
        }
        if (closestIndex == -1){
            System.out.print("supa");
        }
        return cities.get(closestIndex);
    }
}
