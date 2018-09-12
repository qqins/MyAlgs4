package company.huawei;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/12 18:30
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                int value = map.get(str.charAt(i)) + 1;
                map.put(str.charAt(i), value);
            } else {
                map.put(str.charAt(i), 1);
            }
        }
        boolean flag = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (map.get(str.charAt(i)) == 1) {
                flag = true;
                System.out.println(str.charAt(i));
                break;
            }
        }
        if (!flag) {
            System.out.println("NULL");
        }
    }
}
