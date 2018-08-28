package swordoffer.chapter4;

/**
 * @author: Hello World
 * @date: 2018/8/27 22:53
 * <p>
 * 面试题33：二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class Question33 {
    public static boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length==0) {
            return false;
        }
        return verifySquenceOfBST(sequence, 0, sequence.length - 1);
    }

    private static boolean verifySquenceOfBST(int[] sequence, int start, int end) {
        if (end - start <= 1) {
            return true;
        }
        int root = sequence[end];
        int cutIndex = start;
        while (sequence[cutIndex] < root) {
            cutIndex++;
        }
        for (int i = cutIndex; i < end; i++) {
            if (sequence[i] < root) {
                return false;
            }
        }
        return verifySquenceOfBST(sequence, start, cutIndex - 1)
                && verifySquenceOfBST(sequence, cutIndex, end - 1);
    }

    public static void main(String[] args) {
        int [] sequence={1,2,3,4,5};
        System.out.println(VerifySquenceOfBST(sequence));
    }
}
