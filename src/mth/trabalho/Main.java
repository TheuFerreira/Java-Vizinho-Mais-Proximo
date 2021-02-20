package mth.trabalho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[][] matrix = readFile();

        List<Integer> minPath = nearestNeighbor(matrix);
        int distance = distance(matrix, minPath);
        System.out.println(distance);
    }

    private static int[][] readFile() {
        int[][] matrix = new int[0][0];
        try {
            File file = new File("paulo_bebedor.txt");
            Scanner scanner = new Scanner(file);

            int size = Integer.parseInt(scanner.nextLine());
            matrix = new int[size][size];

            for (int i = 0; i < size; i++) {
                String line = scanner.nextLine();

                String[] values = line.split(" ");
                for (int j = 0; j < values.length; j++) {
                    matrix[i][j] = Integer.parseInt(values[j]);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
        return matrix;
    }

    private static List<Integer> nearestNeighbor(int[][] matrix) {
        List<Integer> path = new ArrayList<>();
        path.add(0);

        int currentPosition = path.get(0);
        while (path.size() != matrix.length) {
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
        }

        path.add(0);
        return path;
    }

    public static int distance(int[][] matrix, List<Integer> path) {
        int value = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int row = path.get(i);
            int nextRow = path.get(i + 1);

            value += matrix[row][nextRow];
        }

        return value;
    }
}
