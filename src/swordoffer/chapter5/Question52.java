package swordoffer.chapter5;

/**
 * @author: Hello World
 * @date: 2018/9/2 15:41
 * <p>
 * 面试题52：两个链表的第一个公共节点
 * 输入两个链表，找出它们的第一个公共结点。
 */
public class Question52 {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1;
        ListNode l2 = pHead2;
        int len1 = 0;
        int len2 = 0;
        while (l1 != null) {
            l1 = l1.next;
            len1++;
        }
        while (l2 != null) {
            l2 = l2.next;
            len2++;
        }
        if (len1 > len2) {
            l1 = pHead2;
            l2 = pHead1;
        } else {
            l1 = pHead1;
            l2 = pHead2;
        }
        for (int i = 0; i < Math.abs(len1 - len2); i++) {
            l2 = l2.next;
        }
        while (l1 != null && l2 != null && l1 != l2) {
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1;
    }
}
