package company.pingduoduo;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author: Hello World
 * @date: 2018/8/30 20:08
 */
public class Main4 {
    private static boolean flag;
    private static StringBuilder sb = new StringBuilder();
    private static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int l = scanner.nextInt();
        int[][] vis = new int[l][26];
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            for (int j = 0; j < l; j++) {
                vis[j][s.charAt(j) - 'A'] = 1;
            }
            set.add(s);
        }
        dfs(vis, 0, l);
        if (!flag) {
            System.out.println("-");
        }
    }

    private static void dfs(int[][] vis, int len, int l) {
        if (flag) {
            return;
        }
        if (len == l) {
            if (!set.contains(sb.toString())) {
                System.out.println(sb.toString());
                flag = true;
            }
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (vis[len][i] == 1) {
                sb.append((char) ('A' + i));
                dfs(vis, len + 1, l);
                sb.deleteCharAt(len);
            }
        }
    }
}
