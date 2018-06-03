package algorithms4.chapter2.section1.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/6/3 10:53
 */
public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        /**
         * 增量为 1, 4, 13, 40, 121, 364, 1093……
         */
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        /**
         * 若 N=16
         * 13-sort分组为: 0,13; 1,14; 2,15;
         * 4-sort分组为: 0,4,8,12;
         *              1,5,9,13;
         *              2,6,10,14;
         *              3,7,11,15.
         */
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                /**
                 * j >= h 是由于后面有 j -= h, 避免空指针异常
                 * 从后往前比较, 每次比较的是a[j], a[j-h], 尽管有 j>=h,
                 * 但每个分组中的数都比较到了
                 */
                for (int j = i; j >= h && Template.less(a[j], a[j - h]); j -= h) {
                    Template.exch(a, j, j - h);
                    Template.show(a);
                }
            }
            h = h / 3;
        }
    }

    public static void sort1(Comparable[] a) {
        int N = a.length;
        /**
         * 增量为 1, 2, 4, 8, 16……
         */
        int gap = N / 2;
        while (gap >= 1) {
            for (int i = gap; i < N; i++) {
                for (int j = i; j >= gap && Template.less(a[j], a[j - gap]); j -= gap) {
                    Template.exch(a, j, j - gap);
                }
            }
            gap = gap / 2;
        }
    }

    public static void main(String[] args) {
        Random randomInt = new Random();
        Integer[] a = new Integer[10];
        for (int i = 0; i < 10; i++) {
            a[i] = randomInt.nextInt(100);
        }
        System.out.println(Arrays.toString(a));
        sort1(a);
        System.out.println(Arrays.toString(a));
    }
}
