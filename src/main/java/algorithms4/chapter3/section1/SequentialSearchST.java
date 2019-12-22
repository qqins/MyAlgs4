package algorithms4.chapter3.section1;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author: Hello World
 * @date: 2018/6/20 21:28
 */
public class SequentialSearchST<Key, Value> {
    private Node first;
    private int N;

    public class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        N++;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        first = delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            N--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new ArrayDeque<>();
        for (Node x = first; x != null; x = x.next) {
            queue.add(x.key);
        }
        return queue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Key k : keys()) {
            sb.append("[" + k + "," + get(k) + "]" + " ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        SequentialSearchST<Character, Integer> searchST = new SequentialSearchST();
        String example = "SEARCHEXAMPLE";
        for (int i = 0; i < example.length(); i++) {
            Character key = example.charAt(i);
            searchST.put(key, i);
        }
        for (Character ch : searchST.keys()) {
            System.out.println(ch + " " + searchST.get(ch));
        }
        System.out.println(searchST.toString());
    }
}
