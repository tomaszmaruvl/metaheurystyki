import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Solution implements Comparable<Solution> {

    ArrayList<City> solution;
    double fitness;
    int size;
    List<City> solutionWithReturns;

    public Solution(ArrayList<City> solution) {
        this.solution = solution;
        this.size = solution.size();
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < solution.size(); i++) {
            result += (solution.get(i).index + " ");
        }
        return result;
    }

    public String toStringReturns() {
        String result = "";
        for (int i = 0; i < solutionWithReturns.size(); i++) {
            result += (solutionWithReturns.get(i).index + " ");
        }
        return result;
    }

    public String getSolutionWithReturnString() {
        String result = "";
        for (int i = 0; i < solutionWithReturns.size(); i++) {
            result += (solutionWithReturns.get(i).index + " ");
        }
        return result;
    }

    public String getSummaryString() {
        String result = toString() + " : " + getSolutionWithReturnString() + " : " + fitness;
        return result;
    }

    public void mutationSwap() {
        Random gen = new Random();
        int position1 = gen.nextInt(size);
        int position2 = gen.nextInt(size);
        while ((position1 == position2) || (solution.get(position1).index == 0 && solution.get(position2).index == 0)) {
            position2 = gen.nextInt(size);
        }

        City temp1 = solution.get(position1);
        City temp2 = solution.get(position2);

        solution.set(position1, temp2);
        solution.set(position2, temp1);
    }

    public void mutationInverse() {
        Random gen = new Random();
        int position1 = 0;
        int position2 = 0;
        while (position1 >= position2) {
            position1 = gen.nextInt(size);
            position2 = gen.nextInt(size);
        }
        int mid = position1 + ((position2 + 1) - position1) / 2;
        int endCount = position2;
        for (int i = position1; i < mid; i++) {
            City temp = solution.get(i);
            solution.set(i, solution.get(endCount));
            solution.set(endCount, temp);
            endCount--;
        }
    }

    public CrossedSolutions CrossPMX(Solution otherSolution, ArrayList<City> cities) {
        CrossedSolutions children = new CrossedSolutions();
        Random gen = new Random();
        int smallerCutIndex = gen.nextInt(size - 2);
        int biggerCutIndex = gen.nextInt(size - smallerCutIndex) + smallerCutIndex;
        ArrayList<City> firstSolution = new ArrayList<City>(size);
        ArrayList<City> secondSolution = new ArrayList<City>(size);
        for (int i = 0; i < size; i++) {
            if (i < smallerCutIndex || i > biggerCutIndex) {
                // firstSolution.set(i, solution.get(i));
                // secondSolution.set(i, otherSolution.solution.get(i));
                firstSolution.add(solution.get(i));
                secondSolution.add(otherSolution.solution.get(i));
            } else {
                firstSolution.add(otherSolution.solution.get(i));
                secondSolution.add(solution.get(i));
            }
        }
        // jeśli jest konflikt kopiuję z drugiego rodzica
        for (int i = 0; i < size; i++) {
            if (i < smallerCutIndex || i > biggerCutIndex) {
                if (Collections.frequency(firstSolution, firstSolution.get(i)) > 1) {
                    firstSolution.set(i, otherSolution.solution.get(i));
                }
                if (Collections.frequency(secondSolution, secondSolution.get(i)) > 1) {
                    secondSolution.set(i, solution.get(i));
                }
            }
        }

        children.child1 = new Solution(firstSolution);
        children.child2 = new Solution(secondSolution);
        children.child1.Repair(cities);
        children.child2.Repair(cities);
        return children;
    }

    @Override
    public int compareTo(Solution h) {
        if (fitness > h.fitness) {
            return 1;
        } else if (fitness == h.fitness) {
            return 0;
        } else {
            return -1;
        }
    }

    public void Repair(ArrayList<City> cities) {
        ArrayList<City> listOfCities = (ArrayList<City>) cities.clone();
        listOfCities.remove(0);
        for (int i = 0; i < size; i++)
            for (int j = i + 1; j < size; j++) {
                if (solution.get(i) == solution.get(j)) {
                    for (int k = 0; k < size; k++) {
                        boolean canInsert = true;
                        for (int l = 0; l < size; l++) {
                            if (solution.get(l).index == listOfCities.get(k).index) {
                                canInsert = false;
                                break;
                            }
                        }
                        if (canInsert) {
                            solution.set(j, listOfCities.get(k));
                            break;
                        }
                    }
                }
            }
    }

    // public CrossedSolutions CrossOX(Solution secondSolution){
    //     Random gen = new Random();
    //         CrossedSolutions result = new CrossedSolutions();
    //         int smallerCutIndex = gen.nextInt( size - 2);
    //         int biggerCutIndex = gen.nextInt(size - smallerCutIndex) + smallerCutIndex;
    //         int[] firstChildGenotype = new int[size];
    //         int[] secondChildGenotype = new int[size];

    //         for (int i = 0; i < size; i++)
    //         {
    //                 firstChildGenotype[i] = -1;
    //                 secondChildGenotype[i] = -1;
    //         }

    //         for (int i = 0; i < size; i++)
    //         {
    //             if (! (i < smallerCutIndex || i > biggerCutIndex))
    //             {
    //                 firstChildGenotype[i] = secondSolution.solution.get(i).index;
    //                 secondChildGenotype[i] = solution.get(i).index;
    //             }
    //         }
    //         //tworzę listę z niepodmienionych fragmentów genotypu i dla każdego dziecka używam genów które nie występują w nim


    //         ArrayList<Integer> usunedGens = new ArrayList<Integer>();
    //         //copy usused gens from first indiv
    //         for (int i = 0; i < size; i++)
    //         {
    //             if ((i < smallerCutIndex || i > biggerCutIndex))
    //                 usunedGens.add(solution.get(i).index);
    //         }
    //         //copy usused gens from second indiv
    //         for (int i = 0; i < size; i++)
    //         {
    //             if ((i < smallerCutIndex || i > biggerCutIndex))
    //                 usunedGens.add(secondSolution.solution.get(i).index);
    //         }
    //         List<int> usunedGensInFirstChild = usunedGens.Where(x=> !firstChildGenotype.Contains(x)).GroupBy(y => y).Select(z => z.First()).ToList();
    //         List<int> usunedGensInSecondChild = usunedGens.Where(x => !secondChildGenotype.Contains(x)).GroupBy(y => y).Select(z => z.First()).ToList();
    //         int lastUsedFromFirstList = 0, lastUsedFromSecondList = 0;
    //         for (int i = 0; i < this.Genotype.Length; i++)
    //         {
    //             if ((i < smallerCutIndex || i > biggerCutIndex))
    //             {
    //                         firstChildGenotype[i] = usunedGensInFirstChild[lastUsedFromFirstList++];
    //                         secondChildGenotype[i] = usunedGensInSecondChild[lastUsedFromSecondList++];
    //             }
    //         }
    //         children.First = new Individual(Algorythm, firstChildGenotype);
    //         children.Second = new Individual(Algorythm, secondChildGenotype);
    //         return children;
    //     }

    class CrossedSolutions {
        Solution child1;
        Solution child2;

        public CrossedSolutions() {
        };

        public CrossedSolutions(Solution s1, Solution s2) {
            child1 = s1;
            child2 = s2;
        }
    }
}
