package dsaaaij3.chapter2;

import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/10/7 18:20
 * <p>
 * 最大子序列和问题
 * 给定一系列整数(有正有负), 求最大的子序列和, 若均为负数, 则输出0
 * 如：-2, 11, -4, 13, -5, -2
 * 其最大子序列和为20, 从a1~a3
 */
public class MaxSumTest {
    private static int seqStart = 0;
    private static int seqEnd = -1;

    /**
     * 时间复杂度：O(N^3)
     */
    public static int maxSubSum1(int[] a) {
        /**
         *  若要求全为负数时输出其最大值，而不是0
         *  则 maxSum = Integer.MIN_VALUE
         */
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int thisSum = 0;
                for (int k = i; k <= j; k++) {
                    thisSum += a[k];
                }
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                    seqStart = i;
                    seqEnd = j;
                }
            }
        }
        return maxSum;
    }

    /**
     * 时间复杂度 O(N^2)
     */
    public static int maxSubSum2(int[] a) {
        /**
         *  若要求全为负数时输出其最大值，而不是0
         *  则 maxSum = Integer.MIN_VALUE
         */
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                    seqStart = i;
                    seqEnd = j;
                }
            }
        }
        return maxSum;
    }

    /**
     * 时间复杂度O(NlogN)
     */
    public static int maxSubSum3(int[] a) {
        return a.length > 0 ? maxSumRec(a, 0, a.length - 1) : 0;
    }

    private static int maxSumRec(int[] a, int left, int right) {
        /**
         *  若要求全为负数时输出其最大值，而不是0
         *  则 if (left == right) {
         *             return a[left];
         *      }
         *
         *   maxLeftBorderSum = Integer.MIN_VALUE
         *   maxRightBorderSum = Integer.MIN_VALUE
         */
        if (left == right) {
            if (a[left] > 0) {
                return a[left];
            } else {
                return 0;
            }
        }

        int center = (left + right) / 2;
        int maxLeftSum = maxSumRec(a, left, center);
        int maxRightSum = maxSumRec(a, center + 1, right);

        int maxLeftBorderSum = 0;
        int leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        int maxRightBorderSum = 0;
        int rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += a[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }

        return max3(maxLeftSum, maxRightSum,
                maxLeftBorderSum + maxRightBorderSum);
    }

    private static int max3(int a, int b, int c) {
        return a > b ? a > c ? a : c : b > c ? b : c;
    }

    /**
     * 时间复杂度 O(N)
     */
    public static int maxSubSum4(int[] a) {
        int maxSum = 0;
        int thisSum = 0;

        for (int i = 0, j = 0; j < a.length; j++) {
            thisSum += a[j];

            if (thisSum > maxSum) {
                maxSum = thisSum;
                seqStart = i;
                seqEnd = j;
            } else if (thisSum < 0) {
                i = j + 1;
                thisSum = 0;
            }
        }

        return maxSum;
    }

    /**
     * 第四种方法的升级版(可以参考 剑指offer——42)
     * 若要求全为负数时输出其最大值，而不是0
     */
    public static int maxSubSum4Pro(int[] a) {
        int maxSum = Integer.MIN_VALUE;
        int thisSum = 0;
        for (int val : a) {
            if (thisSum <= 0) {
                thisSum = val;
            } else {
                thisSum += val;
            }
            if (thisSum > maxSum) {
                maxSum = thisSum;
            }
        }
        return maxSum;
    }

    public static void getTimingInfo(int n, int alg) {
        int[] test = new int[n];
        Random rand = new Random();

        long startTime = System.currentTimeMillis();
        long totalTime = 0;

        int i;
        for (i = 0; totalTime < 4000; i++) {
            for (int j = 0; j < test.length; j++) {
                test[j] = rand.nextInt(100) - 50;
            }
            switch (alg) {
                case 1:
                    maxSubSum1(test);
                    break;
                case 2:
                    maxSubSum2(test);
                    break;
                case 3:
                    maxSubSum3(test);
                    break;
                case 4:
                    maxSubSum4(test);
                    break;
                default:
            }

            totalTime = System.currentTimeMillis() - startTime;
        }
        System.out.print(String.format("\t%12.6f",
                (totalTime * 1000 / i) / (double) 1000000));
    }

    public static void main(String[] args) {
        int[] a = {4, -3, 5, -2, -1, 2, 6, -2};
        int maxSum;

        /*maxSum = maxSubSum1(a);
        System.out.println("Max sum is " + maxSum + "; it goes"
                + " from " + seqStart + " to " + seqEnd);

        maxSum = maxSubSum2(a);
        System.out.println("Max sum is " + maxSum + "; it goes"
                + " from " + seqStart + " to " + seqEnd);

        maxSum = maxSubSum3(a);
        System.out.println("Max sum is " + maxSum + "; it goes"
                + " from " + seqStart + " to " + seqEnd);

        maxSum = maxSubSum4(a);
        System.out.println("Max sum is " + maxSum + "; it goes"
                + " from " + seqStart + " to " + seqEnd);*/

        for (int n = 100; n <= 1000000; n *= 10) {
            System.out.print(String.format("N = %7d", n));

            for (int alg = 1; alg <= 4; alg++) {
                if ((alg == 1 && n > 50000) || (alg == 2 && n > 500000)) {
                    System.out.print("\t      NA    ");
                    continue;
                }
                getTimingInfo(n, alg);
            }
            System.out.println();
        }
    }
}
