package chapter2.section1.example;

import java.util.Arrays;

/**
 * @author: Hello World
 * @date: 2018/5/23 20:55
 */
public class Selection {
    /**
     * 基本数据类型都实现了Comparable接口
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
//            if (min != i) {
                exch(a, i, min);
//            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Integer[] a = {8, 2, 5, 2, 9, 0, 3, 7};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
