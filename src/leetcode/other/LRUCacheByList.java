package leetcode.other;

import java.util.HashMap;

/**
 * @author: Hello World
 * @date: 2018/10/26 14:07
 */
public class LRUCacheByList {
    private int size;
    private int capacity;
    private HashMap<Integer, Node> cacheData;
    private Node head;
    private Node tail;

    public LRUCacheByList(int capacity) {
        this.capacity = capacity;
        cacheData = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (cacheData.containsKey(key)) {
            Node node = cacheData.get(key);
            remove(node);
            addLast(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cacheData.containsKey(key)) {
            Node node = cacheData.get(key);
            node.val = value;
            remove(node);
            addLast(node);
            return;
        }

        Node node = new Node(key, value);
        addLast(node);
        cacheData.put(key, node);
        size++;

        if (size > capacity) {
            cacheData.remove(removeFirst());
            size--;
        }
    }

    private void addLast(Node node) {
        node.prev = tail.prev;
        node.next = tail;

        tail.prev.next = node;
        tail.prev = node;
    }

    private int removeFirst() {
        Node next = head.next;
        Node nextNext = next.next;

        next.prev = null;
        next.next = null;

        nextNext.prev = head;
        head.next = nextNext;

        return next.key;
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        node.prev = null;
        node.next = null;

        prev.next = next;
        next.prev = prev;
    }

    private class Node {
        int key;
        int val;
        Node next;
        Node prev;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
