package company.xiaohongshu;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/18 19:40
 */
public class Main2 {
    static class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;

        TreeNode(String x) {
            val = x;
        }
    }

    //    private static String[] in;
    private static int levels = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        String[] level = str1.split("\\s+");
        String[] in = str2.split("\\s+");
        TreeNode root = newTree(level, in, in.length, 0, in.length - 1);
        pre(root);
    }

    private static TreeNode newTree(String[] level, String[] in, int len, int start, int end) {
        if (start > end) {
            return null;
        }
        int ls = start;
        int le = 0;
        int re = end;
        int rs = 0;
        boolean flag = false;
        TreeNode root = new TreeNode("");
        if (start == end) {
            root.val = in[start];
            root.left = null;
            root.right = null;
            return root;
        }
        for (levels = 0; levels < len; levels++) {
            for (int i = start; i < end; i++) {
                if (in[i].equals(level[levels])) {
                    le = i - 1;
                    rs = i + 1;
                    flag = true;
                    root.val = level[levels];
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        root.left = newTree(level, in, len, ls, le);
        root.right = newTree(level, in, len, rs, re);
        return root;
    }

    private static void pre(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        pre(node.left);
        pre(node.right);
    }
}
