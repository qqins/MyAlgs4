package company.fanshi;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/9 15:56
 * 最长回文子串
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = len(s, i, i);
            int len2 = len(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        System.out.println(s.substring(start, end + 1));
    }
    private static int len(String s, int left, int right) {
        int l = left;
        int r = right;
        while ((l >= 0 && r < s.length()) && (s.charAt(l) == s.charAt(r))) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}
