import java.util.ArrayList;

public class Problem {

    private String filePath;
    private double[][] distanceMatrix;
    private int dimension;
    public int capacity;
    public Loader loader;

    public Problem() {
    };

    public Problem(String filePath) {
        this.filePath = filePath;
        try {
            this.loader = new Loader(this.filePath);
            loader.load();
            this.distanceMatrix = loader.distanceMatrix;
            this.dimension = loader.dimension;
            this.capacity = loader.capacity;
        } catch (Exception e) {
        }
    }

    public void calculateFitness(Solution solToCheck) {
        int demand_sum = 0;
        int size = solToCheck.solution.size();
        City zero = new City(0, 0, 0, 0);
        ArrayList<City> tempSol = new ArrayList<City>();
        double distance = 0;

        for (int i = 0; i < size; i++) {
            City actual_city = solToCheck.solution.get(i);
            demand_sum += actual_city.supplyDemand;
            if (demand_sum > capacity) {
                tempSol.add(zero);
                tempSol.add(actual_city);
                demand_sum = actual_city.supplyDemand;
            } else {
                tempSol.add(actual_city);
            }
        }
        for (int i = 0; i < tempSol.size() - 1; i++) {
            int ac_city_nb = tempSol.get(i).index;
            if (i == 0) {
                distance += distanceMatrix[0][ac_city_nb];
            }
            int nx_city_nb = tempSol.get(i + 1).index;
            distance += distanceMatrix[ac_city_nb][nx_city_nb];
            if (i == tempSol.size() - 2) {
                distance += distanceMatrix[0][nx_city_nb];
            }
        }
        solToCheck.solutionWithReturns = tempSol;
        solToCheck.fitness = (int) distance;

        // int demand_sum = 0;
        // int size = solToCheck.solution.size();
        // City zero = new City(0, 0, 0, 0);
        // ArrayList<City> tempSol = new ArrayList<City>();
        // tempSol.add(solToCheck.solution.get(0));
        // for (int i = 1; i < size; i++) {
        // City actual = solToCheck.solution.get(i);
        // City lastOrdered = tempSol.get(tempSol.size() - 1);
        // demand_sum += lastOrdered.supplyDemand;
        // if (actual.index == 0 && lastOrdered.index == 0) {
        // continue;
        // } else if (demand_sum + actual.supplyDemand > capacity) {
        // if(lastOrdered.index != 0) tempSol.add(zero);
        // tempSol.add(actual);
        // demand_sum = 0;
        // }else{
        // tempSol.add(actual);
        // }
        // }
        // int sizeAfter = tempSol.size();
        // for (int i = sizeAfter; i < size; i++) {
        // tempSol.add(zero);
        // }
        // Solution temp = new Solution(tempSol);
        // System.out.println(temp.toString());
    }
}
