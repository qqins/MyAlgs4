package company.xiecheng;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/4 19:39
 * <p>
 * LeetCode 146: LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），
 * 否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，
 * 它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 2
 * p 1 1
 * p 2 2
 * g 1
 * p 2 102
 * p 3 3
 * g 1
 * g 2
 * g 3
 *
 * 输出：1 1 -1 3
 */
public class Main3 {
    private static int capacity;
    private static HashMap<Integer, Integer> data = new HashMap<>();
    private static ArrayDeque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        capacity = scanner.nextInt();
        scanner.nextLine();
        while (scanner.hasNext()) {
            String str1 = scanner.nextLine();
            String[] res = str1.trim().split("\\s+");
            if (res[0].equals("p")) {
                put(Integer.parseInt(res[1]), Integer.parseInt(res[2]));
            }
            if (res[0].equals("g")) {
                System.out.println(get(Integer.parseInt(res[1])));
            }
        }
    }

    public static int get(int key) {
        if (data.containsKey(key)) {
            deque.remove(key);
            deque.add(key);
            return data.get(key);
        }
        return -1;
    }

    public static void put(int key, int value) {
        //满足当key不变，仅改变value时，不算使用数据
        /*if (data.containsKey(key)) {
            data.put(key, value);
            return;
        }
        if (data.size() == capacity) {
            data.remove(deque.pollFirst());
        }
        deque.add(key);
        data.put(key, value);*/

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
