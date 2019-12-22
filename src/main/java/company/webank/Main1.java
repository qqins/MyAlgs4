package company.webank;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/18 15:36
 *
 * 阳阳突发奇想画起了三角！
 *
 * 1/1(1)  1/2(2)  1/3(6)  1/4(7) ...
 *
 * 2/1(3)  2/2(5)  2/3(8) ...
 *
 * 3/1(4)  3/2(9) ...
 *
 * 4/1(10) ...
 *
 * ...
 *
 * 并且在三角每个位置填上了一个数字（即分数后括号中的数字），然后按照z字型给数字了一个编号，如上图所示。
 *
 * 现在阳阳想知道编号为N的数字是多少，希望你能够告诉他。
 *
 * 1≤N≤10000000
 *
 * 输入
 * 一个整数N
 *
 * 输出
 * 一个答案
 *
 *
 * 样例输入
 * 7
 * 样例输出
 * 1/4
 *
 * Hint
 * 输入7，即代表编号7，如题干中所示，编号7对应的数字为1/4
 */
public class Main1 {
    private static int start = 0;
    private static int end = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int first = 1;
        int second = 1;
        int index = index(n);
        if (index % 2 == 0) {
            for (int i = 1, j = index; i <= index && j >= 1; i++, j--) {
                if (n <= end && n == start) {
                    first = i;
                    second = j;
                    break;
                } else {
                    start++;
                }
            }
        } else {
            for (int i = index, j = 1; i >= 1 && j <= index; i--, j++) {
                if (n <= end && n == start) {
                    first = i;
                    second = j;
                    break;
                } else {
                    start++;
                }
            }
        }
        System.out.println(first + "/" + second);
    }

    private static int index(int n) {
        for (int i = 1; i < n; i++) {
            int x = factorial(i);
            int y = factorial(i + 1);
            if (x < n && y >= n) {
                start = x + 1;
                end = y;
                return i + 1;
            }
        }
        return -1;
    }

    private static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return factorial(n - 1) + n;
    }
}
