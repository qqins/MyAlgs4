package swordoffer.chapter6;

/**
 * @author: Hello World
 * @date: 2018/9/3 10:49
 * <p>
 * 面试题58（一）：翻转单词顺序
 * 题目：输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 * 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
 * 则输出"student. a am I"。
 */
public class Question58 {
    public static String reverseSentence(String str) {
        if (str == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        reverse(chars, 0, chars.length - 1);
        int pre = 0;
        for (int i = 0; i <= chars.length; i++) {
            if (i == chars.length || chars[i] == ' ') {
                reverse(chars, pre, i - 1);
                pre = i + 1;
            }
        }
        return String.valueOf(chars);
    }

    private static void reverse(char[] c, int i, int j) {
        while (i < j) {
            char temp = c[i];
            c[i] = c[j];
            c[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * 面试题58（二）：左旋转字符串
     * 题目：字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
     * 请定义一个函数实现字符串左旋转操作的功能。比如输入字符串"abcdefg"和数
     * 字2，该函数将返回左旋转2位得到的结果"cdefgab"。
     */
    public static String leftRotateString(String str, int n) {
        if (str == null || n > str.length() || n < 0) {
            return "";
        }
        char[] chars = str.toCharArray();
        reverse(chars, 0, n - 1);
        reverse(chars, n, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        return String.valueOf(chars);
    }

    public static String leftRotateString1(String str, int n) {
        if (str == null || n < 0 || n > str.length()) {
            return "";
        }
        return str.substring(n) + str.substring(0, n);
    }

    public static void main(String[] args) {
        System.out.println(leftRotateString("abcdefg", 2));
    }
}
