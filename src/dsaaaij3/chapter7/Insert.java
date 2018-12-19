package dsaaaij3.chapter7;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/12/14 10:31
 */
public class Insert {
    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] array) {
        int j;
        for (int i = 1; i < array.length; i++) {
            AnyType tmp = array[i];
            for (j = i; j > 0 && tmp.compareTo(array[j - 1]) < 0; j--) {
                array[j] = array[j - 1];
            }
            array[j] = tmp;
        }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void binaryInsertionSort(AnyType[] array) {
        for (int i = 1; i < array.length; i++) {
            AnyType tmp = array[i];
            //通过二分查找得出要插入的位置
            int index = getIndexByBinary(array, i - 1, tmp);
            //将index位置后面的元素整体往后挪动一位
            for (int j = i; j > index; j--) {
                array[j] = array[j - 1];
            }
            array[index] = tmp;
        }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    int getIndexByBinary(AnyType[] array, int i, AnyType tmp) {
        int index = 0;
        int end = i;
        while (index <= end) {
            int binary = index + (end - index) / 2;
            if (tmp.compareTo(array[binary]) < 0) {
                end = binary - 1;
            } else {
                index = binary + 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[10];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(array));
        binaryInsertionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
