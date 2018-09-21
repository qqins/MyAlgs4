package company.shunfeng;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/18 8:56
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        /*int count = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > b[j]) {
                    count++;
                }
            }
        }*/
        pre = new int[n];
        sort(a,0,n-1);
        System.out.println(count);
    }
    private static int[] pre;
    private static int[] b;
    private static int count = 0;

    /*public static int inversePairs(int[] array) {
        pre = new int[array.length];
        sort(array, 0, array.length - 1);
        return count;
    }*/

    private static void sort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(array, start, mid);
        sort(array, mid + 1, end);
        merge(array, start, mid, end);
    }

    private static void merge(int[] array, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        for (int k = start; k <= end; k++) {
            pre[k] = array[k];
        }
        for (int k = start; k <= end; k++) {
            if (i > mid) {
                array[k] = b[j++];
            } else if (j > end) {
                array[k] = b[i++];
            } else if (pre[i] > b[j]) {
                array[k] = b[j++];
                //左半部分比右半部分大，说明a[i...mid]中的值都比a[j]大
                count += mid - i + 1;
            } else {
                array[k] = b[i++];
            }
        }
    }
}
