package dsaaaij3.chapter7.one;

import java.util.*;

/**
 * @author qin
 * @date 2019/10/26 15:31
 */
public class Bucket {
    public static void bucketSort(int[] arr, int step) {
        int max = arr[0];
        int min = arr[0];
        for (int a : arr) {
            if (max < a) {
                max = a;
            }
            if (min > a) {
                min = a;
            }
        }
        int bucketNum = max / step - min / step + 1;
        List buckList = new ArrayList<List<Integer>>();
        for (int i = 0; i < bucketNum; i++) {
            buckList.add(new ArrayList<Integer>());
        }
        for (int value : arr) {
            int index = indexFor(value, min, step);
            ((ArrayList<Integer>) buckList.get(index)).add(value);
        }
        ArrayList<Integer> bucket = null;
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            bucket = (ArrayList<Integer>) buckList.get(i);
            Collections.sort(bucket);
            // insertSort(bucket);
            for (int k : bucket) {
                arr[index++] = k;
            }
        }
    }

    private static int indexFor(int a, int min, int step) {
        return (a - min) / step;
    }

    private void insertSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int temp = bucket.get(i);
            int j = i - 1;
            for (; j >= 0 && bucket.get(j) > temp; j--) {
                bucket.set(j + 1, bucket.get(j));
            }
            bucket.set(j + 1, temp);
        }
    }

    public static void main(String[] args) {
        Random randomInt = new Random();
        int[] a = new int[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt.nextInt(100);
        }
        System.out.println(Arrays.toString(a));
        bucketSort(a, 10);
        System.out.println(Arrays.toString(a));
    }
}
