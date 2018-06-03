package algorithms4.chapter1.section3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @author Hello World
 * @date 2018.5.8
 */
public class EvaluatePostfix {
    private static final Logger LOGGER = LogManager.getLogger(EvaluatePostfix.class.getName());
    private static Pattern ISNUMBER = Pattern.compile("[0-9]+");

    /**
     * 计算后序表达式：2 3 * 2 1 - / 3 4 1 - * +
     * 其中序表达式：(((2*3)/(2-1))+(3*(4-1)))
     * 将数字压栈，一遇到运算符就将其取出运算，结果再压入栈
     */
    public static void evaluatePostFix() {
        String str = "2 3 * 2 1 - / 3 4 1 - * +";
        String[] strings = str.split("\\s+");
        Stack<Integer> sta = new Stack<>();
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
     * 计算前序表达式：+ / * 2 3 - 2 1 * 3 - 4 1
     * 其中序表达式为：(((2*3)/(2-1))+(3*(4-1)))
     * 先将其反转，将数字压入栈，一遇到运算符就取出数字计算，将计算结果压入栈
     */
    public static void evaluatePreFix() {
        String str = "+ / * 2 3 - 2 1 * 3 - 4 1";
        String[] strings = str.split("\\s+");
        for (int i = 0; i < strings.length / 2; i++) {
            String temp = strings[i];
            strings[i] = strings[strings.length - i - 1];
            strings[strings.length - i - 1] = temp;
        }
        Stack<Integer> sta = new Stack<>();
        for (String s : strings) {
            if (ISNUMBER.matcher(s).matches()) {
                sta.push(Integer.parseInt(s));
            } else {
                int n1 = sta.pop();
                int n2 = sta.pop();
                int n3 = 0;
                if (s.equals("+")) {
                    n3 = n1 + n2;
                } else if (s.equals("-")) {
                    n3 = n1 - n2;
                } else if (s.equals("*")) {
                    n3 = n1 * n2;
                } else if (s.equals("/")) {
                    n3 = n1 / n2;
                }
                sta.push(n3);
            }
        }
        LOGGER.debug(sta.pop());
    }

    public static void main(String[] args) {
        evaluatePreFix();
    }
}
