package swordoffer.chapter4;

import java.util.ArrayList;

/**
 * @author: Hello World
 * @date: 2018/8/28 19:43
 * <p>
 * 面试题34：二叉树中和为某一值的路径
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的
 * 所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成
 * 一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class Question34 {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        back(root, target, new ArrayList<Integer>());
        return result;
    }

    private void back(TreeNode root, int target, ArrayList<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            //若不new，则添加的这个引用都指向了一个list
            result.add(new ArrayList<>(path));
        } else {
            back(root.left, target, path);
            back(root.right, target, path);
        }
        path.remove(path.size() - 1);
    }
}
