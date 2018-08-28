package swordoffer.chapter4;

/**
 * @author: Hello World
 * @date: 2018/8/28 21:31
 * <p>
 * 面试题37：序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 */
public class Question37 {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public String Serialize(TreeNode root) {
        if (root == null) {
            return "$";
        }
        return root.val + " " + Serialize(root.left) + " " + Serialize(root.right);
    }

    public TreeNode Deserialize(String str) {
        String[] strings = str.split(" ");
        return reConStr(strings);
    }

    private int index = 0;

    private TreeNode reConStr(String[] strings) {
        String val = strings[index];
        index++;
        if (val.equals("$")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = reConStr(strings);
        node.right = reConStr(strings);
        return node;
    }
}
