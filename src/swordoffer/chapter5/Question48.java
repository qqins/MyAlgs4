package swordoffer.chapter5;

/**
 * @author: Hello World
 * @date: 2018/8/31 20:32
 * <p>
 * 面试题47：最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该
 * 最长子字符串的长度。假设字符串中只包含 'a'~'z'的字符。例如，
 * 在字符串“arabcacfr”中，最长的不含重复字符的子字符串时“acfr”，
 * 长度是4
 */
public class Question48 {
    public static int longestSubStringWithoutDuplication(String s) {
        int[] index = new int[26];
        int max = 0;
        int preIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            preIndex = Math.max(index[s.charAt(i) - 'a'], preIndex);
            max = Math.max(max, i - preIndex + 1);
            index[s.charAt(i) - 'a'] = i + 1;
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "arabcacfr";
        System.out.println(longestSubStringWithoutDuplication(s));
    }
}
