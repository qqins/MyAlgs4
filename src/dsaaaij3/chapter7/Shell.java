package dsaaaij3.chapter7;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/12/14 10:59
 */
public class Shell {
    public static <AnyType extends Comparable<? super AnyType>>
    void shellSort(AnyType[] array) {
        int j;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                AnyType tmp = array[i];
                for (j = i; j >= gap && tmp.compareTo(array[j - gap]) < 0; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = tmp;
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
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }
}
