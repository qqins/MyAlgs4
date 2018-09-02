package swordoffer.chapter5;

/**
 * @author: Hello World
 * @date: 2018/8/31 14:09
 * <p>
 * 面试题47：礼物的最大价值
 * 在一个 m*n 的棋盘的每一个格都放有一个礼物，每个礼物都有一定价值（大于 0）。从左上
 * 角开始拿礼物，每次向右或向下移动一格，直到右下角结束。给定一个棋盘，求拿到礼物的最
 * 大价值。例如，对于如下棋盘
 * 1  10 3 8
 * 12 2  9 6
 * 5  7 4 11
 * 3  7 16 5
 * 礼物的最大价值为 1+12+5+7+7+16+5=53。
 * 解题
 */
public class Question47 {
    public static int getMost(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0) {
            return 0;
        }
        int m = values.length;
        int n = values[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int up = 0;
                int left = 0;
                if (i > 0) {
                    up = dp[j];
                }
                if (j > 0) {
                    left = dp[j - 1];
                }
                dp[j] = Math.max(left, up) + values[i][j];
            }
        }
        return dp[n - 1];
    }

    /**
     * 动态规划
     * f(i,j) = Max(f(i-1, j), f(i, j-1)) + values(i,j)
     */
    public static int getMost2(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0) {
            return 0;
        }
        int m = values.length;
        int n = values[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int left = 0;
                int up = 0;
                if (i > 0) {
                    up = dp[i - 1][j];
                }
                if (j > 0) {
                    left = dp[i][j - 1];
                }
                dp[i][j] = Math.max(up, left) + values[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] values = {{1, 10, 3, 8}, {12, 2, 9, 6}, {5, 7, 4, 11}, {3, 7, 16, 5}};
        System.out.println(getMost(values));
    }
}
