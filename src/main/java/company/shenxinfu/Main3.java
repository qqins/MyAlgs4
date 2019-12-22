package company.shenxinfu;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/21 20:09
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (a == b || (a > 5 && b < 5) || (a < 5 && b > 5) || (a >= 5 && b >= 5 && Math.abs(a - b) > 1)
                || (a <= 5 && b <= 5 && Math.abs(a - b) > 3)) {
            System.out.println("invalid");
        } else {
            if (a <= 5 && b <= 5) {
                if (a > b) {
                    if (a - b >= 3) {
                        System.out.println("A" + a);
                    } else {
                        System.out.println("A5");
                    }
                } else {
                    if (b - a >= 3) {
                        System.out.println("B" + b);
                    } else {
                        System.out.println("B5");
                    }
                }
            } else {
                if (a > b) {
                    System.out.println("A" + a);
                } else {
                    System.out.println("B" + b);
                }
            }
        }
    }
}
