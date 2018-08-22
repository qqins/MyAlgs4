package swordoffer.chapter3;

/**
 * @author: Hello World
 * @date: 2018/8/16 22:23
 * <p>
 * 面试题17：打印从1到最大的n位数
 * <p>
 * 输入数字 n，按顺序打印出从 1 最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直
 * 到最大的 3 位数即 999。
 */
public class Question17 {
    private static void printNumber(char[] numbers) {
        int index = 0;
        while (index < numbers.length && numbers[index] == '0') {
            index++;
        }
        while (index < numbers.length) {
            System.out.print(numbers[index++]);
        }
        System.out.println();
    }

    /**
     * 采用递归
     */
    public static void print1ToMaxOfNDigits(int n) {
        if (n <= 0) {
            return;
        }
        char[] numbers = new char[n];
        for (int i = 0; i < 10; i++) {
            numbers[0] = (char) (i + '0');
            print1ToMaxOfNDigitsRecursively(numbers, n, 0);
        }
    }

    private static void print1ToMaxOfNDigitsRecursively(char[] numbers, int length, int index) {
        if (index == length - 1) {
            printNumber(numbers);
            return;
        }
        for (int i = 0; i < 10; i++) {
            numbers[index + 1] = (char) (i + '0');
            print1ToMaxOfNDigitsRecursively(numbers, length, index + 1);
        }
    }

    /**
     * 常规方法
     */
    public static void print1ToMaxOfNDigits2(int n) {
        if (n <= 0) {
            return;
        }
        char[] numbers = new char[n];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = '0';
        }
        while (!increment(numbers)) {
            printNumber(numbers);
        }
    }

    private static boolean increment(char[] numbers) {
        //是否超出范围
        boolean isOverflow = false;
        //进位
        int nTakeOver = 0;
        int length = numbers.length;
        for (int i = length - 1; i >= 0; i--) {
            int nSum = numbers[i] - '0' + nTakeOver;
            if (i == length - 1) {
                nSum++;
            }
            if (nSum >= 10) {
                if (i == 0) {
                    isOverflow = true;
                } else {
                    nSum -= 10;
                    nTakeOver = 1;
                    numbers[i] = (char) (nSum + '0');
                }

            } else {
                numbers[i] = (char) (nSum + '0');
                break;
            }
        }
        return isOverflow;
    }

    public static void main(String[] args) {
        print1ToMaxOfNDigits2(2);
    }
}
