package swordoffer.chapter4;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: Hello World
 * @date: 2018/8/28 21:55
 * <p>
 * 面试题38：字符串的排列
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串
 * abc,acb,bac,bca,cab和cba。
 */
public class Question38 {
    private static ArrayList<String> result = new ArrayList<>();

    public static ArrayList<String> Permutation(String str) {
        if (str.length() == 0) {
            return result;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        back(chars, new boolean[chars.length], new StringBuilder());
        return result;
    }

    private static void back(char[] chars, boolean[] hasUsed, StringBuilder stringBuilder) {
        if (stringBuilder.length() == chars.length) {
            result.add(stringBuilder.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (hasUsed[i]) {
                continue;
            }
            //避免重复, 若不加，当字符串为"aa", 结果为"aa","aa"
            if (i != 0 && chars[i] == chars[i - 1] && !hasUsed[i - 1]) {
                continue;
            }
            hasUsed[i] = true;
            stringBuilder.append(chars[i]);
            back(chars, hasUsed, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            hasUsed[i] = false;
        }
    }

    public static void main(String[] args) {
        String str = "aazz";
        ArrayList<String> re = Permutation(str);
        System.out.println(re);
    }
}
