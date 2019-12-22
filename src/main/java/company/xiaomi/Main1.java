package company.xiaomi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/20 18:40
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String str = "";
        while (!(str = scanner.nextLine()).equals("END")) {
            String[] strings = str.split("#");
            int num = nToTen(strings[1], Integer.parseInt(strings[0]));
            if (map.containsKey(num)) {
                map.put(num, null);
            } else {
                map.put(num, str);
            }
            sb.append(str + " ");
        }
        String[] strings = sb.toString().trim().split(" ");
        boolean flag = true;
        for (int i = 0; i < strings.length; i++) {
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (entry.getValue() != null && entry.getValue().equals(strings[i])) {
                    flag = false;
                    System.out.println(entry.getValue());
                }
            }
        }
        if (flag) {
            System.out.println("None");
        }

    }

    private static int nToTen(String src, int base) {
        int digit, result = 0;
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (c >= 'a') {
                digit = c - 'a' + 10;
            } else if (c >= 'A') {
                digit = c - 'A' + 10;
            } else {
                digit = c - '0';
            }
            result = base * result + digit;
        }
        return result;
    }
}
