package algorithms4.chapter2.sort;

import java.util.*;

/**
 * @author: Hello World
 * @date: 2018/6/11 16:52
 * <p>
 * 排序类型	平均情况	    最好情况	    最坏情况	    辅助空间	        稳定性
 * 快速排序	O(nlog₂n)	O(nlog₂n)	O(n²)	    O(log₂n)~O(n)	不稳定
 */
public class Quick {
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (Template.less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (Template.less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            Template.exch(a, i, j);
        }
        Template.exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static void main(String[] args) {
        Random randomInt = new Random();
        Integer[] a = new Integer[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt.nextInt(100);
        }
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
