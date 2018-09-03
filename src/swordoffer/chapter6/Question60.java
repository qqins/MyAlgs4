package swordoffer.chapter6;

/**
 * @author: Hello World
 * @date: 2018/9/3 16:20
 * <p>
 * 面试题60：n个骰子的点数
 * 题目：把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s
 * 的所有可能的值出现的概率。
 */
public class Question60 {
    private static int maxValue = 6;

    public static void printProbability(int number) {
        if (number < 1) {
            return;
        }
        int maxSum = maxValue * number;
        //该数组用来存储每个sum出现的个数，范围为number~maxSum
        int[] probabilities = new int[maxSum - number + 1];
        probability(number, probabilities);
        double total = Math.pow(maxValue, number);
        for (int i = number; i <= maxSum; i++) {
            System.out.println("和为" + i + "概率为：" + probabilities[i - number] + "/" + total);
        }
    }

    private static void probability(int number, int[] probabilities) {
        for (int i = 1; i <= maxValue; i++) {
            probability(number, number - 1, i, probabilities);
        }
    }

    /**
     * 类似于全排列
     *
     * @param number        骰子总个数
     * @param current       当前使用骰子的个数
     * @param sum           所使用骰子相加的和
     * @param probabilities 保存骰子相加和的数组
     */
    private static void probability(int number, int current, int sum, int[] probabilities) {
        if (current == 0) {
            probabilities[sum - number]++;
            return;
        }
        for (int i = 1; i <= maxValue; i++) {
            probability(number, current - 1, sum + i, probabilities);
        }
    }

    /**
     * 使用动态规划
     * f(n) = f(n-1) + f(n-2) + f(n-3) + f(n-4) + f(n-5) + f(n-6)
     *
     * 在k-1个骰子的基础上，再增加一个骰子出现点数和为n的结果只有这6种情况！
     * k表示骰子的个数，n表示总点数
     * 所以：f(k,n)=f(k-1,n-1)+f(k-1,n-2)+f(k-1,n-3)+f(k-1,n-4)+f(k-1,n-5)+f(k-1,n-6)
     * 初始化：有1个骰子，f(1,1)=f(1,2)=f(1,3)=f(1,4)=f(1,5)=f(1,6)=1。
     */
    public static void printProbability2(int number) {
        if (number <= 0) {
            return;
        }
        double total = Math.pow(maxValue, number);
        int[][] dp = new int[number + 1][maxValue * number + 1];
        for (int i = 1; i <= maxValue; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= number; i++) {
            for (int j = i; j <= maxValue * number; j++) {
                int sum = 0;
                for (int k = 1; k < j && k <= maxValue; k++) {
                    sum += dp[i - 1][j - k];
                }
                dp[i][j] = sum;
            }
        }
        for (int i = number; i <= maxValue * number; i++) {
            System.out.println("和为" + i + "概率为: " + dp[number][i] + "/" + total);
        }
    }

    public static void main(String[] args) {
        printProbability(3);
//        printProbability2(3);
    }
}
