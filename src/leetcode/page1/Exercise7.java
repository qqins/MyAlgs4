package leetcode.page1;

/**
 * @author: Hello World
 * @date: 2018/5/28 19:02
 * <p>
 * 7. 反转整数
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 * <p>
 * 示例 1:
 * 输入: 123
 * 输出: 321
 * <p>
 * 示例 2:
 * 输入: -123
 * 输出: -321
 * <p>
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * <p>
 * 注意:
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 */
public class Exercise7 {
    public static int reverse(int x) {
        long a = 0, b = 0;
        long y = 0, t = x;
        if (t < 0) {
            t = Math.abs(t);
        }
        a = t / 10;
        b = t % 10;
        t = a;
        while (a > 0) {
            y = b + y;
            a = t / 10;
            b = t % 10;
            y = y * 10;
            t = a;
        }
        y = y + b;
        if (x < 0) {
            if ((-1 * y) < Integer.MIN_VALUE) {
                return 0;
            }
            return (int) (-1 * y);
        } else {
            if (y > Integer.MAX_VALUE) {
                return 0;
            }
            return (int) y;
        }
    }

    public static int reverse1(int x) {
        long t = x, a = 0, b = 0;
        StringBuilder sb = new StringBuilder();
        if (t < 0) {
            t = Math.abs(t);
        }
        a = t / 10;
        b = t % 10;
        t = a;
        sb.append(b);
        while (a > 0) {
            a = t / 10;
            b = t % 10;
            t = a;
            sb.append(b);
        }
        long y = Long.parseLong(sb.toString());
        if (x < 0) {
            if ((-1 * y) < Integer.MIN_VALUE) {
                return 0;
            }
            return (int) (-1 * y);
        } else {
            if (y > Integer.MAX_VALUE) {
                return 0;
            }
            return (int) y;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
        System.out.println(reverse1(-2147483648));
    }
}
