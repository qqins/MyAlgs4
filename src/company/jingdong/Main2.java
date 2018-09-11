package company.jingdong;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/9 20:04
 * <p>
 * 现有n个物品，每个物品有三个参数 ai , bi , ci ，定义i物品不合格品的依据是 : 若存在物品 j ,
 * 且aj>ai , bj>bi , cj>ci，则称i物品为不合格品。
 * 现给出n个物品的a,b,c参数，请你求出不合格品的数量。
 * <p>
 * 输入
 * 第一行包含一个整数n(1<=n<=500000),表示物品的数量。接下来有n行，每行有三个整数，ai,bi,ci
 * 表示第i个物品的三个参数，1≤ai,bi,ci≤109。
 * <p>
 * 输出
 * 输出包含一个整数，表示不合格品的数量。
 * <p>
 * <p>
 * 样例输入
 * 3
 * 1 4 2
 * 4 3 2
 * 2 5 3
 * 样例输出
 * 1
 * <p>
 * Hint
 * 样例解释
 * 物品1的a,b,c均小于物品3的a,b,c,因此1为不合格品。
 */
public class Main2 {
    private static boolean[] res;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] array = new int[n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        res = new boolean[n];
        for (int i = 0; i < n; i++) {
            product(array, i);
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (res[i]) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static void product(int[][] array, int row) {
        for (int i = 0; i < array.length; i++) {
            boolean flag = true;
            if (i == row) {
                continue;
            }
            for (int j = 0; j < array[0].length; j++) {
                if (array[row][j] > array[i][j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res[row] = true;
            }
        }
    }
}
