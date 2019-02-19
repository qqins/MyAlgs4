package dsaaaij3.chapter7;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/12/23 15:22
 */
public class Merge<AnyType extends Comparable<? super AnyType>> {
    private AnyType[] aux;


    /**
     * 自顶向下
     */
    public void sort(AnyType[] a) {
        aux = (AnyType[]) new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public void sort(AnyType[] a, int lo, int hi) {
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
        if (a[mid].compareTo(a[mid + 1]) > 0) {
            merge(a, lo, mid, hi);
        }
    }

    public void merge(AnyType[] a, int lo, int mid, int hi) {
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
            } else if (aux[j].compareTo(aux[i]) < 0) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    /**
     * 自底向上
     * 比较适合用链表组织的数据
     */
    public void sortBU(AnyType[] a) {
        int N = a.length;
        aux = (AnyType[]) new Comparable[a.length];
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

    public static void main(String[] args) {
        Merge<Integer> merge=new Merge<>();
        Random randomInt = new Random();
        Integer[] a = new Integer[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt.nextInt(100);
        }
        System.out.println(Arrays.toString(a));
        merge.sortBU(a);
        System.out.println(Arrays.toString(a));
    }
}
