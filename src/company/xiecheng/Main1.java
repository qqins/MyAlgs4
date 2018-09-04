package company.xiecheng;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/4 19:28
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long num = scanner.nextLong();
        int result = 0;
        while (num != 0) {
            result++;
            num = (num - 1) & num;
        }
        System.out.println(result);
    }
}
