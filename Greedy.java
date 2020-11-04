import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Greedy {
    public double[][] distanceMatrix;
    public List<City> cities;

    public Greedy(double[][] dmatrix, List<City> lcities) {
        this.distanceMatrix = dmatrix;
        this.cities = lcities;
    }

    public Population calculate() {
        ArrayList<ArrayList<Helper>> matrix = new ArrayList<ArrayList<Helper>>();
        for (int i = 0; i < cities.size(); i++) {
            double[] distances = distanceMatrix[i];
            ArrayList<Helper> list = new ArrayList<Helper>();
            for (int j = 0; j < cities.size(); j++) {
                list.add(new Helper(cities.get(j), distances[j]));
            }
            Collections.sort(list);
            matrix.add(list);
        }
        return null;
    }

    private class Helper implements Comparable<Helper> {
        City city;
        double distance;

        public Helper(City i, double d) {
            this.city = i;
            this.distance = d;
        }
        @Override
        public int compareTo(Helper h) {
            if(distance > h.distance) {return 1;}
            else if (distance == h.distance) {return 0;}
            else {return -1;}
        }
    }
}
