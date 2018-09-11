package other.bignum;

/**
 * @author: Hello World
 * @date: 2018/9/9 16:36
 * <p>
 * 大数加法
 */
public class Add {
    public static String add(String s1, String s2) {
        char[] a = new StringBuilder(s1).reverse().toString().toCharArray();
        char[] b = new StringBuilder(s2).reverse().toString().toCharArray();
        int lenA = a.length;
        int lenB = b.length;
        int maxLen = lenA > lenB ? lenA : lenB;
        int[] res = new int[maxLen + 1];
        //将各位加起来
        for (int i = 0; i < maxLen + 1; i++) {
            int aint = i < lenA ? a[i] - '0' : 0;
            int bint = i < lenB ? b[i] - '0' : 0;
            res[i] = aint + bint;
        }
        //处理进位
        for (int i = 0; i < maxLen + 1; i++) {
            if (res[i] > 10) {
                res[i + 1] += res[i] / 10;
                res[i] = res[i] % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (res[maxLen] != 0) {
            sb.append(res[maxLen]);
        }
        for (int i = maxLen - 1; i >= 0; i--) {
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(add("123","456"));
    }
}
