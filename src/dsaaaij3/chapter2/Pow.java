package dsaaaij3.chapter2;

/**
 * @author: Hello World
 * @date: 2018/10/8 16:09
 * <p>
 * 整数幂运算
 */
public class Pow {
    public static long pow(long x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if ((n & 1) == 0) {
            return pow(x * x, n >> 1);
        } else {
            return pow(x * x, n >> 1) * x;
        }
    }

    public static void main(String[] args) {
        System.out.println(pow(2,10));
    }
}
