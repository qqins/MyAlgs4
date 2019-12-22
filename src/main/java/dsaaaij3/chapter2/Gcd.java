package dsaaaij3.chapter2;

/**
 * @author: Hello World
 * @date: 2018/10/8 15:48
 * <p>
 * 欧几里得算法，计算最大公因数
 */
public class Gcd {
    public static int gcd(int m, int n) {
        //若m<n,则第一次循环时会交换，使得m为最大数，n为最小数
        while (n != 0) {
            int rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println(gcd(1989, 1590));
        System.out.println(gcd(3, 5));
    }
}
