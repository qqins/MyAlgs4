package algorithms4.chapter2;

import algorithms4.chapter2.sort.Quick;
import algorithms4.chapter2.sort.Template;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/6/19 20:43
 * <p>
 * 找到一组数中第k小的元素
 * 利用快速排序的原理，其中j的左边都比j小
 */
public class SelectKthSmall {
    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int size = hi - lo + 1;
        Comparable v = a[lo];
        if (size >= 3) {
            v = mediaOfThree(a, lo, hi);
        }
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

    private Comparable mediaOfThree(Comparable[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (Template.less(a[hi], a[lo])) {
            Template.exch(a, lo, hi);
        }
        if (Template.less(a[hi], a[mid])) {
            Template.exch(a, hi, mid);
        }
        if (Template.less(a[lo], a[mid])) {
            Template.exch(a, lo, mid);
        }
        return a[lo];
    }

    public Comparable select(Comparable[] a, int k) {
        k = k - 1;
        int lo = 0;
        int hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j == k) {
                return a[k];
            } else if (j > k) {
                hi = j - 1;
            } else if (j < k) {
                lo = j + 1;
            }
        }
        /**
         * 之所以返回a[k], 在while中，最后只剩下两个数比较，那么所需的
         * a[k]必定在这两个数之间，若不是a[j], 那么就肯定为a[k]
         */
        return a[k];
    }

    public static void main(String[] args) {
        SelectKthSmall sks = new SelectKthSmall();
        Random randomInt = new Random();
        Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt.nextInt(10);
        }
        System.out.println(Arrays.toString(a));
        System.out.println(sks.select(a, 3));
        Quick.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
