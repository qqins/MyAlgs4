package swordoffer.chapter3;

/**
 * @author: Hello World
 * @date: 2018/8/19 20:47
 * <p>
 * 面试题19：正则表达式匹配
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示
 * 任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，
 * 匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和
 * "ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class Question19 {
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        boolean[][] dp = new boolean[str.length + 1][pattern.length + 1];
        dp[0][0] = true;
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                if (pattern[j] == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (pattern[j] == str[i]) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (pattern[j] == '*') {
                    if (pattern[j - 1] != str[i] && pattern[j - 1] != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[str.length][pattern.length];
    }
}
