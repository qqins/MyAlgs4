package swordoffer.chapter6;

/**
 * @author: Hello World
 * @date: 2018/9/2 20:59
 * <p>
 * 面试题54：二叉搜索树的第k个结点
 * 题目：给定一棵二叉搜索树，请找出其中的第k大的结点。
 */
public class Question54 {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    private TreeNode result;
    private int count = 0;

    private TreeNode kThNode(TreeNode pRoot, int k) {
        if (pRoot == null || k == 0) {
            return null;
        }
        inOrder(pRoot, k);
        return result;
    }

    private void inOrder(TreeNode root, int k) {
        if (root == null || count >= k) {
            return;
        }
        inOrder(root.left, k);
        count++;
        if (count == k) {
            result = root;
        }
        inOrder(root.right, k);
    }
}
