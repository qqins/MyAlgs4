package dsaaaij3.chapter7.one;

import java.util.Arrays;

/**
 * @author qin
 * @date 2019/11/17 10:14
 */
public class StringSortMsd {
    private static final int BUCKET = 256;
    private static final int CUTOFF = 0;
    private static String[] temp;

    public static void sort(String[] arr) {
        int n = arr.length;
        temp = new String[n];
        sort(arr, 0, n - 1, 0);
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

        int[] count = new int[BUCKET + 2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(arr[i], d);
            count[c + 2]++;
        }

        for (int r = 0; r < BUCKET + 1; r++) {
            count[r + 1] += count[r];
        }

        for (int i = lo; i <= hi; i++) {
            int c = charAt(arr[i], d);
            temp[count[c + 1]++] = arr[i];
        }

        for (int i = lo; i <= hi; i++) {
            arr[i] = temp[i - lo];
        }

        for (int r = 0; r < BUCKET; r++) {
            sort(arr, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    private static void insertion(String[] arr, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(arr[j], arr[j - 1], d); j--) {
                exchange(arr, j, j - 1);
            }
        }
    }

    private static void exchange(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
