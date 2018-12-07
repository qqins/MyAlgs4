package dsaaaij3.chapter7;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/12/7 9:36
 */
public class Bubble {
    public static <AnyType extends Comparable<? super AnyType>>
    void bubbleSort(AnyType[] array) {
        boolean changed;
        for (int i = 0; i < array.length - 1; i++) {
            changed = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    AnyType temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    changed = true;
                }
            }
            if (!changed) {
                return;
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
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
}
