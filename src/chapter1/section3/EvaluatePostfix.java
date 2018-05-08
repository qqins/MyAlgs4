package chapter1.section3;

import java.util.Stack;
import java.util.regex.Pattern;

public class EvaluatePostfix {
    private static Pattern ISNUMBER = Pattern.compile("[0-9]+");

    /**
     * 计算后序表达式
     */
    public static void evaluatePostFix() {
        String str = "2 3 * 2 1 - / 3 4 1 - * +";
        String[] strings = str.split("\\s+");
        Stack<Integer> sta = new Stack<>();
        int result = 0;
        for (String s : strings) {
            if (ISNUMBER.matcher(s).matches()) {
                sta.push(Integer.parseInt(s));
            } else {
                int n1 = sta.pop();
                int n2 = sta.pop();
                int n3 = 0;
                if (s.equals("+")) {
                    n3 = n2 + n1;
                } else if (s.equals("-")) {
                    n3 = n2 - n1;
                } else if (s.equals("*")) {
                    n3 = n2 * n1;
                } else if (s.equals("/")) {
                    n3 = n2 / n1;
                }
                sta.push(n3);
            }
        }
        System.out.println(sta.pop());
    }

    /**
     * 计算前序表达式
     */
    public static void evaluatePreFix() {
        String str = "";

    }
    public static void main(String[] args) {
        evaluatePostFix();
    }
}
