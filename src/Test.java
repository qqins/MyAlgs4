import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class.getName());

    public static void main(String[] args) {
        String string = "abcdef";
        boolean flag = true;
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int x = string.indexOf(chars[i]);
            if (x != i) {
                flag = false;
                break;
            }
        }
        System.out.println(flag);
        System.out.println("".length());
    }
}
