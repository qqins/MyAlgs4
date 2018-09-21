package company.shunfeng;

/**
 * @author: Hello World
 * @date: 2018/9/18 8:38
 */
public class Test1 {
    static int sum = 0;

    static boolean add(int i) {
        sum += i;
        return true;
    }

    public static void main(String[] args) {
        int i = 1;
        int j = 0;
        for (add(i); add(i) && i < 2; add(i)) {
            ++i;
            j = sum << 3;
        }
        System.out.println(j + sum);


        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");
        System.out.println(str1==str2);
        System.out.println(str1==str3);
        System.out.println(str1==str3.intern());
    }
}
