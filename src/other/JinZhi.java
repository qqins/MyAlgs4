package other;

/**
 * @author: Hello World
 * @date: 2018/9/20 19:54
 */
public class JinZhi {
    /**
     * 10进制整数转换为其他进制字符串
     */
    public static String itos(int num, int base) {
        int divisor = 1;
        byte[] table = "0123456789ABCDEF".getBytes();
        String result = "";
        while (divisor * base <= num) {
            divisor *= base;
        }
        for (; divisor >= 1; divisor /= base) {
            result += (char) table[num / divisor];
            num %= divisor;
        }
        return result;
    }

    /**
     * 其他进制字符串转换为10进制整数
     */
    public static int stoi(String src, int base) {
        int digit, result = 0;
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (c >= 'a') {
                digit = c - 'a' + 10;
            } else if (c >= 'A') {
                digit = c - 'A' + 10;
            } else {
                digit = c - '0';
            }
            result = base * result + digit;
        }
        return result;
    }
}
