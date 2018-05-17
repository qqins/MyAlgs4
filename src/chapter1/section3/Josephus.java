package chapter1.section3;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author: Hello World
 * @date: 2018/5/14 19:57
 */
public class Josephus {
    public static void main(String[] args) {
        int m = 7;
        int n = 2;
        int count = 0;
        Queue<Integer> queue = new ArrayDeque();
        for (int i = 0; i < m; i++) {
            queue.add(i);
        }
        while (!queue.isEmpty()) {
            if (count == n - 1) {
                System.out.print(queue.remove() + " ");
                count = 0;
            }
            if (!queue.isEmpty()) {
                queue.add(queue.remove());
            }
            count++;
        }
    }
}
