import java.lang.reflect.Array;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String str1 = "ACTGACG";
        String str2 = "TGACGAC";
        System.out.println(str1.length() == str2.length() &&
                str1.concat(str1).indexOf(str2) >= 0);
        String str = "fheio  groei   jfie" +
                "jgoir irj     jgi";
        String[] ss = str.split("\\s+");
        System.out.println(Arrays.toString(ss));
    }
}
