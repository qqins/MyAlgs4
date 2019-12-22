package swordoffer.chapter4;

import java.util.Stack;

/**
 * @author: Hello World
 * @date: 2018/8/26 23:51
 * <p>
 * 面试题31：栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能
 * 为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈
 * 的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就
 * 不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class Question31 {
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<>();
        for (int pushLength = 0, popLength = 0; pushLength < pushA.length; pushLength++) {
            stack.push(pushA[pushLength]);
            while (popLength < popA.length && stack.peek() == popA[popLength]) {
                stack.pop();
                popLength++;
            }
        }
        return stack.isEmpty();
    }
}
