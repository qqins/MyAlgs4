package swordoffer.chapter3;

import java.util.Arrays;

/**
 * @author: Hello World
 * @date: 2018/8/19 22:01
 * <p>
 * 面试题21：调整数组顺序使奇数位于偶数前面
 * <p>
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class Question21 {
    /**
     * 书中的解法，但是相对位置变了
     */
    public static void reOrderArray(int[] array) {
        if (array == null || array.length == 1) {
            return;
        }
        int first = 0;
        int second = array.length - 1;
        while (first < second) {
            while (first < second && array[first] % 2 != 0) {
                first++;
            }
            while (first < second && array[second] % 2 == 0) {
                second--;
            }
            if (first < second) {
                int temp = array[first];
                array[first] = array[second];
                array[second] = temp;
            }
        }
    }

    public static void reOrderArray2(int[] array) {
        //奇数位置
        int first = 0;
        //偶数位置
        int second = 0;
        int[] temp = Arrays.copyOf(array, array.length);
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                second++;
            }
        }
        //上面得到的是偶数的个数, 那么偶数的位置就是数组的长度减去偶数的个数
        second = array.length - second;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] % 2 != 0) {
                array[first++] = temp[i];
            } else if (temp[i] % 2 == 0) {
                array[second++] = temp[i];
            }
        }
    }

    /**
     * 利用插入排序思想
     */
    public static void reOrderArray3(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                int target = array[i];
                int j = i;
                while (j >= 1 && array[j - 1] % 2 == 0) {
                    array[j] = array[j - 1];
                    j--;
                }
                array[j] = target;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        reOrderArray2(a);
        System.out.println(Arrays.toString(a));
    }
}
