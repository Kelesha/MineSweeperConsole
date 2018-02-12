package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       fullMatrix(10,10,0.1);

    }
    public static void gameMenu(){

    }

    public static void fullMatrix(int row, int col, double bombComplexity) {
        Scanner console = new Scanner(System.in);


        // границите са с по + 1 във всяка посока, за граничните случаи на проверка (така или иначе дефинирам row/col ограничение)
        //пълня с бомби матрицата, като използвам рандъм трудност от тип double (0,1 - 0,3 примерно)
        boolean[][] bombs = new boolean[row + 2][col + 2];
        for (int i = 1; i <= row; i++)
            for (int j = 1; j <= col; j++)
                bombs[i][j] = (Math.random() < bombComplexity);


        // обхождам матрицата, пълня с бомби и слагам стойности в клетките около тях (тук ползвах жокер малко)
        int[][] matrix = new int[row + 2][col + 2];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                for (int ii = i - 1; ii <= i + 1; ii++) {
                    for (int jj = j - 1; jj <= j + 1; jj++) {
                        if (bombs[ii][jj]) {
                            matrix[i][j]++;
                            matrix[ii][jj] = 9;
                        }
                    }
                }
            }
        }

        int[][] minusMatrix = new int[row + 2][col + 2];

        boolean check = true;


        System.out.println("Please enter coordinates:");
        int y = Integer.parseInt(console.nextLine());
        int x = Integer.parseInt(console.nextLine());

        if (minusMatrix[x][y] < 0 || x < row || x > row || y < col || y > col) {
            System.out.println("Please enter new coordinate.");
            System.out.println();

        }
        if (matrix[x][y] == 9) {
            System.out.println("BOOM, you lost!");
            //визуализирам цялата матрица с означени бомби с буквата B за инфо
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    if (matrix[i][j] == 9) {
                        System.out.print("B" + " | ");
                    } else
                        System.out.print(matrix[i][j] + " | ");

                }
                System.out.println();
            }
            gameMenu();
        }


        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                for (int ii = i - 1; ii <= i + 1; ii++) {
                    for (int jj = j - 1; jj <= j + 1; jj++) {
                        if (matrix[x][y] != 9) {
                            minusMatrix[x][y] = -1;

                        }
                    }
                }
            }
        }

        //проверка по x/y нагоре и наляво (като заработи коректно, ще обърна цикъла за надолу и надясно)
        //използвам булева, като намери стойност над 0, прави я отрицателна в минусовата матрица и става false,
        //за да излезне от цикъла...

        boolean stopper = true;

        for (int i = x; i >= 1; i--) {
            if (!stopper) {
                break;
            }

            for (int j = y; j >= 1; j--) {
                if (matrix[i][j] == 0) { //обхождам всичко около нулевата клетка и задавам - стойност, за да знам, че е отворена
                    minusMatrix[i][j] = -1;
                    minusMatrix[i][j + 1] = -1;
                    minusMatrix[i][j - 1] = -1;

                    minusMatrix[i - 1][j] = -1;
                    minusMatrix[i - 1][j + 1] = -1;
                    minusMatrix[i - 1][j - 1] = -1;

                    minusMatrix[i + 1][j] = -1;
                    minusMatrix[i + 1][j + 1] = -1;
                    minusMatrix[i + 1][j - 1] = -1;

                    if (i-1>=0 && matrix[i-1][j] == 0 ) {
                        minusMatrix[i - 1][j] = -1;
                        minusMatrix[i - 1][j-1] = -1;
                        minusMatrix[i - 1][j+1] = -1;
                    }
                    if (i-2>=0 &&matrix[i-2][j] == 0) {
                        minusMatrix[i - 2][j] = -1;
                        minusMatrix[i - 2][j-1] = -1;
                        minusMatrix[i - 2][j+1] = -1;
                        if (i-2>=0) {
                            minusMatrix[i - 2][j] = -1;
                            minusMatrix[i - 2][j - 1] = -1;
                            minusMatrix[i - 2][j + 1] = -1;
                        }
                    }

                    if (matrix[i][j] != 9 && matrix[i][j] > 0 ) {
                        minusMatrix[i][j] = -1;
                        stopper = false;

                        if ( matrix[i-1][j] > 0 && matrix[i-1][j] != 9){

                            minusMatrix[i-1][j] = -1;
                            stopper = false;
                        }

                    }
                    if (matrix[i-1][y]>0){
                        minusMatrix[i-1][y] = -1;
                        stopper = false;
                    }

                }


                if (!stopper) {
                    break;
                }
            }
        }
        // печатам скритата матрица с вече отворените стойности
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (minusMatrix[i][j] < 0) {
                    System.out.print(matrix[i][j] + " | ");
                } else {
                    System.out.print("#" + " | ");
                }

            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();

        //визуализирам цялата матрица с означени бомби с буквата B за инфо
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i][j] == 9) {
                    System.out.print("B" + " | ");
                } else
                    System.out.print(matrix[i][j] + " | ");

            }
            System.out.println();
        }
    }
}


