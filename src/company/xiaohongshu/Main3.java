package company.xiaohongshu;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/18 19:16
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long res = 0;
        for (long m = 1; m <= n; m *= 10) {
            long a = n / m;
            long b = n % m;
            res += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        System.out.println(res);
    }
}
