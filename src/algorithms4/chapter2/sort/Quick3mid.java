package algorithms4.chapter2.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/6/12 20:51
 * <p>
 * 当整个序列有序时，每次分割(partition)操作只会将待排序序列减1，此时为最坏情况，
 * 算法复杂度沦为O(n^2)
 * <p>
 * 三值取中法： 待排序序列的前(第一个位置)、中(中间位置)、后(最后一个位置)三个记录中
 * 的中间值(按大小排序)作为枢轴，比如:
 * 9 1 7 5 2 8 6 3 4
 * ↑       ↑       ↑
 * low    mid    high
 * 前      中      后
 * 由于 9 > 4 > 2； 因此将4作为此次分割(partition)操作的枢轴。
 * 三值取中操作后，整个序列变为：
 * 4 1 7 5 2 8 6 3 9
 * ↑       ↑       ↑
 * low    mid    high
 * 前      中      后
 */
public class Quick3mid {
    public void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int pivot = a[lo];
        int size = hi - lo + 1;
        if (size >= 3) {
            pivot = medianOfThree(a, lo, hi);
        }
        while (true) {
            while (a[++i] < pivot) {
                if (i == hi) {
                    break;
                }
            }
            while (pivot < a[--j]) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    private int medianOfThree(int[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (a[lo] > a[hi]) {
            swap(a, lo, hi);
        }
        if (a[mid] > a[hi]) {
            swap(a, mid, hi);
        }
        if (a[mid] > a[lo]) {
            swap(a, mid, lo);
        }
        return a[lo];
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Quick3mid quick3mid = new Quick3mid();
        Random randomInt = new Random();
        int[] a = new int[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt.nextInt(100);
        }
        System.out.println(Arrays.toString(a));
        quick3mid.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
