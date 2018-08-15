package swordoffer.chapter2;

/**
 * @author: Hello World
 * @date: 2018/8/15 21:57
 * <p>
 * 面试题14(leetcode 343)：整数拆分(剪绳子)
 * <p>
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。
 * 返回你可以获得的最大乘积。
 * <p>
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * <p>
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 */
public class Question14 {
    //动态规划
    public static int integerBreakByDp(int n) {
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int[] p = new int[n + 1];
        p[0] = 0;
        p[1] = 1;
        p[2] = 2;
        p[3] = 3;
        int max = 0;
        for (int i = 4; i <= n; i++) {
            max = 0;
            /**
             * 之所以j <= i / 2
             * 以i=4为例, j=1, i-j=3
             * 当j=3, i-j=1
             * 则重复了
             */
            for (int j = 1; j <= i / 2; j++) {
                int temp = p[j] * p[i - j];
                if (max < temp) {
                    max = temp;
                }
            }
            p[i] = max;
        }
        return p[n];
    }

    //贪婪
    public static int integerBreakByGreed(int n) {
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int timesOf3 = n / 3;
        if (n - timesOf3 * 3 == 1) {
            timesOf3--;
        }
        int timeOf2 = (n - timesOf3 * 3) / 2;
        return (int) Math.pow(3, timesOf3) * (int) Math.pow(2, timeOf2);
    }

    public static void main(String[] args) {
        System.out.println(integerBreakByDp(10));
        System.out.println(integerBreakByGreed(10));
    }
}
