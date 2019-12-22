package dsaaaij3.chapter7.one;

import java.util.Arrays;

/**
 * @author qin
 * @date 2019/11/17 15:35
 */
public class StringSortQuick {
    private static final int CUTOFF = 0;

    public static void sort(String[] arr) {
        sort(arr, 0, arr.length - 1, 0);
    }

    private static int charAt(String s, int d) {
        if (d == s.length()) {
            return -1;
        }
        return s.charAt(d);
    }

    private static void sort(String[] arr, int lo, int hi, int d) {
        if (hi <= lo + CUTOFF) {
            insertion(arr, lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        int v = charAt(arr[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(arr[i], d);
            if (t < v) {
                exchange(arr, lt++, i++);
            } else if (t > v) {
                exchange(arr, i, gt--);
            } else {
                i++;
            }
        }

        sort(arr, lo, lt - 1, d);
        if (v >= 0) {
            sort(arr, lt, gt, d + 1);
        }
        sort(arr, gt + 1, hi, d);
    }

    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
                exchange(a, j, j - 1);
            }
        }
    }

    private static void exchange(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(String v, String w, int d) {
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) {
                return true;
            }
            if (v.charAt(i) > w.charAt(i)) {
                return false;
            }
        }
        return v.length() < w.length();
    }

    public static void main(String[] args) {
        String[] strings = {"she", "sells", "seashells", "by", "the", "seashore", "the",
                "shells", "she", "sells", "are", "surely", "seashells"};
        System.out.println(Arrays.toString(strings));
        sort(strings);
        System.out.println(Arrays.toString(strings));
    }
}
