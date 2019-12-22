package swordoffer.chapter2;

/**
 * @author: Hello World
 * @date: 2018/8/1 20:05
 * <p>
 * 面试题5: 替换空格
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class Question5 {
    public String replaceSpace(StringBuffer str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == ' ') {
                str.append("  ");
            }
        }
        int p = length - 1;
        int q = str.length() - 1;
        while (p >= 0 && q > p) {
            char c = str.charAt(p--);
            if (c == ' ') {
                str.setCharAt(q--, '0');
                str.setCharAt(q--, '2');
                str.setCharAt(q--, '%');
            } else {
                str.setCharAt(q--, c);
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("We are happy");
        Question5 question5=new Question5();
        String s = question5.replaceSpace(str);
        System.out.println(s);
    }
}
