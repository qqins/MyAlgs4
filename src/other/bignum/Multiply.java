package other.bignum;

/**
 * @author: Hello World
 * @date: 2018/9/9 18:26
 */
public class Multiply {
    public static String multiply(String s1, String s2) {
        char signA = s1.charAt(0);
        char signB = s2.charAt(0);
        char sign = '+';
        if (signA == '-' && signB != '-') {
            s1 = s1.substring(1);
            sign = signA;
        }
        if (signB == '-' && signA != '-') {
            s2 = s2.substring(1);
            sign = signB;
        }
        char[] a = new StringBuilder(s1).reverse().toString().toCharArray();
        char[] b = new StringBuilder(s2).reverse().toString().toCharArray();
        int lenA = a.length;
        int lenB = b.length;
        int[] res = new int[lenA + lenB];
        //将各位相乘累加
        for (int i = 0; i < lenA; i++) {
            for (int j = 0; j < lenB; j++) {
                res[i + j] += (a[i] - '0') * (b[j] - '0');
            }
        }
        //处理进位
        for (int i = 0; i < res.length; i++) {
            if (res[i] > 10) {
                res[i + 1] += res[i] / 10;
                res[i] = res[i] % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (res[res.length - 1] != 0) {
            sb.append(res[res.length - 1]);
        }
        for (int i = res.length - 2; i >= 0; i--) {
            sb.append(res[i]);
        }
        if (sign == '-') {
            sb.insert(0, sign);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("-123","456"));
    }
}
