package company.fourthreeninenine;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/17 11:02
 * 小九设计了一款迷宫游戏，迷宫地图可以看做一个X*Y（X<100,Y<100）的网格，豆娃（游戏内的角色）要在此网格内从左上角到右下角，
 * 只能走格点且只能向右或者向下走。请设计一个算法，计算豆娃有多少种走迷宫的方法。
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int[][] dp = new int[x][y];
        for (int i = 1; i < y; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < x; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        System.out.println(dp[x - 1][y - 1]);
    }
}
