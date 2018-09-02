package swordoffer.chapter5;

/**
 * @author: Hello World
 * @date: 2018/8/30 16:29
 * <p>
 * 面试题44：数字序列中某一位的数字
 * 数字以 0123456789101112131415... 的格式序列化到一个字符串中，
 * 求这个字符串的第index 位。
 */
public class Question44 {
    public int digitAtIndex(int index) {
        if (index < 0) {
            return -1;
        }
        int digit = 1;
        while (true) {
            int amount = getAmountOfDigit(digit);
            int totalAmount = amount * digit;
            if (index < totalAmount) {
                return digitAtIndex(index, digit);
            }
            index -= totalAmount;
            digit++;
        }
    }

    /**
     *
     * @param digit 位数
     * @return  digit位数的数字共有多少个
     * 如digit=2, return 90
     */
    private int getAmountOfDigit(int digit) {
        if (digit == 1) {
            return 10;
        }
        return (int) Math.pow(10, digit - 1) * 9;
    }

    /**
     *
     * @param index 要求的index位
     * @param digit 所在的位数
     * @return  在digit位数组成的字符串中，第index个数
     */
    private int digitAtIndex(int index, int digit) {
        int number = beginNumber(digit) + index / digit;
        int remain = index % digit;
        return (number + "").charAt(remain) - '0';
    }

    /**
     *
     * @param digit 位数
     * @return  digit位数的起始数字
     * 如digit=2, return 10
     */
    private int beginNumber(int digit) {
        if (digit == 1) {
            return 0;
        }
        return (int) Math.pow(10, digit - 1);
    }
}
