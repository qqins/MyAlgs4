package leetcode.other;

import java.util.Arrays;

/**
 * @author: Hello World
 * @date: 2018/9/1 15:51
 * <p>
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额
 * 所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * <p>
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 */
public class CoinChange {
    /**
     * a: 11-1=10
     * b: 11-2=9
     * c: 11-5=6
     * 求出凑出10, 9, 6,中最小的值，将该最小值加1即为所求结果
     */
    public static int coinChangeByDp(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 动态规划，从上至下
     */
    public static int coinChangeByDpTopToBottom(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChangeByDpTopToBottom(coins, amount, new int[amount]);
    }

    public static int coinChangeByDpTopToBottom(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangeByDpTopToBottom(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    /**
     * 暴力递归，时间复杂度过大
     * 将以下情况组成全排列
     * 1: 0,1,2,3,4,5,6,7,8,9,10,11
     * 2: 0,1,2,3,4,5
     * 5: 0,1,2
     */
    public static int coinChangeByForce(int[] coins, int amount) {
        return coinChangeByForce(0, coins, amount);
    }

    private static int coinChangeByForce(int idxCoin, int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (idxCoin < coins.length && amount > 0) {
            int maxVal = amount / coins[idxCoin];
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i <= maxVal; i++) {
                if (amount >= i * coins[idxCoin]) {
                    int res = coinChangeByForce(idxCoin + 1, coins,
                            amount - i * coins[idxCoin]);
                    if (res != -1) {
                        minCost = Math.min(minCost, res + i);
                    }
                }
            }
            return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(coinChangeByDpTopToBottom(coins, 11));
    }
}
