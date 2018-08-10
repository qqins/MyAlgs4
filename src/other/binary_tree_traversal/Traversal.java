package other.binary_tree_traversal;


import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: Hello World
 * @date: 2018/8/2 22:39
 */
public class Traversal {
    static class TreeNode {
        public char val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(char x) {
            this.val = x;
        }
    }

    /**
     * 递归前序遍历
     *
     * @param root
     */
    public static void recursionPreorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        visit(root);
        recursionPreorderTraversal(root.left);
        recursionPreorderTraversal(root.right);
    }

    /**
     * 非递归前序遍历
     *
     * @param root
     */
    public static void preorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode treeNode;
        while (!stack.isEmpty()) {
            treeNode = stack.pop();
            visit(treeNode);
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
    }

    /**
     * 递归中序遍历
     *
     * @param root
     */
    public static void recursionMiddleOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        recursionMiddleOrderTraversal(root.left);
        visit(root);
        recursionMiddleOrderTraversal(root.right);
    }

    /**
     * 非递归中序遍历
     *
     * @param root
     */
    public static void middleOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode treeNode;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            treeNode = stack.pop();
            visit(treeNode);
            cur = treeNode.right;
        }
    }

    /**
     * 递归后序遍历
     *
     * @param root
     */
    public static void recursionPostOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        recursionPostOrderTraversal(root.left);
        recursionPostOrderTraversal(root.right);
        visit(root);
    }

    /**
     * 非递归后序遍历
     * 利用两个栈实现
     *
     * @param root
     */
    public static void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode cur = root;
        stack1.push(cur);
        while (!stack1.isEmpty()) {
            cur = stack1.pop();
            if (cur.left != null) {
                stack1.push(cur.left);
            }
            if (cur.right != null) {
                stack1.push(cur.right);
            }
            stack2.push(cur);
        }
        while (!stack2.isEmpty()) {
            visit(stack2.pop());
        }
    }

    /**
     * 非递归后序遍历
     * 单栈实现
     *
     * @param root
     */
    public static void postOrderTraversal1(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode lastView = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || cur.right == lastView) {
                visit(cur);
                stack.pop();
                lastView = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }
    }

    /**
     * 层序遍历
     *
     * @param root
     */
    public static void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode cur = root;
        queue.add(cur);
        while (!queue.isEmpty()) {
            cur = queue.remove();
            visit(cur);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public static void visit(TreeNode root) {
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        /**
         * 构造二叉树
         *                A
         *            B       C
         *        D         E     F
         *     G    H         I
         */
        TreeNode a = new TreeNode('A');
        TreeNode b = new TreeNode('B');
        TreeNode c = new TreeNode('C');
        TreeNode d = new TreeNode('D');
        TreeNode e = new TreeNode('E');
        TreeNode f = new TreeNode('F');
        TreeNode g = new TreeNode('G');
        TreeNode h = new TreeNode('H');
        TreeNode i = new TreeNode('I');
        a.left = b;
        a.right = c;
        b.left = d;
        c.left = e;
        c.right = f;
        d.left = g;
        d.right = h;
        e.right = i;
        //前序遍历(根左右), 结果为: ABDGHCEIF
//        recursionPreorderTraversal(a);
//        preorderTraversal(a);

        //中序遍历(左根右), 结果为: GDHBAEICF
//        recursionMiddleOrderTraversal(a);
//        middleOrderTraversal(a);

        //后序遍历(左右根), 结果为: GHDBIEFCA
//        recursionPostOrderTraversal(a);
//        postOrderTraversal(a);
        postOrderTraversal1(a);

        //层序遍历
//        levelOrder(a);
    }
}


