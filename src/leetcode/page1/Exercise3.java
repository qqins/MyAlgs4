package leetcode.page1;


import javax.swing.text.AbstractDocument;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: Hello World
 * @date: 2018/5/20 14:02
 *
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 *
 * 示例：
 *
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 *
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 *
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，
 * "pwke" 是 子序列  而不是子串。
 */
public class Exercise3 {
    //超出时间限制，自己写的
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length() + 1; j++) {
                boolean flag = true;
                String str = s.substring(i, j);
                char[] chars = str.toCharArray();
                for (int k = 0; k < chars.length; k++) {
                    if (str.indexOf(chars[k]) != k) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    if (max < str.length()) {
                        max = str.length();
                    }
                } else {
                    break;
                }
            }
        }
        return max;
    }

    //超出时间限制
    public static int lengthOfLongestSubstring1(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length() + 1; j++) {
                boolean flag = true;
                Set<Character> set = new HashSet<>();
                for (int k = i; k < j; k++) {
                    if (set.contains(s.charAt(k))) {
                        flag = false;
                        break;
                    }
                    set.add(s.charAt(k));
                }
                if (flag) {
                    if (max < set.size()) {
                        max = set.size();
                    }
                } else {
                    break;
                }
            }
        }
        return max;
    }

    /**
     * 测试通过，检测[i,j]范围内的字符有无重复
     * 利用set的不可重复性，判断j位置的字符是否重复，若重复，则从i到j后面的
     * 子字符串都不用检测了，否则，就将j位置的字符串添加进set
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<>();
        int max = 0;
        int i = 0;
        int j = 0;
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                max = Math.max(max, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return max;
    }

    public static int lengthOfLongestSubstring3(String s) {
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            max = Math.max(max, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return max;
    }

    /**
     * 其中i表示子字符串的开始，j表示结束，一旦遇到重复字符，
     * 就从下一个子字符串开始计数
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring4(String s) {
        int max = 0;
        int[] index = new int[128];
        for (int i = 0, j = 0; j < s.length(); j++) {
            /**
             * 若写成i=index[s.charAt(j)];
             * 则当遇到一个新字符时，i=0，就又从头开始计数了
             * 按上述所写，pwwkew结果为5而不是3
             */
            i = Math.max(index[s.charAt(j)], i);
            /**
             * 之所以都要+1,避免当s为单字符时，如s="q",若不+1,结果为0
             */
            max = Math.max(max, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return max;
    }

    public static void main(String[] args) {
        String str = "pwwkew";
        System.out.println(lengthOfLongestSubstring4(str));
    }
}
