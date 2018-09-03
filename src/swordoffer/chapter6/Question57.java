package swordoffer.chapter6;

import java.util.ArrayList;

/**
 * @author: Hello World
 * @date: 2018/9/3 9:57
 * <p>
 * 面试题57（一）：和为s的两个数字
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 */
public class Question57 {
    public static ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        int first = 0;
        int second = array.length - 1;
        while (first < second) {
            int curSum = array[first] + array[second];
            if (curSum == sum) {
                list.add(array[first]);
                list.add(array[second]);
                return list;
            } else if (curSum > sum) {
                second--;
            } else {
                first++;
            }
        }
        return list;
    }

    /**
     * 面试题57（二）：为s的连续正数序列
     * 题目：输入一个正数s，打印出所有和为s的连续正数序列（至少含有两个数）。
     * 例如输入15，由于1+2+3+4+5=4+5+6=7+8=15，所以结果打印出3个连续序列1～5、
     * 4～6和7～8。
     */
    public ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (sum < 3) {
            return result;
        }
        int start = 1;
        int end = 2;
        int curSum = 3;
        while (start < end) {
            if (curSum > sum) {
                curSum -= start;
                start++;
            } else if (curSum < sum) {
                end++;
                curSum += end;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    list.add(i);
                }
                result.add(list);
                curSum -= start;
                start++;
                end++;
                curSum += end;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 4, 7, 11, 15};
        ArrayList<Integer> list = findNumbersWithSum(array, 15);
        System.out.println(list);
    }
}
