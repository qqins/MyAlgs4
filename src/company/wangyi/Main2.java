package company.wangyi;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/8/18 14:45
 * <p>
 * 今天上课，老师教了小易怎么计算加法和乘法，乘法的优先级大于加法，但是如果一个运算加了括号，那么它的优先级是最高的。例如：
 * 1+2*3=7
 * 1*(2+3)=5
 * 1*2*3=6
 * (1+2)*3=9
 * 现在小易希望你帮他计算给定3个数a，b，c，在它们中间添加"+"， "*"， "("， ")"符号，能够获得的最大值。
 * <p>
 * 输入描述:
 * 一行三个数a，b，c (1 <= a, b, c <= 10)
 * <p>
 * <p>
 * 输出描述:
 * 能够获得的最大值
 * <p>
 * 输入例子1:
 * 1 2 3
 * <p>
 * 输出例子1:
 * 9
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            System.out.println(Math.max(maxNum(a + b, c), maxNum(a * b, c)));
        }
    }

    private static int maxNum(int x, int y) {
        return Math.max(x + y, x * y);
    }
}
