package swordoffer.chapter6;

/**
 * @author: Hello World
 * @date: 2018/9/4 9:37
 * <p>
 * 面试题64：求1+2+…+n
 * 题目：求1+2+…+n，要求不能使用乘除法、for、while、if、else、switch、case
 * 等关键字及条件判断语句（A?B:C）。
 */
public class Question64 {
    public static int sumSolution(int n) {
        int sum = n;
        boolean b = (n > 0) && ((sum += sumSolution(n - 1)) > 0);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sumSolution(6));
    }
}
