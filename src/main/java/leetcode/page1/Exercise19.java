package leetcode.page1;

/**
 * @author: Hello World
 * @date: 2018/8/9 20:14
 * <p>
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * <p>
 * 说明：
 * 给定的 n 保证是有效的。
 */
public class Exercise19 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * second先向前移动n+1步
     * 之后first与second一起向前移动,直到second指向最后一个元素
     * 此时first指向的是待删除节点的前一个节点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        //引入dummy节点是为了当head链表只有一个节点,并n=1时,能将该链表置为null
        dummy.next = head;
        //若first与second指向head, 当链表只有一个数时会出错
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 0; i < n + 1; i++) {
            second = second.next;
        }
        while (second != null) {
            first = first.next;
            second = second.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }

    /**
     * 常规解法
     */
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //first指向head是为了统计head链表的元素个数
        ListNode first = head;
        int length = 0;
        while (first != null) {
            first = first.next;
            length++;
        }
        //此时length为待删除节点的前一个节点的序号
        length -= n;
        //此时first指向dummy是为了满足当链表只有一个元素时的情况
        first = dummy;
        while (length > 0) {
            first = first.next;
            length--;
        }
        first.next = first.next.next;
        return dummy.next;
    }
}
