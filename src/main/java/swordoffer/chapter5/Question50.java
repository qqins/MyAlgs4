package swordoffer.chapter5;

/**
 * @author: Hello World
 * @date: 2018/9/2 14:12
 * <p>
 * 面试题50：第一个只出现一次的字符
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */
public class Question50 {
    public static int firstNotRepeatingChar(String str) {
        int[] table = new int[256];
        for (int i = 0; i < str.length(); i++) {
            table[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (table[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(firstNotRepeatingChar("abaccdeff"));
    }
}
