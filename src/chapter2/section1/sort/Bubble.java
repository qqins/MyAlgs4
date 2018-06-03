package chapter2.section1.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/6/3 17:23
 * <p>
 * 排序类型	平均情况	最好情况	最坏情况	辅助空间	稳定性
 * 冒泡排序	O(n²)	O(n)	O(n²)	O(1)	稳定
 * <p>
 * 注意: 以下sort为改进版, 最好情况即有序时复杂度为O(n)
 * 而sort1为常规的, 其最好情况还是为O(n²)
 */
public class Bubble {
    public static void sort(Comparable[] a) {
        boolean changed;
        for (int i = 0; i < a.length - 1; i++) {
            changed = false;
            for (int j = 0; j < a.length - i - 1; j++) {
                if (Template.less(a[j + 1], a[j])) {
                    Template.exch(a, j + 1, j);
                    changed = true;
                }
            }
            if (!changed) {
                return;
            }
        }
    }

    public static void sort1(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (Template.less(a[j + 1], a[j])) {
                    Template.exch(a, j + 1, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        Random randomInt = new Random();
        Integer[] a = new Integer[10000];
        Integer[] b=new Integer[10000];
        for (int i = 0; i < 10000; i++) {
            a[i] = randomInt.nextInt(10000);
        }
        for (int i = 0; i < 10000; i++) {
            b[i]=i;
        }
        long start1=System.currentTimeMillis();
        System.out.println(Arrays.toString(a));
        sort1(a);
        Template.show(a);
        System.out.println(System.currentTimeMillis()-start1);

        long start2=System.currentTimeMillis();
        System.out.println(Arrays.toString(b));
        sort(b);
        Template.show(b);
        System.out.println(System.currentTimeMillis()-start2);
    }
}
