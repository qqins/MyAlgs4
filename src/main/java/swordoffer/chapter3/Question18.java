package swordoffer.chapter3;

/**
 * @author: Hello World
 * @date: 2018/8/19 17:34
 * <p>
 * 面试题18：删除链表的节点
 * 在 O(1) 时间内删除链表节点
 */
public class Question18 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
        if (head == null || tobeDelete == null) {
            return null;
        }
        //要删除的节点不是尾节点
        if (tobeDelete.next != null) {
            ListNode tmp = tobeDelete.next;
            tobeDelete.val = tmp.val;
            tobeDelete.next = tmp.next;
            tmp = null;
            //只有一个节点时
        } else if (head == tobeDelete) {
            head = null;
        } else {
            ListNode cur = head;
            while (cur != tobeDelete) {
                cur = cur.next;
            }
            cur.next = null;
        }
        return head;
    }

    /**
     * 面试题18.2：删除链表中重复的节点
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，
     * 重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5
     * 处理后为 1->2->5
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode prev = null;
        ListNode cur = pHead;
        while (cur != null) {
            ListNode next = cur.next;
            if (next != null && cur.val == next.val) {
                while (next != null && cur.val == next.val) {
                    next = next.next;
                }
                //当头结点就重复时
                if (prev == null) {
                    pHead = next;
                } else {
                    prev.next = next;
                }
                cur = next;
            } else {
                prev = cur;
                cur = next;
            }
        }
        return pHead;
    }

    /**
     * 递归版
     */
    public ListNode deleteDuplicationByRecursively(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode next = pHead.next;
        if (pHead.val == next.val) {
            while (next != null && pHead.val == next.val) {
                next = next.next;
            }
            return deleteDuplicationByRecursively(next);
        } else {
            pHead.next = deleteDuplicationByRecursively(pHead.next);
            return pHead;
        }
    }
}
