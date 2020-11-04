import java.util.ArrayList;

public class Problem {

    private String filePath;
    public double[][] distanceMatrix;
    public int capacity;
    public Loader loader;
    City zero = new City(0, 0, 0, 0);

    public Problem() {
    };

    public Problem(String filePath) {
        this.filePath = filePath;
        try {
            this.loader = new Loader(this.filePath);
            loader.load();
            this.distanceMatrix = loader.distanceMatrix;
            this.capacity = loader.capacity;
        } catch (Exception e) {
        }
    }

    public void calculateFitness(Solution solToCheck) {
        int demand_sum = 0;
        int size = solToCheck.solution.size();
        // City zero = new City(0, 0, 0, 0);
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
    }

    public void calculateFitness(Population popToCalculate) {
        double sum = 0;
        for (int k = 0; k < popToCalculate.populationQuantity; k++) {
            Solution solToCheck = popToCalculate.population.get(k);
            int demand_sum = 0;
            int size = solToCheck.solution.size();
            // City zero = new City(0, 0, 0, 0);
            ArrayList<City> tempSol = new ArrayList<City>();
            double distance = 0;

            for (int i = 0; i < size; i++) {
                City actual_city = solToCheck.solution.get(i);
                demand_sum += actual_city.supplyDemand;
                if (demand_sum > capacity) {
                    if (actual_city.index != 0) {
                        tempSol.add(zero);
                        tempSol.add(actual_city);
                        demand_sum = actual_city.supplyDemand;
                    }
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
            for ( int i = 0 ; i< tempSol.size()-1 ; i++){
                if(tempSol.get(i).index == tempSol.get(i+1).index){
                    System.out.print("");};
            }
            solToCheck.solutionWithReturns = tempSol;
            
            solToCheck.fitness = (int) distance;
            sum += distance;
            if (k == 0) {
                popToCalculate.bestSolution = solToCheck;
                popToCalculate.worstSolution = solToCheck;
            } else {
                if (distance > popToCalculate.worstSolution.fitness)
                    popToCalculate.worstSolution = solToCheck;
                else if (distance < popToCalculate.bestSolution.fitness)
                    popToCalculate.bestSolution = solToCheck;
            }
            if (k == popToCalculate.populationQuantity - 1) {
                popToCalculate.averageSolution = sum / popToCalculate.populationQuantity;
            }
        }
    }

    public void printDistanceMatrix() {
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                System.out.print((int) distanceMatrix[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }
}
