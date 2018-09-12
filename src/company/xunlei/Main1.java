package company.xunlei;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/12 18:30
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println(getNum(num));
    }

    private static int getNum(int num) {
        int res = 0;
        for (int i = 1; i < num; i++) {
            for (int j = i; j < num; j++) {
                for (int k = j + 1; k < num; k++) {
                    if (i * i + j * j == k * k) {
                        int gcd = maxGcd(i, j, k);
                        if (gcd == 1) {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }

    private static int maxGcd(int a, int b, int c) {
        int d = maxGcd2(a, b);
        int i = c % d;
        int maxGcd = 1;
        if (i == 0) {
            maxGcd = d;
        } else {
            c = maxGcd2(a, b);
            d = i;
            maxGcd = maxGcd2(c, d);
        }
        return maxGcd;
    }

    private static int maxGcd2(int a, int b) {
        int i = a % b;
        int maxGcd = 1;
        if (i == 0) {
            maxGcd = b;
        } else {
            a = b;
            b = i;
            maxGcd = maxGcd2(a, b);
        }
        return maxGcd;
    }
}
