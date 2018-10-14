package dsaaaij3.chapter3;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/10/12 10:02
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    private static class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> prev, Node<AnyType> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(AnyType element) {
        add(size(), element);
        return true;
    }

    public void add(int index, AnyType element) {
        addBefore(getNode(index, 0, size()), element);
    }

    private void addBefore(Node<AnyType> p, AnyType element) {
        Node<AnyType> newNode = new Node<>(element, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    public AnyType get(int index) {
        return getNode(index).data;
    }

    public AnyType set(int index, AnyType newVal) {
        Node<AnyType> p = getNode(index);
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    private Node<AnyType> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    private Node<AnyType> getNode(int index, int lower, int upper) {
        Node<AnyType> p;
        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException("getNode index: " + index + "; size: " + size());
        }
        if (index < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > index; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    public AnyType remove(int index) {
        return remove(getNode(index));
    }

    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");

        for (AnyType element : this) {
            sb.append(element + " ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AnyType nextElement = current.data;
            current = current.next;
            okToRemove = true;
            return nextElement;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }
}

class TestLinkedList {
    public static void main(String[] args) {
        System.out.println("===========================================");
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }
        for (int i = 20; i <= 30; i++) {
            list.add(0, i);
        }
        System.out.println(list);

        list.remove(0);
        list.remove(list.size() - 1);
        System.out.println(list);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }
        System.out.println(list);
        System.out.println("===========================================");

        System.out.printf("输入规模     \t自带remove方法 \t迭代器中remove方法 \n");
        for (int n = 100; n <= 1000000; n *= 10) {
            System.out.print(String.format("N=%7d", n));
            for (int alg = 0; alg <= 1; alg++) {
                getTimingInfo(n, alg);
            }
            System.out.println();
        }
    }

    private static void getTimingInfo(int n, int alg) {
        MyLinkedList<Integer> list;
        Random random=new Random();
        long startTime = System.currentTimeMillis();
        long totalTime = 0;

        int k;
        for (k = 0; totalTime < 4000; k++) {
            list = new MyLinkedList<>();
            for (int j = 0; j <n; j++) {
                list.add(random.nextInt(n));
            }
            if (alg == 0) {
                int i = 0;
                while (i < list.size()) {
                    if (list.get(i) % 2 == 0) {
                        list.remove(i);
                    } else {
                        i++;
                    }
                }
            } else if (alg == 1) {
                Iterator<Integer> iterator = list.iterator();

                while (iterator.hasNext()) {
                    if (iterator.next() % 2 == 0) {
                        iterator.remove();
                    }
                }
            }
            totalTime = System.currentTimeMillis() - startTime;
        }

        System.out.print(String.format("\t%12.6f", (totalTime * 1000 / k) / (double) 1000000));
    }
}
