package leetcode.other;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * @author: Hello World
 * @date: 2018/9/25 22:14
 */
public class LRUCache {
    private int capacity;
    private HashMap<Integer, Integer> cacheData;
    private ArrayDeque<Integer> deque;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheData = new HashMap<Integer, Integer>();
        deque = new ArrayDeque<>();
    }

    public int get(int key) {
        if (cacheData.containsKey(key)) {
            deque.remove(key);
            deque.add(key);
            return cacheData.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        //key不变，仅改变value时，也算使用数据
        if (cacheData.containsKey(key)) {
            deque.remove(key);
        }
        if (deque.size() == capacity) {
            cacheData.remove(deque.pollFirst());
        }
        cacheData.put(key, value);
        deque.add(key);
    }
}
