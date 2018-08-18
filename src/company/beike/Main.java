package company.beike;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/8/18 13:29
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            double result = n;
            double cur = 0;
            double pre = n;
            for (int i = 1; i < m; i++) {
                cur = Math.sqrt(pre);
                result = result + cur;
                pre = cur;
            }
            System.out.printf("%.2f", result);
        }
    }
}
