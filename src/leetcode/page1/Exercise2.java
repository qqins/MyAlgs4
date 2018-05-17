package leetcode.page1;

/**
 * @author: Hello World
 * @date: 2018/5/17 19:20
 * <p>
 * 2,两数相加
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。
 * 将两数相加返回一个新的链表。你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class Exercise2 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode p = l1;
        ListNode q = l2;
        ListNode r = new ListNode(0);
        ListNode t = r;
        while (p != null || q != null) {
            //若p、q长度不一，就要将其高位补0
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;

            //carry为进位
            int sum = x + y + carry;
            carry = sum / 10;
            /**
             * 当第一次循环时
             * 之所以把结果放在下一个节点，是为了避免最后又一个多余的尾部节点
             * 若一开始就 t.val=sum%10; t.next=...; t=t.next;
             * 那么当p和q最高位都相加完后，t仍会new一个新节点，这样就会有一个多余的节点
             * 或许会想，在循环结束后判断carry（进位）若为0，就将t指向null，试过，最后r
             * 还是会有一个多余节点
             * 所以一开始就让其从下一个节点开始置数
             */
            t.next = new ListNode(sum % 10);
            t = t.next;

            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            t.next = new ListNode(carry);
        }
        return r.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode r = addTwoNumbers(l1, l2);
        System.out.println(r.val + " " + r.next.val + " " + r.next.next.val + " " );
    }
}
