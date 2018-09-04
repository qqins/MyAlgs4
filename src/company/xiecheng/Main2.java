package company.xiecheng;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/4 20:30
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        int exmple = scan.nextInt();
        int[][] array = new int[num][3];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            array[i][0] = scan.nextInt();
            array[i][1] = scan.nextInt();
            array[i][2] = scan.nextInt();
            if (array[i][1] < exmple && array[i][2] > exmple) {
                list.add(array[i][0]);
            }
        }
        if (list.size() == 0) {
            System.out.println("null");
        } else {

            list.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            for (int i : list) {
                System.out.println(i);
            }
        }
    }
}
