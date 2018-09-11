package company.oppo;

/**
 * @author: Hello World
 * @date: 2018/9/10 19:05
 */
public class A {
    private static int i=1;
    static {
        i=2;
    }

    public static int next() {
      return   i++;
    }

    public static void main(String[] args) {
        A a=new A();
        A b=new A();
        A.next();
        a.next();
        b.next();
        System.out.println(a.next());
    }
}
