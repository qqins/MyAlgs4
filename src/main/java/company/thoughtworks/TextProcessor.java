package company.thoughtworks;

import java.util.TreeSet;

/**
 * @author: Hello World
 * @date: 2018/9/21 21:34
 */
public class TextProcessor {
    /**
     * @param text  输入的待处理的文本
     * @param width 文本排版的宽度
     * @return 返回处理的结果
     */
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
            //处理空格
            if (text.charAt(i) == ' ') {
                start = i;
                //计算有多少连续空格
                int cnt = 0;
                //若有连续多个空格，则将这几个连续的空格视为一个单词
                while (i < text.length() && text.charAt(i) == ' ') {
                    end = start + cnt;
                    cnt++;
                    i++;
                }
                //之所以要减1，是因为跳出while循环后i已经指向下一个不为空格的字符，若不减1，在外层for循环中i又会加1，这样就会轮空一个字符
                i = i - 1;
                word(text, width, start, end, sb);
                //下一个单词的起始位置
                start = end + 1;
            } else {
                //若轮到最后一个字符或下一个字符为空格，则当前字符为当前单词的最后一个字母
                if (i == text.length() - 1 || text.charAt(i + 1) == ' ') {
                    word(text, width, start, i, sb);
                }
            }
        }
        return sb.toString();
    }

    /**
     * word方法用于提取文本中的每个单词，并计算出每个单词所处的行号或者横跨哪几个行号
     *
     * @param text  输入的待处理文本
     * @param width 文本排版宽度
     * @param start 文本中单词的起始位置
     * @param end   文本中单词的终止位置
     * @param sb    使用StringBuilder作为装载结果的容器
     */
    private static void word(String text, int width, int start, int end, StringBuilder sb) {
        //使用TreeSet来存放每个单词中每个字符的行号，一是保证行号的有序性，另外就是保证行号的唯一性
        TreeSet<Integer> set = new TreeSet<Integer>();
        //定义行号
        int lineNumber = 0;
        for (int i = start; i <= end; i++) {
            sb.append(text.charAt(i));
            //单词中的字符所在下标除以宽度再加上1就是该字符所在的行号（加1是确保行号从1开始）
            lineNumber = i / width + 1;
            set.add(lineNumber);
        }

        sb.append("(");
        int size = set.size();
        //set集合中的所有元素就是该单词所在（跨）的行号
        for (int i = 0; i < size; i++) {
            sb.append(set.pollFirst());
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append(");");
    }

    public static void main(String[] args) {
        System.out.println("文本为：The main theme of education in engineering school is learning to teach " +
                "yourself，宽度为30的输出：");
        System.out.println(process("The main theme of education in engineering school is learning to teach yourself",
                30));
        System.out.println("=========================================================================================");
        System.out.println("文本为：So   many whitespaces，宽度为10的输出：");
        System.out.println(process("So   many whitespaces", 10));
        System.out.println("=========================================================================================");
        System.out.println("文本为：qinshengqs，宽度为1的输出为：");
        System.out.println(process("qinshengqs", 1));
        System.out.println("=========================================================================================");
        System.out.println("文本为：a b c d e f g h i j k，宽度为10的输出：");
        System.out.println(process("a b c d e f g h i j k", 10));
        System.out.println("=========================================================================================");
        System.out.println("文本长度为9的输出：");
        //字符串长度为9
        System.out.println(process("abcdefghi", 1));
        System.out.println("=========================================================================================");
        System.out.println("文本长度为81的输出：");
        //字符串长度为81
        System.out.println(process("aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhi", 1));
        System.out.println("=========================================================================================");
        System.out.println("文本中含有非英文字母的输出：");
        //字符串中含有非字母字符
        System.out.println(process("abcd123efg", 1));
    }
}
