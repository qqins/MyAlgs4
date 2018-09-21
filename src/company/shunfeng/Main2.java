package company.shunfeng;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/15 11:53
 * leetcode 68
 *
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 说明:
 *
 * 单词是指由非空格字符组成的字符序列。
 *
 * 每个单词的长度大于 0，小于等于 maxWidth。
 *
 * 输入单词数组 words 至少包含一个单词。
 *
 * 输入
 * 一个单词数组和一个长度 maxWidth
 *
 * 输出
 * 重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本
 *
 *
 * 样例输入
 * I'm,king,of,the,world
 * 3
 * 样例输出
 * I'm
 * king
 * of
 * the
 * world
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str=scanner.nextLine();
        int n=scanner.nextInt();
        String[] strings=str.split(",");
        List<String> res = fullJustify(strings, n);
        for (String s : res) {
            System.out.println(s);
        }
    }
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        char[] spaces = new char[maxWidth];
        for (int i = 0; i < maxWidth; i++){
            spaces[i] = ' ';
        }
        for (int start = 0; start < words.length;){
            int len = words[start].length();
            int end = start + 1;
            for (; end < words.length && len + 1 + words[end].length() <= maxWidth; end++){
                len += 1 + words[end].length();
            }
            StringBuilder newStr = new StringBuilder(maxWidth);
            newStr.append(words[start]);
            int slotNum = end - start - 1;
            start++;
            if (end != words.length && slotNum != 0){
                int evenNum = (maxWidth - (len - slotNum)) / slotNum;
                int leftNum = (maxWidth - (len - slotNum)) % slotNum;
                while (start < end){
                    newStr.append(spaces, 0, evenNum);
                    if (leftNum > 0){
                        newStr.append(' ');
                        leftNum--;
                    }
                    newStr.append(words[start]);
                    start++;
                }
            } else {
                while (start < end){
                    newStr.append(' ');
                    newStr.append(words[start]);
                    start++;
                }
                newStr.append(spaces, 0, maxWidth - newStr.length());
            }

            res.add(newStr.toString());
        }
        return res;
    }
}
