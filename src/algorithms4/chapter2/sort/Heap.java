package algorithms4.chapter2.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/6/14 19:27
 *
 * 排序类型	平均情况	    最好情况	    最坏情况	    辅助空间	  稳定性
 * 堆排序	O(nlog₂n)	O(nlog₂n)	O(nlog₂n)	O(1)	  不稳定
 */
public class Heap {
    public void sort(Comparable[] pq) {
        int n = pq.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
        }
        while (n > 1) {
            exch(pq, 1, n--);
            sink(pq, 1, n);
        }
    }

    private void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) {
                j++;
            }
            if (!less(pq, k, j)) {
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }

    /**
     * 为了便于分析，设定数组的下标从1开始
     * 所以以下的i和j都要减1
     */
    private boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private void exch(Comparable[] pq, int i, int j) {
        Comparable temp = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = temp;
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        Random randomInt = new Random();
        Integer[] a = new Integer[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt.nextInt(100);
        }
        System.out.println(Arrays.toString(a));
        heap.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
