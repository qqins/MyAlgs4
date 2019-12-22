package swordoffer.chapter6;

import java.util.*;

/**
 * @author: Hello World
 * @date: 2018/9/4 11:01
 * <p>
 * 面试题68：树中两个结点的最低公共祖先
 * 题目：输入两个树结点，求它们的最低公共祖先。
 */
public class Question68 {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 二叉搜索树
     *          ___6______
     *      /              \
     * ___2__          ___8__
     * /      \        /      \
     * 0      _4       7       9
     * /  \
     * 3   5
     * <p>
     * 2和8的最低公共节点为6
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int min = Math.min(p.val, q.val);
        int max = p.val + q.val - min;
        while (!(root.val >= min && root.val <= max)) {
            if (root.val > max) {
                root = root.left;
            }
            if (root.val < min) {
                root = root.right;
            }
        }
        return root;
    }

    public TreeNode lowestCommonAncestorByRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestorByRecursive(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestorByRecursive(root.right, p, q);
        }
        return root;
    }

    /**
     * 二叉树
     *   _______3______
     *  /              \
     * ___5__          ___1__
     * /      \        /      \
     * 6      _2       0       8
     * /  \
     * 7   4
     * <p>
     * 6和4的最低公共节点为5
     */
    public TreeNode lowestCommonAncestor2ByRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2ByRecursive(root.left, p, q);
        TreeNode right = lowestCommonAncestor2ByRecursive(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    /**
     * 寻找从root到p，q节点的路径，将其分别存入ArrayList中，然后通过ArrayList来找公共祖先
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> first = findPath(root, p);
        ArrayList<TreeNode> second = findPath(root, q);
        int i = first.size() - 1;
        int j = second.size() - 1;
        while (i >= 0 && j >= 0 && (first.get(i) == second.get(i))) {
            i--;
            j--;
        }
        return first.get(i + 1);
    }

    private ArrayList<TreeNode> findPath(TreeNode root, TreeNode end) {
        if (root == null) {
            return null;
        }
        ArrayList<TreeNode> ans = new ArrayList<>();
        ArrayList<TreeNode> left = findPath(root.left, end);
        ArrayList<TreeNode> right = findPath(root.right, end);
        if (left == null && right == null) {
            if (root == end) {
                ans.add(root);
                return ans;
            } else {
                return null;
            }
        }
        if (left != null) {
            left.add(root);
            return left;
        }
        if (right != null) {
            right.add(root);
            return right;
        }
        return null;
    }

    /**
     * 层序
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        parent.put(root, null);
        queue.offer(root);
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                parent.put(node.left, node);
                queue.offer(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                queue.offer(node.right);
            }
        }
        Set<TreeNode> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = parent.get(p);
        }
        while (!set.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }
}