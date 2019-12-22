package company.xiaohongshu;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/18 19:00
 * 字符串压缩
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sr = scanner.nextLine();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < sr.length(); i++) {
            if (i < sr.length() - 1 && sr.charAt(i) == sr.charAt(i + 1)) {
                count++;
            } else {
                if (count != 0) {
                    sb.append(count);
                }
                sb.append(sr.charAt(i));
                count = 0;
            }
        }
        System.out.println(sb.toString());
    }
}
