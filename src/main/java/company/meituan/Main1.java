package company.meituan;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/6 20:49
 * <p>
 * 给定一张包含N个点、N-1条边的无向连通图，节点从1到N编号，每条边的长度均为1。
 * 假设你从1号节点出发并打算遍历所有节点，那么总路程至少是多少？
 * <p>
 * 输入
 * 第一行包含一个整数N，1≤N≤105。
 * <p>
 * 接下来N-1行，每行包含两个整数X和Y，表示X号节点和Y号节点之间有一条边，1≤X，Y≤N。
 * <p>
 * 输出
 * 输出总路程的最小值。
 * <p>
 * <p>
 * 样例输入
 * 4
 * 1 2
 * 1 3
 * 3 4
 * 样例输出
 * 4
 * <p>
 * Hint
 * 按1->2->1->3->4的路线遍历所有节点，总路程为4。
 */
public class Main1 {
    /**
     * 方法1
     */
    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] res = new int[n + 1];
        int maxDepth = 0;
        for (int i = 0; i < (n - 1); i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            res[y] = res[x] + 1;
            if (res[y] > maxDepth) {
                maxDepth = res[y];
            }
        }
        System.out.println((n - 1) * 2 - maxDepth);
    }*/

    /**
     * 方法2
     */
    private static int maxDepth = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] array = new int[n + 1][n + 1];
        for (int i = 0; i < n - 1; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            array[x][y] = 1;
        }
        int result = dfs(array, 1, 0);
        System.out.println(2 * (n - 1) - result);
    }

    private static int dfs(int[][] array, int row, int depth) {
        for (int j = 1; j < array[0].length; j++) {
            if (array[row][j] != 0) {
                maxDepth = dfs(array, j, depth + 1);
            }
        }
        return maxDepth > depth ? maxDepth : depth;
    }
}
