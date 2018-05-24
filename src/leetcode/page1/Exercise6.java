package leetcode.page1;

/**
 * @author: Hello World
 * @date: 2018/5/24 19:57
 * <p>
 * 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后从左往右，逐行读取字符："PAHNAPLSIIGYIR"
 * <p>
 * 实现一个将字符串进行指定行数变换的函数:
 * string convert(string s, int numRows);
 * <p>
 * 示例 1:
 * 输入: s = "PAYPALISHIRING", numRows = 3
 * 输出: "PAHNAPLSIIGYIR"
 * <p>
 * 示例 2:
 * 输入: s = "PAYPALISHIRING", numRows = 4
 * 输出: "PINALSIGYAHRPI"
 * 解释:
 * <p>
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
public class Exercise6 {
    /**
     * 找规律题，当numRows=4时，其对应的下标为：
     * 0    6       12
     * 1  5 7    11 13
     * 2 4  8 10
     * 3    9
     * 可以看出，每一竖列为等差竖列，
     * 当n==0 || n==numRows时，行之间相隔2*(numRows-1)
     * 初始时, step1=i
     * 然后以step1=step1+2*(numRows-1)往后循环
     *
     * 若n不在头尾处，将每行的相邻的两个数行标设为step1,step2
     * 初始时, step1=i, step2=2*(numRows-1)-i
     * 然后以step1=step1+2*(numRows-1), step2=step2+2*(numRows-1)往后循环
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        StringBuffer sb = new StringBuffer();
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
        for (int i = 0; i < numRows; i++) {
            int step1 = i;
            int step2 = 2 * (numRows - 1) - i;
            while (step1 < s.length() || step2 < s.length()) {
                if (i == 0 || i == numRows - 1) {
                    sb.append(s.charAt(step1));
                } else {
                    sb.append(s.charAt(step1));
                    /**
                     * 由于step1总比step2小, 所以还要额外判断step2是否
                     * 比s长度小
                     */
                    if (step2 < s.length()) {
                        sb.append(s.charAt(step2));
                    }
                }
                step1 = step1 + 2 * (numRows - 1);
                step2 = step2 + 2 * (numRows - 1);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        System.out.println(convert(s, 5));
    }
}
