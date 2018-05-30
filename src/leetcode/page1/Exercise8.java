package leetcode.page1;

/**
 * @author: Hello World
 * @date: 2018/5/29 19:01
 * <p>
 * 8, 字符串转整数
 * <p>
 * 实现 atoi，将字符串转为整数。
 * 在找到第一个非空字符之前，需要移除掉字符串中的空格字符。如果第一个非空字符是正号或负号，
 * 选取该符号，并将其与后面尽可能多的连续的数字组合起来，这部分字符即为整数的值。如果第一个非空字符是数字，
 * 则直接将其与之后连续的数字字符组合起来，形成整数。
 * 字符串可以在形成整数的字符后面包括多余的字符，这些字符可以被忽略，它们对于函数没有影响。
 * 当字符串中的第一个非空字符序列不是个有效的整数；或字符串为空；或字符串仅包含空白字符时，则不进行转换。
 * 若函数不能执行有效的转换，返回 0。
 * <p>
 * 说明：
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。
 * 如果数值超过可表示的范围，则返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。
 * <p>
 * 示例 1:
 * 输入: "42"
 * 输出: 42
 * <p>
 * 示例 2:
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * <p>
 * 示例 3:
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * <p>
 * 示例 4:
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * <p>
 * 示例 5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−2^31) 。
 */
public class Exercise8 {
    public static int myAtoi(String str) {
        long result = 0;
        long t = 0;
        boolean flag = false;
        str = str.trim();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '-' || str.charAt(i) == '+') {
                /**
                 * +-2应输出0
                 * 保证数字前的符号不被过滤
                 */
                if (i < str.length() - 1 && Character.isDigit(str.charAt(i + 1))) {
                    continue;
                } else {
                    break;
                }
            }
            if (Character.isDigit(str.charAt(i))) {
                if (i > 0 && str.charAt(i - 1) == '-') {
                    flag = true;
                }
                result = str.charAt(i) - '0' + t;
                t = result * 10;
                if (!flag && result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (flag && (-1*result) < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                break;
            }
            /**
             * 0-1应输出为0而不是-1
             * 确保后面的为数字
             */
            if (i < str.length() - 1 && !Character.isDigit(str.charAt(i + 1))) {
                break;
            }
        }
        if (flag) {
            result = -1 * result;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("9223372036854775808"));
    }
}
