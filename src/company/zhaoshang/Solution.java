package company.zhaoshang;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/25 16:36
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n=scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i]=scanner.nextInt();
        }
        int min = array[0];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
            if (array[i] - min > max) {
                max = array[i] - min;
            }
        }
        System.out.println(max >= 0 ? max : 0);
    }
}
