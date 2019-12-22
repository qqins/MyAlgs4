package company.ruijie;

import java.util.ArrayList;

/**
 * @author: Hello World
 * @date: 2018/9/21 17:53
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<String> l=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            l.add(String.valueOf(i));
        }
        l.stream().filter(e->!e.equals("5")).forEach(System.out::print);
    }
}
