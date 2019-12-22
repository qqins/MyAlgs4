package company.shunfeng;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/15 9:25
 *
 * 对称主义者小A定义了一种“双节棍字符串”：形式如同aa…bb…cc…,其中字符a的个数等于字符c的个数，字符b的个数小于他们的个数。例如aaabccc,AACMM。给你一个字符串，输出该字符串的最长双节棍子串，若没有则输出NULL,若有多个则输出最靠前的一个。输入字符串的最长有1000000个字符。
 *
 * 输入
 * aabcccdeeea
 *
 * 输出
 * cccdeee
 *
 *
 * 样例输入
 * AAABCCCDEEE
 * 样例输出
 * AAABCCC
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int[] array = new int[256];
        for (int i = 0; i < str.length(); i++) {
            array[str.charAt(i)]++;
        }
        int start = 0;
        int count = 0;
        int end = 0;
        int max = 0;
        for (int i = 0; i < str.length(); i = i + count) {
            count = array[str.charAt(i)];
            if (i + count < str.length() && array[str.charAt(i + count)] < count) {
                int second = array[str.charAt(i + count)];
                if (i + count + second < str.length() && array[str.charAt(i + count + second)] >= count) {
                    if (count + second + count > max) {
                        max = count + second + count;
                        start = i;
                        end = i + count + second + count;
                    }
                }
            }
        }
        if (max == 0) {
            System.out.println("NULL");
        } else {
            System.out.println(str.substring(start, end));
        }
    }
}
