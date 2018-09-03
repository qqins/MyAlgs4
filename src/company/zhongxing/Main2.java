package company.zhongxing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: Hello World
 * @date: 2018/9/3 19:30
 */
public class Main2 {
    static List<String> result = new ArrayList<>();
    static Set<Integer> set = new HashSet<>();

    static List<String> passwordList(String userName, String motherName, int pwdLen) {
        int[] ch = new int[26];
        String user = userName.toLowerCase();
        String mother = motherName.toLowerCase();
        for (int i = 0; i < user.length(); i++) {
            if (ch[user.charAt(i) - 'a'] == 0) {
                ch[user.charAt(i) - 'a']++;
            }
        }
        for (int i = 0; i < mother.length(); i++) {
            if (ch[mother.charAt(i) - 'a'] == 1) {
                ch[mother.charAt(i) - 'a']++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (ch[i] == 2) {
                sb.append((char) (i + 'a'));
            }
        }
        char[] pub = sb.toString().toCharArray();

        back(pub, new boolean[pub.length], new StringBuilder(), pwdLen);
        return result;
    }

    private static void back(char[] chars, boolean[] hasUsed, StringBuilder stringBuilder, int pwdLen) {
        if (stringBuilder.length() == pwdLen) {
            if (!set.contains(sum(stringBuilder.toString()))) {
                result.add(stringBuilder.toString());
            }
            set.add(sum(stringBuilder.toString()));
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (hasUsed[i]) {
                continue;
            }
            if (i != 0 && chars[i] == chars[i - 1] && !hasUsed[i - 1]) {
                continue;
            }
            hasUsed[i] = true;
            stringBuilder.append(chars[i]);
            back(chars, hasUsed, stringBuilder, pwdLen);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            hasUsed[i] = false;
        }
    }

    private static int sum(String string) {
        int sum = 0;
        for (int i = 0; i < string.length(); i++) {
            sum += string.charAt(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        List<String> list = passwordList("RadheGupta", "RADHIKA", 3);
        System.out.println(list);
    }
}
