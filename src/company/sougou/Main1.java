package company.sougou;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/14 17:29
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n=scanner.nextInt();
        int[] array = new int[n];
        HashSet<Integer> set=new HashSet<>();
        for (int i = 0; i < n; i++) {
            array[i]=scanner.nextInt();
            set.add(array[i]);
        }
        int start=0;
        int end=0;
        for (int i = 0; i < n; i++) {
            if (set.contains(array[i])) {

            }
        }
    }
}
