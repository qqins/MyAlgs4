package swordoffer.chapter5;

/**
 * @author: Hello World
 * @date: 2018/9/2 14:23
 * <p>
 * 面试题51：数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输
 * 入一个数组，求出这个数组中的逆序对的总数 P。
 * 如{7, 5, 6, 4}, 一共存在5个逆序对, (7,6) (7,5) (7,4) (6,4) (5,4)
 */
public class Question51 {
    /**
     * 利用归并排序
     */
    private static int[] pre;
    private static int count = 0;

    public static int inversePairs(int[] array) {
        pre = new int[array.length];
        sort(array, 0, array.length - 1);
        return count;
    }

    private static void sort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(array, start, mid);
        sort(array, mid + 1, end);
        merge(array, start, mid, end);
    }

    private static void merge(int[] array, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        for (int k = start; k <= end; k++) {
            pre[k] = array[k];
        }
        for (int k = start; k <= end; k++) {
            if (i > mid) {
                array[k] = pre[j++];
            } else if (j > end) {
                array[k] = pre[i++];
            } else if (pre[i] > pre[j]) {
                array[k] = pre[j++];
                //左半部分比右半部分大，说明a[i...mid]中的值都比a[j]大
                count += mid - i + 1;
            } else {
                array[k] = pre[i++];
            }
        }
    }

    /**
     * 暴力求解
     */
    public static int inversePairsByForce(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(inversePairs(new int[]{7, 5, 6, 4}));

    }
}
