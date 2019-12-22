package swordoffer.chapter3;

/**
 * @author: Hello World
 * @date: 2018/8/16 21:57
 * <p>
 * 面试题16：数值的整数次方
 * <p>
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class Question16 {
    public static double Power(double base, int exponent) {
        int n = Math.abs(exponent);
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return base;
        }
        double result = Power(base, n >> 1);
        result *= result;
        if ((n & 1) == 1) {
            result *= base;
        }
        return exponent < 0 ? 1 / result : result;
    }

    public static void main(String[] args) {
        System.out.println(Power(2, 5));
    }
}
