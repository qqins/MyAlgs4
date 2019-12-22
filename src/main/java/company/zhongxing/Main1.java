package company.zhongxing;

import java.util.*;

/**
 * @author: Hello World
 * @date: 2018/9/10 10:38
 * 按频次排序(由大到小)
 */
public class Main1 {
    static List<Integer> salaryfrequeny(int num, int[] salaries) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < num; i++) {
            if (!map.containsKey(salaries[i])) {
                int frequeny = 0;
                for (int j = i; j < num; j++) {
                    if (salaries[i] == salaries[j]) {
                        frequeny++;
                    }
                }
                map.put(salaries[i], frequeny);
            }
        }

        Map<Integer, Integer> sortedMap = sortByValueComparator(map);
        List<Integer> res = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            int temp = entry.getKey();
            int freq = entry.getValue();
            for (int i = 0; i < freq; i++) {
                res.add(temp);
            }
        }
        return res;
    }

    /**
     * 频次逆序
     */
    private static Map<Integer, Integer> sortByValueComparator(Map<Integer, Integer> unsortedMap) {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(unsortedMap.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue() - o1.getValue());
        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static void main(String[] args) {
        int[] salaries = {10000, 20000, 40000, 30000, 30000, 30000, 40000, 20000, 50000, 50000, 50000, 50000, 60000,
                60000, 60000, 70000
                , 80000, 90000, 100000};
        List<Integer> res = salaryfrequeny(19, salaries);
        System.out.println(res);
    }
}
