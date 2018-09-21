package company.fourthreeninenine;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/17 8:50
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i]=scanner.nextInt();
        }
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(array[i]);
            if (pq.size() == m+1) {
                pq.poll();
            }
        }
        System.out.println(pq);
    }
}
