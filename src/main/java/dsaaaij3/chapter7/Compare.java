package dsaaaij3.chapter7;

import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/12/19 11:03
 */
public class Compare {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.printf("输入规模     \t选择       \t\t冒泡       \t\t插入       \t\t二分插入       \t希尔 \n");
        for (int n = 1000; n < 1000000; n *= 10) {
            Integer[] array = new Integer[n];
            for (int i = 0; i < n; i++) {
                array[i] = random.nextInt(n);
            }
            System.out.print(String.format("N=%7d", n));
            for (int alg = 0; alg < 5; alg++) {
                Integer[] a = new Integer[n];
                System.arraycopy(array,0,a,0,n);
                getTiminginfo(n,alg,a);
            }
            System.out.println();
        }
    }

    public static void getTiminginfo(int n, int alg, Integer[] array) {
        long start=System.currentTimeMillis();
        switch (alg) {
            case 0:
                Selection.selectionSort(array);
                break;
            case 1:
                Bubble.bubbleSort(array);
                break;
            case 2:
                Insert.insertionSort(array);
                break;
            case 3:
                Insert.binaryInsertionSort(array);
                break;
            case 4:
                Shell.shellSort(array);
                break;
        }
        long totalTime=System.currentTimeMillis()-start;
        System.out.print(String.format("\t%12.6f", totalTime / (double) 1000));
    }
}
