package other.hashmap;

import java.util.*;

/**
 * @author: Hello World
 * @date: 2018/9/11 10:04
 */
public class HashMapSort {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2, 1);
        map.put(4, 5);
        map.put(8, 3);
        map.put(5, 6);
        map.put(1, 9);
        Map<Integer, Integer> sortedMap = sortByValueComparator(map);
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }

    /**
     * 按键逆序
     */
    private static Map<Integer, Integer> sortByKeyComparator(Map<Integer, Integer> unsortedMap) {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(unsortedMap.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getKey() - o1.getKey());
        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    /**
     * 按值逆序
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
}
