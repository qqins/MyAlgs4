package swordoffer.chapter5;

/**
 * @author: Hello World
 * @date: 2018/8/29 9:15
 * <p>
 * 面试题39：数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在
 * 数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class Question39 {
    /**
     * 由于出现的次数要大于其长度的一半
     * 当长度为偶数时，该数必然会相邻出现
     * 当长度为奇数时，若不相邻出现，则一定是交替出现，且该数出现在
     * 所有奇数位置上
     * 我们要找的数字一定是最后一个把cnt设为1的数字
     */
    public int moreThanHalfNumSolution(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int cnt = 1;
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            cnt = result == array[i] ? cnt + 1 : cnt - 1;
            if (cnt == 0) {
                result = array[i];
                cnt = 1;
            }
        }
        cnt = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == result) {
                cnt++;
            }
        }
        return cnt > array.length / 2 ? result : 0;
    }

    /**
     * 利用快排的思想
     */
    public static int moreThanHalfNumSolutionByPartition(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int mid = array.length / 2;
        int start = 0;
        int end = array.length - 1;
        int index = partition(array, start, end);
        while (index != mid) {
            if (index > mid) {
                end = index - 1;
                index = partition(array, start, end);
            } else {
                start = index + 1;
                index = partition(array, start, end);
            }
        }
        int result = array[mid];
        int times = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == result) {
                times++;
            }
        }
        if (times * 2 > array.length) {
            return result;
        } else {
            return 0;
        }
    }

    private static int partition(int[] array, int start, int end) {
        int i = start;
        int j = end + 1;
        int value = array[start];
        while (true) {
            while (i != end && array[++i] < value) {

            }
            while (j != start && array[--j] > value) {

            }
            if (i >= j) {
                break;
            }
            swap(array, i, j);
        }
        swap(array, start, j);
        return j;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(moreThanHalfNumSolutionByPartition(array));
    }
}
