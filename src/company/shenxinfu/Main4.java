package company.shenxinfu;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/21 20:40
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int road = scanner.nextInt();
        int straight = scanner.nextInt();
        int bent = scanner.nextInt();
        if (road < 128 || road > 255) {
            System.out.println("error");
        } else {
            int num1 = numberOf1(road);
            int num0 = 8 - num1;
            int ae = 130 * num1 + 90 * num0;
            int other = straight * num1 + bent * num0;
            if (ae > other) {
                System.out.println("lose");
            } else if (ae < other) {
                System.out.println("win");
            } else {
                System.out.println("deuce");
            }
        }
    }

    public static int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n - 1) & n;
        }
        return count;
    }
}
