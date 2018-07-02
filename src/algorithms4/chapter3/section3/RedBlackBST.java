package algorithms4.chapter3.section3;

import com.sun.org.apache.regexp.internal.RE;
import sun.misc.Cache;

/**
 * @author: Hello World
 * @date: 2018/7/2 20:11
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private boolean color;
        private int size;

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    public RedBlackBST() {

    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.right) + size(h.left);
        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.right) + size(h.left);
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
        }
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, RED, 1);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && !isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        h.size = 1 + size(h.right) + size(h.left);
        return h;
    }
}
