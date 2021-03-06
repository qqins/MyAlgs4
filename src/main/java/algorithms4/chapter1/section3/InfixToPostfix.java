package algorithms4.chapter1.section3;


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
     * 结果为：2 3 * 2 1 - / 3 4 1 - * +
     */
    public static void infixToPostfix() {
//        String str = "( ( ( 2 * 3 ) / ( 2 - 1 ) ) + ( 3 * ( 4 - 1 ) ) )";
        String str = "2 * 3 / ( 2 - 1 ) + 3 * ( 4 - 1 )";
//        String str = "6 * ( 5 + ( 2 + 3 ) * 8 + 3 )";
        String[] strings = str.split("\\s+");
        Stack<String> sta = new Stack<>();
        StringBuffer sb = new StringBuffer();
        for (String s : strings) {
            /*if (s.equals("(")) {
            } else if (s.equals(")")) {
                sb.append(sta.pop() + " ");
            } else if (ISNUMBER.matcher(s).matches()) {
                sb.append(s + " ");
            } else {
                sta.push(s);
            }*/
            if (ISNUMBER.matcher(s).matches()) {
                sb.append(s + " ");
            } else {
                switch (s) {
                    case ")":
                        while (!sta.isEmpty() && (!"(".equals(sta.peek()))) {
                            sb.append(sta.pop() + " ");
                        }
                        sta.pop();
                        break;
                    case "(":
                        sta.push(s);
                        break;
                    case "^":
                        while (!sta.isEmpty() && (!("^".equals(sta.peek()) ||
                                "(".equals(sta.peek())))) {
                            sb.append(sta.pop());
                        }
                        sta.push(s);
                        break;
                    case "*":
                    case "/":
                        while (!sta.isEmpty() && (!"+".equals(sta.peek())) &&
                                (!"-".equals(sta.peek())) && (!"(".equals(sta.peek()))) {
                            sb.append(sta.pop() + " ");
                        }
                        sta.push(s);
                        break;
                    case "+":
                    case "-":
                        while (!sta.isEmpty() && (!"(".equals(sta.peek()))) {
                            sb.append(sta.pop() + " ");
                        }
                        sta.push(s);
                        break;
                    default:
                }
            }
        }
        while (!sta.isEmpty()) {
            sb.append(sta.pop() + " ");
        }
        System.out.println(sb.toString());
    }

    /**
     * 将后序表达式转为中序表达式
     * 结果为：(((2*3)/(2-1))+(3*(4-1)))
     */
    public static void postFixToInfix() {
//        String str = "2 3 * 2 1 - / 3 4 1 - * +";
        String str = "6 5 2 3 + 8 * + 3 + *";
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
     * 首先将其反转，将运算符压栈，遇到（就弹出
     * 结果为：+/*23-21*3-41
     */
    public static void infixToPrefix() {
//        String str = "( ( ( 2 * 3 ) / ( 2 - 1 ) ) + ( 3 * ( 4 - 1 ) ) )";
        String str = "2 * 3  / ( 2 - 1 ) + 3 * ( 4 - 1 )";
//        String str = "6 * ( 5 + ( 2 + 3 ) * 8 + 3 )";
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
            /*if (s.equals(")")) {
            } else if (s.equals("(")) {
                sb.append(sta.pop() + " ");
            } else if (ISNUMBER.matcher(s).matches()) {
                sb.append(s + " ");
            } else {
                sta.push(s);
            }*/

            if (ISNUMBER.matcher(s).matches()) {
                sb.append(s + " ");
            } else {
                switch (s) {
                    case "(":
                        while (!sta.isEmpty() && (!")".equals(sta.peek()))) {
                            sb.append(sta.pop() + " ");
                        }
                        sta.pop();
                        break;
                    case ")":
                        sta.push(s);
                        break;
                    case "^":
                        while (!sta.isEmpty() && (!("^".equals(sta.peek()) ||
                                "(".equals(sta.peek())))) {
                            sb.append(sta.pop());
                        }
                        sta.push(s);
                        break;
                    case "*":
                    case "/":
                        //由于字符串之前进行了反转，那么处于栈顶的元素优先级是最高的
                        sta.push(s);
                        break;
                    case "+":
                    case "-":
                        while (!sta.isEmpty() && (!")".equals(sta.peek()))) {
                            sb.append(sta.pop() + " ");
                        }
                        sta.push(s);
                        break;
                    default:
                }
            }
        }
        while (!sta.isEmpty()) {
            sb.append(sta.pop() + " ");
        }
        System.out.println(sb.reverse().toString());
    }

    /**
     * 将前序表达式转为中序表达式
     * 结果为：(((2*3)/(2-1))+(3*(4-1)))
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
        infixToPostfix();
        postFixToInfix();
        infixToPrefix();
    }
}
