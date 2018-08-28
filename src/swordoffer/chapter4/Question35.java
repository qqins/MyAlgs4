package swordoffer.chapter4;

/**
 * @author: Hello World
 * @date: 2018/8/28 20:16
 * <p>
 * 面试题35：复杂链表的复制
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
 * 另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 */
public class Question35 {
    class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public RandomListNode clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        //1，将链表当前节点往后复制
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode clone = new RandomListNode(cur.label);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }
        //2，设置复制节点的随机节点
        cur = pHead;
        while (cur != null) {
            RandomListNode clone = cur.next;
            if (cur.random != null) {
                clone.random = cur.random.next;
            }
            cur = clone.next;
        }
        //3，将当前这个链表分解为2个链表，奇数项为原始链表，偶数项为复制链表
        cur = pHead;
        RandomListNode cloneHead = cur.next;
        while (cur.next != null) {
            RandomListNode clone = cur.next;
            cur.next = clone.next;
            cur = clone;
        }
        return cloneHead;
    }
}
