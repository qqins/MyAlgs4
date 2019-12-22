package company.webank;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/18 17:31
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[6];
        int count = 0;
        for (int i = 0; i < 6; i++) {
            array[i] = scanner.nextInt();
            if (array[i] != 0) {
                count++;
            }
        }
        int[] a = new int[count];
        int index = 0;
        int max = 0;
        for (int i = 0; i < 6; i++) {
            if (array[i] != 0) {
                a[index++] = array[i] * (i + 1) * (i + 1);
                max += array[i] * (i + 1) * (i + 1);
            }
        }
        if (max % 36 == 0) {
            System.out.println(max / 36);
        } else {
            System.out.println(max / 36 + 1);
        }
        int[] dp = new int[max + 1];
        dp[0] = 1;
        for (int x : a) {
            for (int i = x; i <= max; i++) {
                dp[i] += dp[i - x];
            }
        }
        System.out.println(dp[max]);
    }
}
