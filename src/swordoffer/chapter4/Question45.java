package swordoffer.chapter4;

import java.util.Arrays;

/**
 * @author: Hello World
 * @date: 2018/8/30 17:05
 * <p>
 * 面试题45：把数组排成最小的数
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最
 * 小的一个。例如输入数组 {3，32，321}，则打印出这三个数字能排成的最小数字为
 * 321323。
 */
public class Question45 {
    public String printMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        int length = numbers.length;
        String[] nums = new String[length];
        for (int i = 0; i < length; i++) {
            nums[i] = numbers[i] + "";
        }
        //若S1+S2 < S2+S1, 则S1在前, S2在后
        Arrays.sort(nums, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        String result = "";
        for (String str : nums) {
            result += str;
        }
        return result;
    }
}
