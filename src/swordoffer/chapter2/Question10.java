package swordoffer.chapter2;

/**
 * @author: Hello World
 * @date: 2018/8/12 12:34
 * <p>
 * 面试题10.1: 斐波那契数列
 * 大家都知道斐波那契数列，现在要求输入一个整数n，
 * 请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * <p>
 * 输入10, 返回55. 输入5000(递归可能出错), 返回12502500
 */
public class Question10 {
    public static int Fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        int first = 0;
        int second = 1;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    public static int FibonacciByRe(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return FibonacciByRe(n - 1) + FibonacciByRe(n - 2);
    }

    /**
     * 题10.2: 青蛙跳台阶问题
     * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多
     * 少种跳法。
     */
    public static int JumpFloor(int target) {
        if (target < 2) {
            return target;
        }
        int first = 1;
        int second = 1;
        int result = 0;
        for (int i = 2; i <= target; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    /**
     * 题10.3: 变态跳台阶
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
     */
    public int JumpFloorII(int target) {
        if (target < 2) {
            return target;
        }
        int result = 1;
        for (int i = 2; i <= target; i++) {
            result = 2 * result;
        }
        return result;
        /**
         * if (target < 2) {
         *     return target;
         * }
         * return 1 << target - 1;
         */
    }

    /**
     * 题10.4：矩形覆盖
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
     * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     */
    public int RectCover(int target) {
        if (target < 2) {
            return target;
        }
        int first = 1;
        int second = 1;
        int result = 0;
        for (int i = 2; i <= target; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Fibonacci(10));
    }
}
