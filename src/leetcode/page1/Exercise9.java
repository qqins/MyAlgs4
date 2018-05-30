package leetcode.page1;

/**
 * @author: Hello World
 * @date: 2018/5/30 18:37
 * <p>
 * 9, 回文数
 * <p>
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * 输入: 121
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 */
public class Exercise9 {
    public static boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        if (str.length() % 2 == 0) {
            for (int i = str.length() / 2, j = str.length() / 2 - 1; i < str.length(); i++, j--) {
                if (str.charAt(i) != str.charAt(j)) {
                    return false;
                }
            }
        } else {
            for (int i = str.length() / 2, j = str.length() / 2; i < str.length(); i++, j--) {
                if (str.charAt(i) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 该方法的复杂度为O(log10 n)
     * @param x
     * @return
     */
    public static boolean isPalindrome1(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertNum = 0;
        /**
         * 如1221, 算出其后半段数为12与前半段数12相等
         * 当后半段数大于前半段数时结束循环
         */
        while (revertNum < x) {
            revertNum = revertNum * 10 + x % 10;
            x /= 10;
        }
        /**
         * 前者是x为偶数的情况
         * 后者是x为奇数的情况
         */
        return x == revertNum || x == revertNum / 10;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome1(101101));
    }
}
