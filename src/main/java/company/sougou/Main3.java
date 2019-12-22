package company.sougou;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/14 16:41
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int max = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int temp = scanner.nextInt();
            if (temp > max) {
                max = temp;
            }
            queue.offer(temp);
        }
        int r1 = 0;
        int r2 = 0;
        r1 += max;
        for (int i = 0; i < n; i++) {
            if (queue.peek() == max) {
                queue.poll();
                break;
            } else {
                queue.offer(queue.poll());
            }
        }
        boolean flag = true;
        while (!queue.isEmpty()) {
            if (flag) {
                if (queue.peekFirst() > queue.peekLast()) {
                    r2 += queue.pollFirst();
                } else {
                    r2 += queue.pollLast();
                }
                flag = false;
            } else {
                if (queue.peekFirst() > queue.peekLast()) {
                    r1 += queue.pollFirst();
                } else {
                    r1 += queue.pollLast();
                }
                flag = true;
            }
        }
        System.out.println(Math.abs(r1 - r2));
    }
}
