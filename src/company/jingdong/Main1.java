package company.jingdong;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/9 18:48
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        boolean flag = true;
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] array = new int[m][2];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < 2; j++) {
                    array[i][j] = scanner.nextInt();
                }
            }
            if (flag) {
                System.out.println("YES");
                flag = false;
            } else {
                System.out.println("No");
                flag = true;
            }
        }
    }
}
