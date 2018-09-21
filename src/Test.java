import java.util.Date;

public class Test {
    static int x = 10;

    static {
        x += 5;
    }

    public static void main(String[] args) {
        System.out.println("X=" + x);
        Date data=new Date();
        System.out.println(data.getTime());
        System.out.println(data.getTime());
    }

    static {
        x /= 3;
    }
}
