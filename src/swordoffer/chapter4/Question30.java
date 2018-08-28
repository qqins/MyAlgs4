package swordoffer.chapter4;

import java.util.Stack;

/**
 * @author: Hello World
 * @date: 2018/8/26 9:20
 * <p>
 * 面试题30：包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的
 * min函数（时间复杂度应为O（1））。
 */
public class Question30 {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minstack = new Stack<>();

    public void push(int node) {
        stack.push(node);
        if (minstack.isEmpty() || node < minstack.peek()) {
            minstack.push(node);
        } else {
            minstack.push(minstack.peek());
        }
    }

    public void pop() {
        stack.pop();
        minstack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minstack.peek();
    }
}
