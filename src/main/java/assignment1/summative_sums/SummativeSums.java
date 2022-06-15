package assignment1.summative_sums;

import java.util.Arrays;

public class SummativeSums {

    private static int sumArray(int[] array){
        return Arrays.stream(array).sum();
    }

    public static void main(String[] args){
        int[] array1 = new int[]{ 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] array2 = new int[]{ 999, -60, -77, 14, 160, 301 };
        int[] array3 = new int[]{ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,
                140, 150, 160, 170, 180, 190, 200, -99 };
        int[][] arrays = new int[][]{array1,array2,array3};
        int count = 1;
        for(int[] array: arrays){
            System.out.println("#" + count + " Array Sum: " + sumArray(array));
            count++;
        }
    }

}
