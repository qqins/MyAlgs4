package algorithms4.chapter2.sort;


import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/6/11 10:32
 * <p>
 * 改进如下：
 * 1, 当数组规模较小时, 采用插入排序, 加快小数组的排序速度
 * 2, 若数组左半部分最大值小于右半部分最小值, 则其已经有序, 无需再比较, 直接返回
 * 3, 在递归中交换参数避免数组复制
 */
public class MergeImprove {
    private static final int CUTOFF = 7;

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (Template.less(src[j], src[i])) {
                dst[k] = src[j++];
            } else {
                dst[k] = src[i++];
            }
        }
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        System.arraycopy(a, 0, aux, 0, a.length);
       /* for (int i = 0; i < a.length; i++) {
            aux[i]=a[i];
        }*/
        sort(aux, a, 0, a.length - 1);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi <= lo + CUTOFF) {
            insertionSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        /**
         * 在递归调用的每个层次交换输入数组和辅助数组
         * 归并是对两个有序的部分进行排序，在递归时交换src和dst
         * 是为了对左右两个有序的部分进行排序
         */
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);
        if (!Template.less(src[mid + 1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        merge(src, dst, lo, mid, hi);
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && Template.less(a[j], a[j - 1]); j--) {
                Template.exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Random randomInt = new Random();
        Integer[] a = new Integer[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt.nextInt(100);
        }
        Template.show(a);
        sort(a);
        Template.show(a);
    }
}
