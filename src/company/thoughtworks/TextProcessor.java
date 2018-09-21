package company.thoughtworks;

import java.util.TreeSet;

/**
 * @author: Hello World
 * @date: 2018/9/20 21:05
 */
public class TextProcessor {
    public static String process(String text, int width) {
        if (text.length() < 10 || text.length() > 80) {
            return "ERROR: Width out of range!";
        }
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = 0;
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isLetter(text.charAt(i)) && text.charAt(i) != ' ') {
                return "ERROR: Invalid character detected!";
            }
            if (text.charAt(i) == ' ') {
                start = i;
                int cnt = 0;
                while (text.charAt(i) == ' ') {
                    end = start + cnt;
                    cnt++;
                    i++;
                }
                i = i - 1;
                word(text, width, start, end, sb);
                start = end + 1;
            } else {
                if (i == text.length() - 1 || text.charAt(i + 1) == ' ') {
                    word(text, width, start, i, sb);
                }
            }
        }
        return sb.toString();
    }

    private static void word(String text, int width, int start, int end, StringBuilder sb) {
        TreeSet<Integer> set = new TreeSet<>();
        int lineNumber = 0;
        for (int i = start; i <= end; i++) {
            sb.append(text.charAt(i));
            lineNumber = i / width + 1;
            set.add(lineNumber);
        }

        sb.append("(");
        int size = set.size();
        for (int i = 0; i < size; i++) {
            sb.append(set.pollFirst());
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append(");");
    }

    public static void main(String[] args) {
        System.out.println(process("The main theme of education in engineering school is learning to teach yourself",
                30));
        System.out.println(process("So   many whitespaces", 10));
        System.out.println(process("abcdefghijklmn", 1));
        System.out.println(process("0948yyjhteryt  y", 1));
    }
}
