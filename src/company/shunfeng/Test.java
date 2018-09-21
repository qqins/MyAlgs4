package company.shunfeng;

/**
 * @author: Hello World
 * @date: 2018/9/15 10:15
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(val());
    }

    public static int val() {
        int num=5;
        try {
            num = num / 0;
        } catch (Exception e) {
            num=10;
        }finally {
            num=15;
        }
        return num;
    }
}
