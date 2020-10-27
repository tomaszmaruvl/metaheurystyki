import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Loader {

    String fileLocation;
    double[][] distanceMatrix;
    ArrayList<City> citiesList;
    int capacity;
    int dimension;


    public Loader(String fileLocation){
        this.fileLocation = fileLocation;
        this.citiesList = new ArrayList<City>();
    }


    public void load() throws FileNotFoundException {
        File file = new File(fileLocation);
        Scanner input = new Scanner(file);
        List<String> list = new ArrayList<String>();


        while (input.hasNextLine()) {//Lista stringow z tekstu
            list.add(input.nextLine());
        }
        input.close();

        dimension = Integer.parseInt((((list.get(3)).split(":"))[1]).trim());
        capacity = Integer.parseInt((((list.get(5)).split(":"))[1]).trim());

        for (int i = 7 ; i < 7 + dimension ; i++){
            String[] coord_row = (list.get(i)).split(" ");
            String[] demand_row = (list.get(i+dimension+1)).split(" ");

            citiesList.add(new City((toInt(coord_row[0])-1), toInt(coord_row[1]), toInt(coord_row[2]), toInt(demand_row[1])));
        }
        calculateDistanceMatrix();
    }

    public void calculateDistanceMatrix(){
        distanceMatrix = new double[dimension][dimension];
        for (int i = 0 ; i < dimension ; i++ ){
            for (int j = 0 ; j < dimension ; j++){
                distanceMatrix[i][j] = (citiesList.get(i)).calculateDistance(citiesList.get(j));
            }
        }
    }
    private int toInt(String strNumber){
        return Integer.parseInt(strNumber);
    }
}
