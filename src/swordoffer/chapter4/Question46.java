package swordoffer.chapter4;

/**
 * @author: Hello World
 * @date: 2018/8/31 9:30
 * <p>
 * 面试题46：把数字翻译成字符串(LeetCode91)
 * <p>
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * <p>
 * 示例 2:
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class Question46 {
    /**
     * 使用动态规划
     * f(i) = f(i-1) + gf(i-2)
     * 当当前字符与前一个字符组成的数字小于26, 则g=1, 否则g=0
     */
    public static int numDecodings(String s) {
        if (s.isEmpty() || s == null || s.charAt(0) == '0') {
            return 0;
        }
        int length = s.length();
        int[] dp = new int[length + 1];
        dp[0] = 1;
        dp[1] = 1;
        /**
         * s下标：  0 1 2 3
         * 字符串： 1 2 2 5
         * dp下标： 1 2 3 4
         */
        for (int i = 2; i <= length; i++) {
            if (s.charAt(i - 1) > '0') {
                dp[i] = dp[i - 1];
                if (s.charAt(i - 2) > '0' &&
                        Integer.parseInt(s.substring(i - 2, i)) <= 26) {
                    dp[i] += dp[i - 2];
                }
            } else {
                if (s.charAt(i - 1) == '0') {
                    if (s.charAt(i - 2) > '0' && s.charAt(i - 2) <= '2') {
                        dp[i] = dp[i - 2];
                    }
                }
            }
        }
        return dp[length];
    }

    public static void main(String[] args) {
        String s = "12258";
        System.out.println(numDecodings(s));
    }
}
