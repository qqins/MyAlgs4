package chapter2.section1.sort;


import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/5/23 21:38
 * <p>
 * 排序类型	平均情况	最好情况	最坏情况	辅助空间	稳定性
 * 插入排序	O(n²)	O(n)	O(n²)	O(1)	稳定
 */
public class Insertion {
    /**
     * 通过运行时间对比，sort1由于sort2
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && (less(a[j], a[j - 1])); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void sort1(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            /**
             * 将比较条件a[j] < a[j - 1]写在循环体里
             * 当左边比右边大时，直接结束内循环，避免进行j次比较
             * 从而当最好的情况即数组本来有序，这样就只比较N-1次，交换0次
             */
            for (int j = i + 1; j > 0 && (a[j] < a[j - 1]); j--) {
                int t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
            }
        }
    }

    public static void sort2(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            /**
             * 将比较条件写在循环内，无论左边是否大于右边，都要进行j次比较
             */
            for (int j = i + 1; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    int t = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = t;
                }
            }
        }
    }

    private static boolean less(Comparable w, Comparable v) {
        return w.compareTo(v) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[10000];
        Integer[] b = new Integer[10000];
        Random randomInt=new Random();
        for (int i = 0; i < a.length; i++) {
            b[i] = randomInt.nextInt(10000);
        }
        for (int i = 0; i < a.length; i++) {
            a[i]=i;
        }
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println("插入排序所用时间为：");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(Arrays.toString(a));

        long start1 = System.currentTimeMillis();
        System.out.println(Arrays.toString(b));
        Selection.sort(b);
        System.out.println("选择排序所用时间为：");
        System.out.println(System.currentTimeMillis() - start1);
        System.out.println(Arrays.toString(b));

    }
}
