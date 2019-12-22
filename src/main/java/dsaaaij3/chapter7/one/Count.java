package dsaaaij3.chapter7.one;

import java.util.Arrays;
import java.util.Random;

/**
 * @author qin
 * @date 2019/10/26 17:12
 */
public class Count {
    public static void countSort(int[] arr) {
        int[] temp = new int[arr.length];
        int max = arr[0], min = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int k = max - min + 1;
        int[] count = new int[k];
        for (int value : arr) {
            count[value - min]++;
        }
        for (int i = 1; i < count.length; ++i) {
            count[i] = count[i] + count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; --i) {
            int index = count[arr[i] - min] - 1;
            temp[index] = arr[i];
            count[arr[i] - min]--;
        }
        System.arraycopy(temp, 0, arr, 0, arr.length);
    }

    public static void countSort2(int[] arr) {
        int[] temp = new int[arr.length];
        int max = arr[0], min = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int k = max - min + 1;
        int[] count = new int[k + 1];
        for (int value : arr) {
            count[value - min + 1]++;
        }
        for (int i = 0; i < count.length - 1; ++i) {
            count[i + 1] += count[i];
        }

        for (int value : arr) {
            temp[count[value - min]++] = value;
        }
        System.arraycopy(temp, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        Random randomInt = new Random();
        int[] a = new int[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt.nextInt(100);
        }
        System.out.println(Arrays.toString(a));
        countSort2(a);
        System.out.println(Arrays.toString(a));
    }
}
