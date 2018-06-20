package algorithms4.chapter3.section1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author: Hello World
 * @date: 2018/6/20 21:28
 */
public class SequentialSearchST<Key, Value> implements Iterable<Key> {
    private Node first;

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

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    @Override
    public Iterator<Key> iterator() {
        return new SearchIterator();
    }

    private class SearchIterator implements Iterator<Key> {
        Node temp = first;

        @Override
        public boolean hasNext() {
            return temp != null;
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Key res = temp.key;
            temp = temp.next;
            return res;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Key> iterator = this.iterator();
        while (iterator.hasNext()) {
            Key temp = iterator.next();
            sb.append("[" + temp + "," + get(temp) + "]" + " ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SequentialSearchST<Character, Integer> searchST = new SequentialSearchST();
        searchST.put('S', 0);
        searchST.put('E', 1);
        searchST.put('A', 2);
        searchST.put('A', 8);
        searchST.put('Q', 6);
        System.out.println(searchST.get('A'));
        System.out.println(searchST.toString());
    }
}
