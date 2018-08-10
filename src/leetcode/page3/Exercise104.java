package leetcode.page3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: Hello World
 * @date: 2018/8/10 13:36
 * <p>
 * 104. 二叉树的最大深度
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 */
public class Exercise104 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 该方法也可以求树的高度
     * 输入哪个节点就输出哪个节点对应的高度
     *
     * @param root 树的节点
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * DFS
     * 深度优先搜索
     */
    public int macDepthByDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();
        stack.push(root);
        value.push(1);
        int max = 0;
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            int temp = value.pop();
            max = Math.max(temp, max);
            if (treeNode.left != null) {
                stack.push(treeNode.left);
                value.push(temp + 1);
            }
            if (treeNode.right != null) {
                stack.push(treeNode.right);
                value.push(temp + 1);
            }
        }
        return max;
    }

    /**
     * BFS
     * 广度优先搜索
     */
    public int maxDepthByBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            count++;
        }
        return count;
    }
}
