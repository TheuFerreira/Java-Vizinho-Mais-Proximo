package mth.trabalho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	    // write your code here

        int path[][] = readFile("paulo_bebedor.txt");

        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println("");
        }

        List<Integer> minPath = nearestNeighbor(path);
        for (int i = 0; i < minPath.size(); i++) {
            System.out.print(minPath.get(i) + " ");
        }
        System.out.println("");
    }

    private static int[][] readFile(String path) {
        int matrix[][] = new int[0][0];
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            int size = Integer.parseInt(scanner.nextLine());
            matrix = new int[size][size];

            for (int i = 0; i < size; i++) {
                String line = scanner.nextLine();

                String values[] = line.split(" ");
                for (int j = 0; j < values.length; j++) {
                    matrix[i][j] = Integer.parseInt(values[j]);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } finally {
            return matrix;
        }
    }

    // Vizinho mais próximo
    private static List<Integer> nearestNeighbor(int matrix[][]) {
        List<Integer> path = new ArrayList<>();
        path.add(0);

        int currentPosition = path.get(0);
        while (true) {
            int selectedIndex = -1;
            int selectedWeight = -1;
            for (int i = 0; i < matrix[currentPosition].length; i++) {
                if (i == currentPosition || path.contains(i))
                    continue;

                if (selectedIndex == -1) {
                    selectedWeight = matrix[currentPosition][i];
                    selectedIndex = i;
                    continue;
                }

                if (selectedWeight > matrix[currentPosition][i]) {
                    selectedWeight = matrix[currentPosition][i];
                    selectedIndex = i;
                }
            }
            currentPosition = selectedIndex;
            path.add(selectedIndex);
            if (path.size() == matrix.length)
                break;
        }

        path.add(0);
        return path;
    }
}
