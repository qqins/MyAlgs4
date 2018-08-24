package swordoffer.chapter3;

/**
 * @author: Hello World
 * @date: 2018/8/24 15:28
 * <p>
 * 面试题23：链表中环的入口结点
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class Question23 {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode meetingNode = meetingNode(pHead);
        if (meetingNode == null) {
            return null;
        }
        int nodesInLoop = 1;
        ListNode temp = meetingNode;
        while (temp.next != meetingNode) {
            temp = temp.next;
            nodesInLoop++;
        }
        temp = pHead;
        for (int i = 0; i < nodesInLoop; i++) {
            temp = temp.next;
        }
        ListNode result = pHead;
        while (result != temp) {
            result = result.next;
            temp = temp.next;
        }
        return result;
    }

    private ListNode meetingNode(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode slow = pHead;
        if (slow == null) {
            return null;
        }
        ListNode fast = slow.next;
        while (fast != null && slow != null) {
            if (fast == slow) {
                return fast;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            }
        }
        return null;
    }
}
