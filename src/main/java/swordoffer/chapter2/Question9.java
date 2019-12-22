package swordoffer.chapter2;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: Hello World
 * @date: 2018/8/11 20:25
 * <p>
 * 面试题9: 用两个栈实现队列
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class Question9 {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        Question9 queue = new Question9();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}

/**
 * 用两个队列实现栈
 */
class TwoQueueForStack {
    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();

    public void push(int node) {
        if (!queue1.isEmpty()) {
            queue1.offer(node);
        } else {
            queue2.offer(node);
        }
    }

    public int pop() {
        int result = 0;
        if (queue1.isEmpty()) {
            if (queue2.isEmpty()) {
                throw new NoSuchElementException("Stack is empty");
            }
            while (queue2.size() > 1) {
                queue1.offer(queue2.poll());
            }
            result = queue2.poll();
        } else {
            while (queue1.size() > 1) {
                queue2.offer(queue1.poll());
            }
            result = queue1.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        TwoQueueForStack stack = new TwoQueueForStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

/**
 * 一个队列实现一个栈
 */
class OneQueueForStack {
    private Queue<Integer> queue = new LinkedList<>();

    public void push(int node) {
        queue.offer(node);
    }

    public int pop() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        int size = queue.size();
        while (size-- > 1) {
            queue.offer(queue.poll());
        }
        return queue.poll();
    }

    public static void main(String[] args) {
        OneQueueForStack stack = new OneQueueForStack();
        stack.push(4);
        stack.push(5);
        stack.push(6);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
