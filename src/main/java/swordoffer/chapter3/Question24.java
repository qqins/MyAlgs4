package swordoffer.chapter3;

/**
 * @author: Hello World
 * @date: 2018/8/24 19:59
 * <p>
 * 面试题24：反转链表
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class Question24 {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode ReverseList(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }
}
