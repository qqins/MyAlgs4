package swordoffer.chapter6;

/**
 * @author: Hello World
 * @date: 2018/9/4 9:47
 * <p>
 * 面试题65：不用加减乘除做加法
 * 题目：写一个函数，求两个整数之和，要求在函数体内不得使用＋、－、×、÷
 * 四则运算符号。
 */
public class Question65 {
    public static int add(int num1, int num2) {
        int sum = 0;
        int carry = 0;
        while (num2 != 0) {
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        }
        return num1;
    }

    public static void main(String[] args) {
        System.out.println(add(17,0));
    }
}
