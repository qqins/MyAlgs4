package swordoffer.chapter2;


import java.util.ArrayList;
import java.util.Stack;

/**
 * @author: Hello World
 * @date: 2018/8/1 20:34
 * <p>
 * 面试题6：从尾到头打印链表
 */
public class Question6 {
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 方法1: 使用栈
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.add(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!stack.isEmpty()) {
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

    /**
     * 方法2：使用递归
     */
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (listNode != null) {
            arrayList = printListFromTailToHead1(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }

    /**
     * 方法3：使用头插法逆置链表
     */
    public ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ListNode head = new ListNode(-1);
        while (listNode != null) {
            //保存下一个节点
            ListNode temp = listNode.next;
            //将当前节点置于头节点与空节点之间
            listNode.next = head.next;
            head.next = listNode;
            //指向下一个节点
            listNode = temp;
        }
        head = head.next;
        while (head != null) {
            arrayList.add(head.val);
            head = head.next;
        }
        return arrayList;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        Question6 question6 = new Question6();
        ArrayList<Integer> arrayList = question6.printListFromTailToHead3(listNode1);
        System.out.println(arrayList);
    }
}


