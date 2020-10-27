import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Solution {

    List<City> solution;
    double fitness;
    int size;
    List<City> solutionWithReturns;

    public Solution(List<City> solution) {
        this.solution = solution;
        this.size = solution.size();
    }

    public String toString(){
        String result = "";
        for (int i = 0 ; i < solution.size(); i++){
            result += solution.get(i).index;
        }
        return result;
    }
    
    public String getSilutionWithReturnString(){
        String result = "";
        for (int i = 0 ; i < solutionWithReturns.size() ; i++){
            result += solutionWithReturns.get(i).index;
        }
        return result;
    }
    public void mutationSwap(){
        Random gen = new Random();
        int position1 = gen.nextInt(size);
        int position2 = gen.nextInt(size);
        while ((position1 == position2) || (solution.get(position1).index == 0 && solution.get(position2).index == 0) ){position2 = gen.nextInt(size);}

        City temp1 = solution.get(position1);
        City temp2 = solution.get(position2);

        solution.set(position1,temp2);
        solution.set(position2,temp1);
    }

    public void mutationInverse(){
        Random gen = new Random();
        int position1 = 0;
        int position2 = 0;
        while(position1 >= position2) {
            position1 = gen.nextInt(size); 
            position2 = gen.nextInt(size); 
        }
        int mid = position1 + ((position2 + 1) - position1) / 2;
			int endCount = position2;
			for (int i = position1; i < mid; i++) {
				City temp = solution.get(i);
				solution.set(i,solution.get(endCount));
				solution.set(endCount, temp);
				endCount--;
			}
    }
}

