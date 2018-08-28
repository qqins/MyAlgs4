package swordoffer.chapter4;

import java.util.ArrayList;

/**
 * @author: Hello World
 * @date: 2018/8/25 20:51
 * <p>
 * 面试题29：顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class Question29 {
    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        //行
        int rows = matrix.length - 1;
        //列
        int columns = matrix[0].length - 1;
        int length1 = rows / 2;
        int length2 = columns / 2;
        for (int i = 0; i <= length1 && i <= length2 && rows >= 0 && columns >= 0; i++) {
            printMatrixInCircle(matrix, result, i, rows, i, columns);
            rows--;
            columns--;
        }
        return result;
    }

    /**
     * @param matrix     数组
     * @param result     结果
     * @param rows       行开始
     * @param rowsEnd    行结束
     * @param columns    列开始
     * @param columnsEnd 列结束
     */
    private static void printMatrixInCircle(int[][] matrix, ArrayList<Integer> result, int rows, int rowsEnd, int columns,
                                            int columnsEnd) {
        //从左向右打印
        for (int i = columns; i <= columnsEnd; i++) {
            result.add(matrix[rows][i]);
        }
        //从上向下打印
        if (rows < rowsEnd) {
            for (int i = rows + 1; i <= rowsEnd; i++) {
                result.add(matrix[i][columnsEnd]);
            }
        }
        //从右向左打印
        if (rows < rowsEnd && columns < columnsEnd) {
            for (int i = columnsEnd - 1; i >= columns; i--) {
                result.add(matrix[rowsEnd][i]);
            }
        }
        //从下向上打印
        if (rows < rowsEnd - 1 && columns < columnsEnd) {
            for (int i = rowsEnd - 1; i >= rows + 1; i--) {
                result.add(matrix[i][columns]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        ArrayList<Integer> result = printMatrix(matrix);
        System.out.println(result);
    }
}
