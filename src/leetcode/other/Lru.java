package leetcode.other;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * @author: Hello World
 * @date: 2018/9/25 22:14
 */
public class Lru {
    private static int capacity;
    private static HashMap<Integer, Integer> data = new HashMap<>();
    private static ArrayDeque<Integer> deque = new ArrayDeque<>();

    public static int get(int key) {
        if (data.containsKey(key)) {
            deque.remove(key);
            deque.add(key);
            return data.get(key);
        }
        return -1;
    }

    public static void put(int key, int value) {
        //key不变，仅改变value时，也算使用数据
        if (data.containsKey(key)) {
            deque.remove(key);
        }
        if (deque.size() == capacity) {
            data.remove(deque.pollFirst());
        }
        data.put(key, value);
        deque.add(key);
    }
}
