package dsaaaij3.chapter7;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/12/7 15:28
 */
public class Selection {
    public static <AnyType extends Comparable<? super AnyType>>
    void selectionSort(AnyType[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                AnyType temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[10];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(array));
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
