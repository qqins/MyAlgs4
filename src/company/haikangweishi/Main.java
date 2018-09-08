package company.haikangweishi;

/**
 * @author: Hello World
 * @date: 2018/9/8 15:50
 */
public class Main {
    private int index = 0;

    public boolean isNumeric(String src) {
        if (src.length() < 1) {
            return false;
        }

        boolean flag = scanInteger(src);

        if (index < src.length() && src.charAt(index) == '.') {
            index++;
            flag = scanUnsignedInteger(src) || flag;
        }

        if (index < src.length() && (src.charAt(index) == 'E' || src.charAt(index) == 'e')) {
            index++;
            flag = flag && scanInteger(src);
        }

        return flag && index == src.length();

    }

    private boolean scanInteger(String src) {
        if (index < src.length() && (src.charAt(index) == '+' || src.charAt(index) == '-')) {
            index++;
        }
        return scanUnsignedInteger(src);
    }

    private boolean scanUnsignedInteger(String src) {
        int start = index;
        while (index < src.length() && src.charAt(index) >= '0' && src.charAt(index) <= '9') {
            index++;
        }
        return start < index;
    }
}
