package swordoffer.chapter2;

/**
 * @author: Hello World
 * @date: 2018/8/4 22:07
 * <p>
 * 面试题7: 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 * 则重建二叉树并返回。
 */
public class Question7 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 根据前序与中序重建二叉树
     *
     * @param pre 前序序列
     * @param in  中序序列
     * @return
     */
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        TreeNode root = reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        return root;
    }

    /**
     * @param pre      前序序列
     * @param startPre 前序序列开始下标
     * @param endPre   前序序列结束下标
     * @param in       中序序列
     * @param startIn  中序序列开始下标
     * @param endIn    中序序列结束下标
     * @return
     */
    public static TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn) {
            return null;
        }
        //前序序列的第一个元素为根节点
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                /**
                 * 递归构建当前根节点的左子树, 当前左子树的个数为: i-startIn+1
                 * 左子树对应的前序遍历的位置在[startPre+1, startPre+i-startIn]
                 * 左子树对应的中序遍历的位置在[startIn, i-1]
                 */
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                /**
                 * 递归构建当前根节点的右子树, 当前右子树的个数为: endIn-i
                 * 右子树对应的前序遍历的位置在[startPre+i-startIn+1, endPre]
                 * 右子树对应的中序遍历的位置在[i+1, endIn]
                 */
                root.right = reConstructBinaryTree(pre, startPre + i - startIn + 1, endPre, in, i + 1, endIn);
            }
        }
        return root;
    }

    /**
     * 根据中序后序序列重建二叉树
     *
     * @param in   中序序列
     * @param post 后序序列
     * @return
     */
    public static TreeNode reConstructBinaryTreePost(int[] in, int[] post) {
        TreeNode root = reConstructBinaryTreePost(in, 0, in.length - 1, post, 0, post.length - 1);
        return root;
    }

    /**
     * @param in        中序序列
     * @param startIn   中序序列开始下标
     * @param endIn     中序序列结束下标
     * @param post      后序序列
     * @param startPost 后序序列开始下标
     * @param endPost   后序序列结束下标
     * @return
     */
    public static TreeNode reConstructBinaryTreePost(int[] in, int startIn, int endIn, int[] post, int startPost,
                                                     int endPost) {
        if (startIn > endIn || startPost > endPost) {
            return null;
        }
        TreeNode root = new TreeNode(post[endPost]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == post[endPost]) {
                /**
                 * 递归构建当前根节点的左子树, 当前左子树的个数为: i-startIn+1
                 * 左子树对应的中序遍历的位置在[startIn, i-1]
                 * 左子树对应的后序遍历的位置在[startPost, startPost+i-startIn-1]
                 */
                root.left = reConstructBinaryTreePost(in, startIn, i - 1, post, startPost, startPost + i - startIn - 1);
                /**
                 * 递归构建当前根节点的右子树, 当前右子树的个数为: endIn-i
                 * 右子树对应的中序遍历的位置在[i+1, endIn]
                 * 右子树对应的后序遍历的位置在[startPost+i-startIn, endPost-1]
                 */
                root.right = reConstructBinaryTreePost(in, i + 1, endIn, post, startPost + i - startIn, endPost - 1);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] post = {7, 4, 2, 5, 8, 6, 3, 1};
        TreeNode root = reConstructBinaryTreePost(in, post);
        preVisit(root);
        System.out.println();
        inVisit(root);
        System.out.println();
        postVisit(root);
    }

    public static void preVisit(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preVisit(root.left);
        preVisit(root.right);
    }

    public static void inVisit(TreeNode root) {
        if (root == null) {
            return;
        }
        inVisit(root.left);
        System.out.print(root.val + " ");
        inVisit(root.right);
    }

    public static void postVisit(TreeNode root) {
        if (root == null) {
            return;
        }
        postVisit(root.left);
        postVisit(root.right);
        System.out.print(root.val + " ");
    }
}
