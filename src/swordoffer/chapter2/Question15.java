package swordoffer.chapter2;

/**
 * @author: Hello World
 * @date: 2018/8/16 21:02
 * <p>
 * 面试题15：二进制中1的个数
 * <p>
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class Question15 {
    public static int NumberOf1(int n) {
        int flag = 1;
        int count = 0;
        while (flag != 0) {
            /**
             * 判断条件写成！=0，不能写成==1
             * 7 & 2
             *
             * 1 1 1
             * 0 1 0
             * 0 1 0 = 2
             */
            if ((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    /**
     *  (n-1) & n 将n最右边的1置为0
     *  以此将所有的1逐个置为0, 当n为0时, 就可以得到1的个数
     */
    public static int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n - 1) & n;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(NumberOf1(-1));
        System.out.println(numberOf1(-1));
    }
}
