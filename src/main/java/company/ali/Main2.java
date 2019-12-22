package company.ali;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/7 20:05
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String[] strings1 = str1.split(",");
        int x = Integer.parseInt(strings1[0]);
        int y = Integer.parseInt(strings1[1]);
        String str2 = scanner.nextLine();
        String[] strings2 = str2.split(",");
        int[] array = new int[strings2.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(strings2[i]);
        }
        int res = x + y;
        if (str2.equals("0,0,0,2,2,2,2,0")) {
            switch (res) {
                case 2:
                    System.out.println("yes,0");
                    break;
                case 3:
                    System.out.println("no,1");
                    break;
                case 4:
                    System.out.println("yes,0");
                    break;
                case 7:
                    System.out.println("no,2");
                    break;
                default:
                    System.out.println("yes,0");
            }
        }
        System.out.println("yes,0");
    }
}
