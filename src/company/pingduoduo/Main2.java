package company.pingduoduo;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/8/30 19:43
 */
public class Main2 {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int heigt = scanner.nextInt();
        int wid = scanner.nextInt();
        char[][] array = new char[heigt][wid];
        String str;
        for (int i = 0; i < heigt; i++) {
            str = scanner.next();
            for (int j = 0; j < wid; j++) {
                array[i][j] = str.charAt(j);
            }
        }
        int[] temp = new int[wid];
        for (int i = heigt - 1; i >= 0; i--) {
            for (int j = 0; j < wid; j++) {
                if (array[i][j] == 'x') {
                    temp[j] = i;
                } else if (array[i][j] == 'o') {
                    if (temp[j] != 0) {
                        array[i][j] = '.';
                        array[--temp[j]][j] = 'o';

                    } else {
                        array[i][j] = '.';
                    }
                }
            }
        }
        for (int i = 0; i < heigt; i++) {
            for (int j = 0; j < wid; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }
}
