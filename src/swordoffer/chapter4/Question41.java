package swordoffer.chapter4;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author: Hello World
 * @date: 2018/8/29 22:59
 * <p>
 * 面试题41：数据流中的中位数
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
 * 那么中位数就是所有数值排序之后位于中间的数值。如果从数据流
 * 中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据
 * 的中位数。
 */
public class Question41 {
    /**
     * 最大堆，存储左半边元素
     */
    private PriorityQueue<Integer> left = new PriorityQueue<>
            ((o1, o2) -> o2 - o1);

    /**
     * 最小堆，存储右半边元素
     */
    private PriorityQueue<Integer> right = new PriorityQueue<>();
    private int N = 0;

    /**
     * 为了保证左右堆的数目只差不超过1，约定右边堆数目大于等于左边堆
     * 当N为偶数时，插入后变为奇数，将其插入到右边最小堆，为了保证右边的
     * 数始终大于左边的数，先将该数插入到左边，然后将左边最大的数插入到
     * 右边
     * 当N为奇数时(说明此时右边比左边数目大1)，插入后变为偶数，将其插入到
     * 左边最大堆，同理，先将该数插入到右边最小堆，再取出右边最小数插入到
     * 左边
     */
    public void Insert(Integer num) {
        if (N % 2 == 0) {
            left.offer(num);
            right.offer(left.poll());
        } else {
            right.offer(num);
            left.offer(right.poll());
        }
        N++;
    }

    public Double GetMedian() {
        if (N % 2 == 0) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return (double) right.peek();
        }
    }

    /**
     * 面试题41.2：字符流中第一个不重复的字符
     * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，
     * 当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
     * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
     * 如果当前字符流没有存在出现一次的字符，返回#字符。
     */
    private int[] cnts = new int[256];
    private Queue<Character> queue = new LinkedList<>();

    public void Insert(char ch) {
        cnts[ch]++;
        queue.add(ch);
        while (!queue.isEmpty() && cnts[queue.peek()] > 1) {
            queue.poll();
        }
    }

    public char FirstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }
}
