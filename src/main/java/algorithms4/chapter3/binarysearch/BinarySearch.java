package algorithms4.chapter3.binarysearch;


import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/6/24 19:56
 */
public class BinarySearch {
    public static int binarySearch(int[] a, int target) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target < a[mid]) {
                hi = mid - 1;
            } else if (target > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 递归
     */
    public static int binarySearchByRe(int[] a, int target, int lo, int hi) {
        if (hi < lo) {
            return -1;
        }
        int mid = lo + (hi - lo) / 2;
        if (target < a[mid]) {
            return binarySearchByRe(a, target, lo, mid - 1);
        } else if (target > a[mid]) {
            return binarySearchByRe(a, target, mid + 1, hi);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        Random randomInt = new Random();
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt.nextInt(10);
        }
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        System.out.println(binarySearchByRe(a, 4,0,a.length-1));
    }
}
