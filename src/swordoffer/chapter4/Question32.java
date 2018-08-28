package swordoffer.chapter4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: Hello World
 * @date: 2018/8/27 19:37
 * <p>
 * 面试题32：从上到下打印二叉树
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class Question32 {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (root == null) {
            return arrayList;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            arrayList.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return arrayList;
    }

    /**
     * 面试题32.2：分行从上到下打印二叉树
     * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
     */
    public ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (pRoot == null) {
            return result;
        }
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            int cnt = queue.size();
            while (cnt > 0) {
                TreeNode node = queue.poll();
                arrayList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                cnt--;
            }
            result.add(arrayList);
        }
        return result;
    }

    /**
     * 面试题32.3：之字形打印二叉树
     * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
     * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行
     * 以此类推。
     */
    public ArrayList<ArrayList<Integer>> printForZhi(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        TreeNode node = null;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(pRoot);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            while (!stack1.isEmpty()) {
                node = stack1.pop();
                arrayList.add(node.val);
                if (node.left != null) {
                    stack2.push(node.left);
                }
                if (node.right != null) {
                    stack2.push(node.right);
                }
            }
            result.add(arrayList);
            arrayList = new ArrayList<>();
            while (!stack2.isEmpty()) {
                node = stack2.pop();
                arrayList.add(node.val);
                if (node.right != null) {
                    stack1.push(node.right);
                }
                if (node.left != null) {
                    stack1.push(node.left);
                }
            }
            if (!arrayList.isEmpty()) {
                result.add(arrayList);
            }
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> printForZhi2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        boolean flag = false;
        while (!queue.isEmpty()) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            int cnt = queue.size();
            while (cnt > 0) {
                TreeNode node = queue.poll();
                if (!flag) {
                    arrayList.add(node.val);
                } else {
                    arrayList.add(0, node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                cnt--;
            }
            flag = !flag;
            result.add(arrayList);
        }
        return result;
    }
}
