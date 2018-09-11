package company.fanshi;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/9 15:49
 * 大数相乘
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        int len1 = str1.length();
        int len2 = str2.length();

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        convert(s1, len1);
        convert(s2, len2);
        int csize = len1 + len2 + 3;
        int c[] = new int[csize];
        for (int ii = 0; ii < csize; ii++) {
            c[ii] = 0;
        }
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                int a = Integer.parseInt(String.valueOf(s1[i]));
                int b = Integer.parseInt(String.valueOf(s2[j]));
                c[i + j] += a * b;
            }
        }
        int m = 0;
        for (; m < csize; m++) {
            int carry = c[m] / 10;
            c[m] = (char) (c[m] % 10);
            if (carry > 0) {
                c[m + 1] += (char) carry;
            }
        }
        for (m = csize - 1; m >= 0; m--) {
            if (c[m] > 0) {
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n <= m; n++) {
            sb.append(c[m - n]);
        }
        System.out.println(sb.toString());
    }

    public static void convert(char[] s, int len) {
        for (int i = 0; i < len / 2; i++) {
            s[i] += s[len - i - 1];
            s[len - i - 1] = (char) (s[i] - s[len - i - 1]);
            s[i] = (char) (s[i] - s[len - i - 1]);
        }
    }
}
