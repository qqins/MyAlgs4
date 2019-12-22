package other.hashmap;

import java.util.*;

/**
 * @author: Hello World
 * @date: 2018/9/11 10:35
 */
public class TreeMapSort {

    public static void main(String[] args) {
        /*按键逆序
        Map<Integer, Integer> map = new TreeMap<>((o1, o2) -> o2 - o1);*/

        Map<Integer, Integer> map = new TreeMap<>();
        map.put(1, 4);
        map.put(4, 2);
        map.put(3, 0);
        map.put(6, 9);
        map.put(2, 8);
        Map<Integer, Integer> sortedMap = sortByValueComparator(map);
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
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
