package company.shenxinfu;

/**
 * @author: Hello World
 * @date: 2018/9/21 19:08
 */
public class Test {
    public static void main(String[] args) {
        int j=~999999;
        System.out.println(j&=~(1<<0));
    }
}
