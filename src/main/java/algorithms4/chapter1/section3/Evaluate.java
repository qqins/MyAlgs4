package algorithms4.chapter1.section3;

import edu.princeton.cs.algs4.Stack;

public class Evaluate {
    public static void main(String[] args) {
        String str = "(1+((2+3)*(4*5)))";
        Stack<Character> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (c == '(') {
                ;
            } else if (c == '+') {
                ops.push(c);
            } else if (c == '-') {
                ops.push(c);
            } else if (c == '*') {
                ops.push(c);
            } else if (c == '/') {
                ops.push(c);
            } else if (c == ')') {
                char op = ops.pop();
                double val = vals.pop();
                if (op == '+') {
                    val = vals.pop() + val;
                } else if (op == '-') {
                    val = vals.pop() - val;
                } else if (op == '*') {
                    val = vals.pop() * val;
                } else if (op == '/') {
                    val = vals.pop() / val;
                }
                vals.push(val);
            } else {
                vals.push(Double.parseDouble(Character.toString(c)));
            }
        }
        System.out.println(vals.pop());
    }
}
