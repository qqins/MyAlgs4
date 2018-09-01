package swordoffer.chapter4;

/**
 * @author: Hello World
 * @date: 2018/8/30 11:03
 * <p>
 * 面试题43：整数中1出现的次数（从1到n整数中1出现的次数）
 * 输入一个整数n, 求1~n这n个整数的十进制表示中1出现的次数。
 * 例如，输入12,1~12这些整数中包含1的数字有1, 10, 11和12,
 * 1一共出现5次
 */
public class Question43 {
    /**
     * 分别算出各位，十位，百位...中1的个数
     * 复杂度：O(logN)
     */
    public static int numberOf1Between1AndNSolution(int n) {
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) {
            int a = n / m;
            int b = n % m;
            //+8是为了进位，若该位>=2, 则加8往前进一位才能包含前面的1
            //如22，其个位1有3个(1,11,21)，若不加8，得出的结果就是2
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }

    /**
     * 暴力
     * 复杂度：O(NlogN)
     */
    public int numberOf1Between1AndNSolution2(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += numbrs(i);
        }
        return result;
    }

    private int numbrs(int n) {
        int sum = 0;
        while (n > 0) {
            if (n % 10 == 1) {
                sum++;
            }
            n = n / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(numberOf1Between1AndNSolution(12145));
    }
}
