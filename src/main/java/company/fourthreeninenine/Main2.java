package company.fourthreeninenine;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/17 10:47
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        int[][] dp = new int[array.length + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= array.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j] + (array[i - 1] <= j ? dp[i][j - array[i - 1]] : 0);
            }
        }
        System.out.println(dp[array.length][m]);
    }
}
