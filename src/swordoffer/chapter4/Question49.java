package swordoffer.chapter4;

/**
 * @author: Hello World
 * @date: 2018/8/31 21:47
 * <p>
 * 面试题49：丑数
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，
 * 因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class Question49 {
    public int getUglyNumberSolutionByDp(int index) {
        if (index <= 6) {
            return index;
        }
        int[] dp = new int[index];
        dp[0] = 1;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        for (int i = 1; i < index; i++) {
            int n2 = dp[i2] * 2;
            int n3 = dp[i3] * 3;
            int n5 = dp[i5] * 5;
            dp[i] = Math.min(n2, Math.min(n3, n5));
            if (dp[i] == n2) {
                i2++;
            }
            if (dp[i] == n3) {
                i3++;
            }
            if (dp[i] == n5) {
                i5++;
            }
        }
        return dp[index - 1];
    }

    /**
     * 常规方法，超时
     */
    public int getUglyNumberSolution(int index) {
        if (index <= 0) {
            return 0;
        }
        int number = 0;
        int ugly = 0;
        while (ugly < index) {
            number++;
            if (isUgly(number)) {
                ugly++;
            }
        }
        return number;
    }

    private boolean isUgly(int number) {
        while (number % 2 == 0) {
            number /= 2;
        }
        while (number % 3 == 0) {
            number /= 3;
        }
        while (number % 5 == 0) {
            number /= 5;
        }
        return number == 1;
    }

    /**
     * 超级丑数
     * <p>
     * 编写一段程序来查找第 n 个超级丑数。
     * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
     * <p>
     * 示例:
     * 输入: n = 12, primes = [2,7,13,19]
     * 输出: 32
     * 解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，
     * 前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
     */
    public static int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n + 1];
        ugly[0] = 1;
        //记录每个质因数对应丑数的下标
        int[] pointer = new int[primes.length];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = 0; j < primes.length; j++) {
                if (ugly[pointer[j]] * primes[j] < min) {
                    min = ugly[pointer[j]] * primes[j];
                    minIndex = j;
                    /**
                     *若最小值不止一个，则对应的pointer都需要加1
                     * 且结果为：1,2,4,7,8,13,14,14,16,19,26,26
                     */
                } /*else if (ugly[pointer[j]] * primes[j] == min) {
                    pointer[j]++;
                }*/
            }
            ugly[i] = min;
            pointer[minIndex]++;
        }
        return ugly[n - 1];
    }

    public static void main(String[] args) {
        int[] primes = {2, 7, 13, 19};
        System.out.println(nthSuperUglyNumber(12, primes));
    }
}
