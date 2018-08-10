package leetcode.linkedlist;

/**
 * @author: Hello World
 * @date: 2018/8/9 21:03
 * <p>
 * 206. 反转链表
 * <p>
 * 反转一个单链表。
 * <p>
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class ReverseList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 头插法
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 递归
     */
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //head是p的前一个节点
        ListNode p = reverseList1(head.next);
        //相当于p.next=head
        head.next.next = head;
        //使p的尾节点为null
        head.next = null;
        return p;
    }
}
