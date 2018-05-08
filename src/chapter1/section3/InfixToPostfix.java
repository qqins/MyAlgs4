package chapter1.section3;


import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Pattern;

public class InfixToPostfix {
    /**
     * 利用正则表达式判断是否为整数
     * 以下只能匹配非负整数
     */
    private static Pattern ISNUMBER = Pattern.compile("[0-9]+");

    /**
     * 将中序表达式转为后序表达式
     */
    public static void infixToPostfix() {
        String str = "( ( ( 2 * 3 ) / ( 2 - 1 ) ) + ( 3 * ( 4 - 1 ) ) )";
        String[] strings = str.split("\\s+");
        Stack<String> sta = new Stack<>();
        StringBuffer sb = new StringBuffer();
        for (String s : strings) {
            if (s.equals("(")) {
            } else if (s.equals("+")) {
                sta.push("+");
            } else if (s.equals("-")) {
                sta.push("-");
            } else if (s.equals("*")) {
                sta.push("*");
            } else if (s.equals("/")) {
                sta.push("/");
            } else if (s.equals(")")) {
                sb.append(sta.pop() + " ");
            } else {
                sb.append(s + " ");
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * 将后序表达式转为中序表达式
     */
    public static void postFixToInfix() {
        String str = "2 3 * 2 1 - / 3 4 1 - * +";
        String[] strings = str.split("\\s+");
        Stack<String> num = new Stack<>();
        for (String s : strings) {
            if (ISNUMBER.matcher(s).matches()) {
                num.push(s);
            } else {
                String s1 = num.pop();
                String s2 = num.pop();
                String s3 = "(" + s2 + s + s1 + ")";
                num.push(s3);
            }
        }
        System.out.println(num.pop());
    }

    /**
     * 将中序表达式转为前序表达式
     * 首先将其反转
     * +/*23-21*3-41
     */
    public static void infixToPrefix() {
        String str = "( ( ( 2 * 3 ) / ( 2 - 1 ) ) + ( 3 * ( 4 - 1 ) ) )";
        String[] strings = str.split("\\s+");
        for (int i = 0; i < strings.length / 2; i++) {
            String temp = strings[i];
            strings[i] = strings[strings.length - i - 1];
            strings[strings.length - i - 1] = temp;
        }
        System.out.println(Arrays.toString(strings));
        Stack<String> sta = new Stack<>();
        StringBuffer sb = new StringBuffer();
        for (String s : strings) {
            if (s.equals(")")) {
            } else if (s.equals("+")) {
                sta.push("+");
            } else if (s.equals("-")) {
                sta.push("-");
            } else if (s.equals("*")) {
                sta.push("*");
            } else if (s.equals("/")) {
                sta.push("/");
            } else if (s.equals("(")) {
                sb.append(sta.pop() + " ");
            } else {
                sb.append(s + " ");
            }
        }
        System.out.println(sb.reverse().toString());
    }

    /**
     * 将前序表达式转为中序表达式
     */
    public static void preFixToInfix() {
        String str = "+ / * 2 3 - 2 1 * 3 - 4 1";
        String[] strings = str.split("\\s+");
        for (int i = 0; i < strings.length / 2; i++) {
            String temp = strings[i];
            strings[i] = strings[strings.length - i - 1];
            strings[strings.length - i - 1] = temp;
        }
        Stack<String> sta = new Stack<>();
        for (String s : strings) {
            if (ISNUMBER.matcher(s).matches()) {
                sta.push(s);
            } else {
                String s1 = sta.pop();
                String s2 = sta.pop();
                String s3 = "(" + s1 + s + s2 + ")";
                sta.push(s3);
            }
        }
        System.out.println(sta.pop());
    }

    public static void main(String[] args) {
        preFixToInfix();
    }
}
