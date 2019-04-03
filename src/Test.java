public class Test {
    public static void main(String[] args) {
        int a = 0;
        System.out.println(++a > 0 || ++a > 0 ? a++ : ++a);
    }
}
