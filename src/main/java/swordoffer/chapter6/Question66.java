package swordoffer.chapter6;

/**
 * @author: Hello World
 * @date: 2018/9/4 10:06
 * <p>
 * 面试题66：构建乘积数组
 * 题目：给定一个数组A[0, 1, …, n-1]，请构建一个数组B[0, 1, …, n-1]，其
 * 中B中的元素B[i] =A[0]×A[1]×… ×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 */
public class Question66 {
    public int[] multiply(int[] A) {
        int[] B = new int[A.length];
        //从左往右累乘
        for (int i = 0, product = 1; i < B.length; i++) {
            //B[i]=A[0]A[1]...A[i-1]
            B[i] = product;
            product *= A[i];
        }
        //从右往左累乘
        for (int i = B.length - 1, product = 1; i >= 0; i--) {
            //B[i]=A[0]A[1]...A[i-1]*product
            B[i] *= product;
            //product=A[n]A[n-1]...A[i+1]
            product *= A[i];
        }
        return B;
    }
}
