package company.jingdong;

/**
 * @author: Hello World
 * @date: 2018/9/9 19:16
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(B.c);
      /*  ClassLoader classLoader=ClassLoader.getSystemClassLoader();
        Class clazz = classLoader.loadClass("A");
        System.out.print("Test");
        clazz.forName("A");*/
    }
}

class Test2 {
    public static final String a = "JD";
    static {
        System.out.print("OK");
    }
}

class A {
//    public static String c = "C";
    static {
        System.out.print("A");
    }
}

class B extends A {
    static{
        System.out.print("B");
    }
    public static String c = "C";
}