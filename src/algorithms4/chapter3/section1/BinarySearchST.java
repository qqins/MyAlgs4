package algorithms4.chapter3.section1;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: Hello World
 * @date: 2018/6/25 19:48
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    private void resize(int capacity) {
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        keys = tempk;
        vals = tempv;
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
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        }
        return null;
    }

    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        if (N == keys.length) {
            resize(2 * keys.length);
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        if (isEmpty()) {
            return;
        }
        int i = rank(key);
        if (i == N || keys[i].compareTo(key) != 0) {
            return;
        }
        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        N--;
        keys[N] = null;
        vals[N] = null;
        if (N > 0 && N == keys.length / 4) {
            resize(keys.length / 2);
        }
    }

    public Iterable<Key> keys() {
        return keys(keys[0], keys[N - 1]);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("secone argument to keys() is null");
        }
        /**
         * 将Stack换成ArrayDeque，则输出倒序
         */
        Stack<Key> queue = new Stack<>();
        if (lo.compareTo(hi) > 0) {
            return queue;
        }
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.push(keys[i]);
        }
        if (contains(hi)) {
             queue.push(keys[rank(hi)]);
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
        BinarySearchST<Character, Integer> st = new BinarySearchST<>();
        String str = "SEARCHEXAMPLE";
        for (int i = 0; i < str.length(); i++) {
            st.put(str.charAt(i), i);
        }
        System.out.println(st.toString());
        st.delete('A');
        System.out.println(st.toString());
    }
}
