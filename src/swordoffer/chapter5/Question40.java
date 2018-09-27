package swordoffer.chapter5;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author: Hello World
 * @date: 2018/8/29 20:45
 * <p>
 * 面试题40：最小的k个数
 * <p>
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，
 * 则最小的4个数字是1,2,3,4,。
 */
public class Question40 {
    /**
     * 最大堆(左右子树值都比自己小)，解决 topN大的问题
     * 最小堆(左右子树值都比自己大)，解决 topN小的问题
     * 利用java中PriorityQueue队列实现堆
     */
    public static ArrayList<Integer> getLeastNumbersSolution(int[] input, int k) {
        if (input == null || input.length == 0 || k > input.length || k <= 0) {
            return new ArrayList<>();
        }
        /**
         * 看源码可以知道，o1代表待插入的元素，o2代表现在堆中的根元素
         * 当o1 > o2时，o2-o1<0, 看源码可知，当其结果小于0时，才将其
         * 插入堆中的根节点
         * ps：默认的从小到大的顺序，写成o2-o1也就是从大到小了
         *
         * PriorityQueue对元素采用的是堆排序，头是按指定排序方式的最大(小)元素。
         * 堆排序只能保证根是最大（最小），整个堆并不是有序的。每次调用
         * offer或poll都会重排序
         */
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                (o1, o2) -> o2 - o1);
        for (int num : input) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return new ArrayList<>(maxHeap);
    }

    /**
     * 利用快排的思想
     */
    public ArrayList<Integer> getLeastNumbersSolutionByPartition(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k <= 0) {
            return result;
        }
        int start = 0;
        int end = input.length - 1;
        int index = partition(input, start, end);
        while (index != k - 1) {
            if (index < k - 1) {
                start = index + 1;
                index = partition(input, start, end);
            } else {
                end = index - 1;
                index = partition(input, start, end);
            }
        }
        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;
    }

    private int partition(int[] input, int start, int end) {
        int i = start;
        int j = end + 1;
        int value = input[start];
        while (true) {
            while (i != end && input[++i] < value) {

            }
            while (j != start && input[--j] > value) {

            }
            if (i >= j) {
                break;
            }
            swap(input, i, j);
        }
        swap(input, start, j);
        return j;
    }

    private void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    /**
     * 自己构建堆
     */
    public static ArrayList<Integer> getLeastNumbers(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k <= 0) {
            return res;
        }
        int[] temp = new int[k];
        for (int i = 0; i < input.length; i++) {
            if (i < k) {
                temp[i] = input[i];
            } else {
                for (int j = temp.length / 2; j >= 1; j--) {
                    sink(temp, j, k);
                }
                if (temp[0] > input[i]) {
                    temp[0] = input[i];
                }
            }
        }
        for (int i = 0; i < k; i++) {
            res.add(temp[i]);
        }
        return res;
    }

    private static void sink(int[] array, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < array.length && less(array, j, j + 1)) {
                j++;
            }
            if (!less(array, k, j)) {
                break;
            }
            swapForHeap(array, k, j);
            k = j;
        }
    }

    private static void swapForHeap(int[] array, int i, int j) {
        int temp = array[i - 1];
        array[i - 1] = array[j - 1];
        array[j - 1] = temp;
    }

    private static boolean less(int[] array, int i, int j) {
        return array[i - 1] < array[j - 1];
    }

    public static void main(String[] args) {
        int[] input = {4, 5, 1, 6, 2, 7, 3, 8};
        ArrayList<Integer> res = getLeastNumbers(input, 4);
        System.out.println(res);
    }
}
