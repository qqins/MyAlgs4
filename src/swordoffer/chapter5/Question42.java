package swordoffer.chapter5;

/**
 * @author: Hello World
 * @date: 2018/8/30 10:41
 * <p>
 * 面试题42：连续子数组的最大和
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数
 * 组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n).
 */
public class Question42 {
    public static int findGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int result = Integer.MIN_VALUE;
        int sum = 0;
        for (int val : array) {
            sum = sum <= 0 ? val : sum + val;
            result = Math.max(sum, result);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {-14, -2, 3, -4, -5};
        System.out.println(findGreatestSumOfSubArray(a));
    }
}
