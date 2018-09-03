package swordoffer.chapter6;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author: Hello World
 * @date: 2018/9/3 15:04
 * <p>
 * 面试题59（一）：滑动窗口的最大值
 * 题目：给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。例如，
 * 如果输入数组{2, 3, 4, 2, 6, 2, 5, 1}及滑动窗口的大小3，那么一共存在6个
 * 滑动窗口，它们的最大值分别为{4, 4, 6, 6, 6, 5}，
 */
public class Question59 {
    /**
     * 使用双端队列
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || size < 1 || size > num.length) {
            return list;
        }
        //将每个窗口的最大值的下标记录在队首
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < num.length; i++) {
            //将比队尾小的数的下标踢出
            while (!deque.isEmpty() && num[deque.peekLast()] < num[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            //判断队首元素是否还位于当前窗口内
            if (deque.peekFirst() == i - size) {
                deque.pollFirst();
            }
            if (i >= size - 1) {
                list.add(num[deque.peekFirst()]);
            }
        }
        return list;
    }

    /**
     * 使用最大堆
     */
    public ArrayList<Integer> maxInWindows1(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || size < 1 || size > num.length) {
            return list;
        }
        PriorityQueue<Integer> max = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < size; i++) {
            max.offer(num[i]);
        }
        list.add(max.peek());
        for (int i = size, j = i - size; i < num.length; i++, j++) {
            max.remove(num[j]);
            max.offer(num[i]);
            list.add(max.peek());
        }
        return list;
    }
}
