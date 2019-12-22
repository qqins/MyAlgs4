package dsaaaij3.chapter7.quick;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/12/23 18:00
 */
public class Quick<AnyType extends Comparable<? super AnyType>> {
    public void sort(AnyType[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(AnyType[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition1(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private int partition(AnyType[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        AnyType pivot = a[lo];
        int size = hi - lo + 1;
        if (size >= 3) {
            pivot = medianOfThree(a, lo, hi);
        }
        while (true) {
            while (a[++i].compareTo(pivot) < 0) {
                if (i == hi) {
                    break;
                }
            }
            while (pivot.compareTo(a[--j]) < 0) {
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

    private int partition1(AnyType[] a, int lo, int hi) {
        AnyType pivot = a[lo];
        int size = hi - lo + 1;
        if (size >= 3) {
            pivot = medianOfThree(a, lo, hi);
        }

        int left = lo;
        for (int i = lo + 1; i <= hi; i++) {
            if (a[i].compareTo(pivot) < 0) {
                swap(a, ++left, i);
            }
        }
        swap(a, left, lo);

        return left;
    }

    private AnyType medianOfThree(AnyType[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (a[lo].compareTo(a[hi]) > 0) {
            swap(a, lo, hi);
        }
        if (a[mid].compareTo(a[hi]) > 0) {
            swap(a, mid, hi);
        }
        if (a[mid].compareTo(a[lo]) > 0) {
            swap(a, mid, lo);
        }
        return a[lo];
    }

    private void swap(AnyType[] a, int i, int j) {
        AnyType temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Quick quick = new Quick();
        Random randomInt = new Random();
        Integer[] a = new Integer[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt.nextInt(100);
        }
        System.out.println(Arrays.toString(a));
        quick.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
