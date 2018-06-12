package algorithms4.chapter2.sort;


import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/6/10 20:25
 * <p>
 * 排序类型	平均情况	    最好情况	    最坏情况	    辅助空间	稳定性
 * 归并排序	O(nlog₂n)	O(nlog₂n)	O(nlog₂n)	O(n)	稳定
 */
public class Merge {
    private static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        /**
         * i从左半部分开始
         * j从右半部分开始
         */
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        /**
         * 左右两半部分各自有序
         * 若左半部分用尽，则直接取右半部分元素
         * 若右半部分用尽，则直接去左半部分元素
         * 若右半部分当前元素小于左半部分当前元素，则取右半部分元素
         * 若右半部分当前元素大于左半部分当前元素，则取左半部分元素
         */
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (Template.less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    /**
     * 自顶向下
     */
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        /**
         * 因为左右部分各自有序
         * 则只需a[mid]>a[mid+1], 才进行排序
         */
        if (Template.less(a[mid + 1], a[mid])) {
            merge(a, lo, mid, hi);
        }
    }

    public static void main(String[] args) {
        Random randomInt = new Random();
        Integer[] a = new Integer[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt.nextInt(100);
        }
        System.out.println(Arrays.toString(a));
        sortBU(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 自底向上
     * 比较适合用链表组织的数据
     */
    public static void sortBU(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz *= 2) {
            for (int lo = 0; lo < N - sz; lo += 2 * sz) {
                /**
                 * mid=lo+((lo+sz+sz-1)-lo)/2=lo+(sz+sz-1)/2=lo+sz-1
                 * 之所以使用Math.min, 是因为当N为奇数时的情况
                 */
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }

        }
    }
}
