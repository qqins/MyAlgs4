package algorithms4.chapter2.sort;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/6/11 21:09
 * <p>
 * 三向切分的快速排序
 * 在重复元素很多的情况下，如生日，年龄，性别，薪资等
 * 此时运用三向切分的快速排序要比标准快速排序以及归并排序快
 * <p>
 * 排序类型     时间复杂度       空间复杂度   稳定性
 * 三向快排     O(n)~O(nlog₂n)   O(log₂n)    不稳定
 */
public class Quick3way {
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo;
        int i = lo + 1;
        int gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                Template.exch(a, lt++, i++);
            } else if (cmp > 0) {
                Template.exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        Random randomInt = new Random();
        Integer[] a = new Integer[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt.nextInt(5);
        }
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
