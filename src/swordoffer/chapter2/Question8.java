package swordoffer.chapter2;

/**
 * @author: Hello World
 * @date: 2018/8/10 20:59
 * <p>
 * 面试题8: 二叉树的下一个节点
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中
 * 的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class Question8 {
    class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        /**
         * 父节点
         */
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        TreeLinkNode nextNode = null;
        /**
         * 当右子树存在时, 此时该节点的下一节点为右子树的最左节点
         */
        if (pNode.right != null) {
            TreeLinkNode curr = pNode.right;
            while (curr.left != null) {
                curr = curr.left;
            }
            nextNode = curr;

        } else if (pNode.next != null) {
            /**
             * 当右子树不存在, 父节点存在时
             * 若该节点为父节点的左节点, 则父节点为下一节点
             * 否则往上找,直至找到一个节点的父节点的左节点为该节点,同理,该父节点为下一节点
             */
            TreeLinkNode curr = pNode;
            TreeLinkNode parent = pNode.next;
            /**
             * 当节点处于最右边时, 使用parent!=null这一条件可以
             * 得到该节点的下一节点为空节点
             */
            while (parent != null && curr == parent.right) {
                curr = parent;
                parent = parent.next;
            }
            nextNode = parent;
        }
        return nextNode;
    }
}
