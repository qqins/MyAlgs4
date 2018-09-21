package company.shenxinfu;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/21 19:31
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Queue<String> queue = new LinkedList<>();
        boolean flag = true;
        int pre = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                if (i == str.length() - 1 || str.charAt(i + 1) != ch) {
                    int count = i - pre + 1;
                    String s = String.valueOf(count) + ch;
                    if (!queue.contains(s)) {
                        queue.offer(s);
                    }
                    pre = i + 1;
                }
            } else {
                flag = false;
                break;
            }
        }
        if (flag) {
            for (String s : queue) {
                System.out.print(s + " ");
            }
        } else {
            System.out.println(0);
        }
    }
}
