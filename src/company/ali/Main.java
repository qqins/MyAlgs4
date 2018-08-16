package company.ali;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/8/16 10:56
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt();
        String[][] strings=new String[num][2];
        for (int i = 0; i < num; i++) {
            strings[i]=scanner.nextLine().trim().split(",");
        }
        int[][] point= new int[num][2];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < 2; j++) {
                point[i][j]=Integer.parseInt(strings[i][j]);
            }
        }

    }
}
