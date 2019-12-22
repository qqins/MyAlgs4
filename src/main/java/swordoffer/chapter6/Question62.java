package swordoffer.chapter6;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Hello World
 * @date: 2018/9/4 9:01
 * <p>
 * 面试题62：圆圈中最后剩下的数字
 * 题目：0, 1, …, n-1这n个数字排成一个圆圈，从数字0开始每次从这个圆圈里
 * 删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 */
public class Question62 {
    public int lastRemainingSolution(int n, int m) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }
        int count = 0;
        int result = -1;
        while (!queue.isEmpty()) {
            if (count == m - 1) {
                result = queue.poll();
                count = 0;
            }
            if (!queue.isEmpty()) {
                queue.offer(queue.poll());
            }
            count++;
        }
        return result;
    }
}
