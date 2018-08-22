package swordoffer.chapter3;

/**
 * @author: Hello World
 * @date: 2018/8/19 21:39
 *
 * 面试题20：表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class Question20 {
    public boolean isNumeric(char[] str) {
        if (str==null) {
            return false;
        }

        /**
         * []?或()?代表括号内是可选的
         * d* 表示0个或多个数字
         * d+ 表示1个以上数字
         */
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }
}
