package swordoffer.chapter3;

/**
 * @author: Hello World
 * @date: 2018/8/24 15:07
 * <p>
 * 面试题22：链表中倒数第k个结点
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class Question22 {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        ListNode cur = head;
        ListNode result = head;
        for (int i = 1; i < k; i++) {
            if (cur.next != null) {
                cur = cur.next;
            } else {
                return null;
            }
        }
        while (cur.next != null) {
            cur = cur.next;
            result = result.next;
        }
        return result;
    }
}
