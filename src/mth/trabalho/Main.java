package mth.trabalho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	    // write your code here

        int path[][] = ReadFile("paulo_bebedor.txt");

        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private static int[][] ReadFile(String path) {
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
}
