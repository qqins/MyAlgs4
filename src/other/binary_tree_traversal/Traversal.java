package other.binary_tree_traversal;

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

    public static void visit(TreeNode root) {
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        /**
         * 构造二叉树
         *                A
         *            B       C
         *        D        E     F
         *     G     H        I
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
        //递归前序遍历, 结果为: ABDGHCEIF
        recursionPreorderTraversal(a);
    }
}


