package company.jingdong;

/**
 * @author: Hello World
 * @date: 2018/9/9 19:25
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("A");
        new Main();
        new Main();
    }

    public Main() {
        System.out.println("B");
    }
    {
        System.out.println("C");
    }
    static {
        System.out.println("D");
    }

    /*public static void main(String[] args) {
        System.out.print(fun1());
    }

    public static String fun1() {
        try {
            System.out.print("A");
            return fun2();
        }finally {
            System.out.print("B");
        }
    }

    public static String fun2() {
        System.out.print("C");
        return "D";
    }*/
}
