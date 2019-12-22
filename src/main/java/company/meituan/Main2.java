package company.meituan;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/6 20:50
 * <p>
 * (滑动窗口变种？)
 * 小明拿到了一个数列a1 , a2 , ... an ，小明想知道存在多少个区间[l,r]同时满足下列两个条件：
 * <p>
 * 1、r-l+1=k;
 * <p>
 * 2、在al, al+1,...ar中，存在一个数至少出现了 t 次。
 * <p>
 * 输出满足条件的区间个数。
 * <p>
 * 输入
 * 输入第一行三个整数n,k,t(1≤n,k,t≤105。
 * 第二行 n 个整数，a1 , a2 , ... an (1≤ai≤105)。
 * <p>
 * 输出
 * 输出一个数，问题的答案。
 * <p>
 * <p>
 * 样例输入
 * 5 3 2
 * 3 1 1 1 2
 * 样例输出
 * 3
 * <p>
 * Hint
 * 区间[1,3]中1出现了2次，区间[2,4]中1出现了3次，区间[3,5]中1出现了2次。所以一共有3个区间满足条件。
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int t = scanner.nextInt();
        int[] array = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            array[i] = scanner.nextInt();
        }
        int result = 0;
        for (int i = k; i < n + 1; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i - k + 1; j <= k; j++) {
                map.put(array[j], 1);
            }
            if (k - map.size() <= t) {
                result++;
            }
        }
        System.out.println(result);
    }
}
