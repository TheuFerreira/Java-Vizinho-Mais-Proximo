package mth.trabalho;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        long ti = System.currentTimeMillis();
        int[][] matrix = readFile();

        List<Integer> minPath = nearestNeighbor(matrix);
        System.out.println("Solução vizinho mais próximo\n");
        showPath(minPath);
        System.out.println("\n");
        int distance = distance(matrix, minPath);
        System.out.println("A distância total é: " + distance);

        List<Integer> answer = localSearch(matrix);
        System.out.println("Solução final busca local\n");
        showPath(answer);
        System.out.println("\n");
        distance = distance(matrix, answer);
        System.out.println("A distância total é: " + distance);

        List<Integer>list = localSearch(answer, matrix);
        if (list!=null)
        {
            showPath(list);
            System.out.println("\n");
            distance = distance(matrix, list);

            System.out.println("A distância total é: " + distance);
        }
        System.out.println("O tempo de execução foi de: "+(System.currentTimeMillis()-ti)+" milisegundos");
    }

    private static int[][] readFile() {
        int[][] matrix = new int[0][0];
        try {
            File file = new File("INST3.txt");
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

    private static void showPath(List<Integer> path) {
        for (Integer i : path)
            System.out.print(i + " ");
        System.out.println();
    }

    private static int distance(int[][] matrix, List<Integer> path) {
        int value = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int row = path.get(i);
            int nextRow = path.get(i + 1);

            value += matrix[row][nextRow];
        }

        return value;
    }

    private static List<Integer> localSearch(int[][] matrix) {
        List<Integer> minPath = nearestNeighbor(matrix);

        int distanceMinPath = distance(matrix, minPath);
        int distanceNewPath = 1000;
        int cont=0;

        System.out.println("Tentativas de otimização utilizando busca local\n");

        while ((cont < 1000000) || (distanceMinPath <= distanceNewPath)) {
            minPath.remove(0);
            minPath.remove(minPath.size() - 1);

            minPath = switchValues(minPath, matrix.length - 2);

            minPath.add(0, 0);
            minPath.add(0);

            distanceNewPath = distance(matrix, minPath);
            //showPath(minPath);
            cont++;
        }

        return minPath;
    }










    private static List<Integer> localSearch(List<Integer> minPath, int [][] matrix) {


        int distanceMinPath = distance(matrix, minPath);
        int distanceNewPath = 1000;
        int cont=0;

        System.out.println("Tentativas de otimização utilizando busca local\n");

        while ((cont < 1000) ) {
            minPath.remove(0);
            minPath.remove(minPath.size() - 1);

            minPath = switchValues(minPath, matrix.length - 2);

            minPath.add(0, 0);
            minPath.add(0);

            distanceNewPath = distance(matrix, minPath);
            //showPath(minPath);
            cont++;
            if (distanceMinPath > distanceNewPath)
            {
                break;
            }
        }


        return (distanceMinPath <= distanceNewPath)? minPath:null;
    }









    private static List<Integer> switchValues(List<Integer> path, int size) {
        Random random = new Random();
        int index = random.nextInt(size);
        int secondIndex = random.nextInt(size);

        while (index == secondIndex){
            index = random.nextInt(size);
            secondIndex = random.nextInt(size);
        }

        int temp = path.get(index);

        path.set(index, path.get(secondIndex));
        path.set(secondIndex, temp);


        return path;
    }
}
