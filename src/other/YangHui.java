package other;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/13 12:49
 */
public class YangHui {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[][] array = new int[num][];
        for (int i = 0; i < num; i++) {
            array[i] = new int[i + 1];
        }
        for (int i = 0; i < num; i++) {
            array[i][0] = 1;
            array[i][i] = 1;
        }
        for (int i = 2; i < num; i++) {
            for (int j = 1; j < i; j++) {
                array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
            }
        }
        for (int i = 0; i < num; i++) {
            for (int j = i; j < num - 1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i + 1; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
