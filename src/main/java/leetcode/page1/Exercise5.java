package leetcode.page1;

import java.util.Objects;

/**
 * @author: Hello World
 * @date: 2018/5/23 18:43
 * <p>
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * <p>
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * <p>
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class Exercise5 {
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            //回文数为奇数时
            int len1 = expandAroundCenter(s, i, i);
            //回文数为偶数时
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            /**
             * end-start表示上一个回文数的长度
             */
            if (len > end - start) {
                /**
                 * len-1是要考虑到当回文数长度为偶数
                 * 当回文数为奇数时，len/2与（len-1）/2结果一样
                 */
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int l = left;
        int r = right;
        while ((l >= 0 && r < s.length()) && (s.charAt(l) == s.charAt(r))) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    public static void main(String[] args) {
        Exercise5 ex = new Exercise5();
        System.out.println(ex.longestPalindrome("babad"));
    }
}
