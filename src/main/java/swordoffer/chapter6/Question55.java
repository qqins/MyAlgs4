package swordoffer.chapter6;

/**
 * @author: Hello World
 * @date: 2018/9/2 21:18
 * <p>
 * 面试题55（一）：二叉树的深度
 * 题目：输入一棵二叉树的根结点，求该树的深度。从根结点到叶结点依次经过的
 * 结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 */
public class Question55 {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return (left < right) ? right + 1 : left + 1;
    }

    /**
     * 面试题55（二）：平衡二叉树
     * 题目：输入一棵二叉树的根结点，判断该树是不是平衡二叉树。如果某二叉树中
     * 任意结点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     */
    private boolean isBalance = true;

    public boolean isBalancedSolution(TreeNode root) {
        height(root);
        return isBalance;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        if (Math.abs(left - right) > 1) {
            isBalance = false;
        }
        return 1 + Math.max(left, right);
    }
}
