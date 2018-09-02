package swordoffer.chapter6;

/**
 * @author: Hello World
 * @date: 2018/9/2 21:56
 * <p>
 * 面试题56（一）：数组中只出现一次的两个数字
 * 题目：一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序
 * 找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 */
public class Question56 {
    public void findNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int diff = 0;
        for (int num : array) {
            diff ^= num;
        }
        /**
         * 可得到最右侧不为0的位
         *  diff=00000000 00000000 00000000 00001100
         * !diff=11111111 11111111 11111111 11110011
         * -diff=11111111 11111111 11111111 11110100
         * diff & -diff
         *      =00000000 00000000 00000000 00000100
         */

        diff &= -diff;
        for (int num : array) {
            if ((num & diff) == 0) {
                num1[0] ^= num;
            } else {
                num2[0] ^= num;
            }
        }
    }

    /**
     * 面试题56（二）：数组中唯一只出现一次的数字
     * 题目：在一个数组中除了一个数字只出现一次之外，其他数字都出现了三次。请
     * 找出那个只出现一次的数字。
     */
    public static int findNumberAppearingOnce(int[] numbers) {
        int[] bitSum = new int[32];
        for (int i = 0; i < numbers.length; i++) {
            int bitMask = 1;
            for (int j = 31; j >= 0; j--) {
                int bit = numbers[i] & bitMask;
                if (bit != 0) {
                    bitSum[j] += 1;
                }
                bitMask = bitMask << 1;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = result << 1;
            result += bitSum[i] % 3;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = { 4, 3, 3, 2, 2, 2, 3 };
        System.out.println(findNumberAppearingOnce(numbers));
    }
}
