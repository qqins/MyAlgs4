package algorithms4.chapter3.section4;

import algorithms4.chapter3.section1.SequentialSearchST;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author: Hello World
 * @date: 2018/8/1 21:44
 * <p>
 * 基于拉链法的散列表
 */
public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;
    //键值对总数
    private int n;
    //散列表的大小
    private int m;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<Key, Value>();
        }
    }

    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
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
        int i = hash(key);
        return st[i].get(key);
    }

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (value == null) {
            delete(key);
            return;
        }
        if (n >= 10 * m) {
            resize(2 * m);
        }
        int i = hash(key);
        if (!st[i].contains(key)) {
            n++;
        }
        st[i].put(key, value);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        int i = hash(key);
        if (st[i].contains(key)) {
            n--;
        }
        st[i].delete(key);
        if (m > INIT_CAPACITY && n <= 2 * m) {
            resize(m / 2);
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                queue.add(key);
            }
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
        SeparateChainingHashST<Character, Integer> sst = new SeparateChainingHashST<>();
        String str = "SEARCHEXAMPLE";
        for (int i = 0; i < str.length(); i++) {
            sst.put(str.charAt(i), i);
        }
        System.out.println(sst.toString());
        sst.delete('A');
        System.out.println(sst.toString());
    }
}
