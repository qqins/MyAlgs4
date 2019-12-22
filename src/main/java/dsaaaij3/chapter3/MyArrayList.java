package dsaaaij3.chapter3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author: Hello World
 * @date: 2018/10/11 22:12
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {
    private static final int DEFAULT_CAPACITY = 10;

    private AnyType[] theItems;
    private int theSize;

    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public AnyType get(int index) {
        if (index <= 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[index];
    }

    public AnyType set(int index, AnyType element) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType old = theItems[index];
        theItems[index] = element;
        return old;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size()) {
            return;
        }
        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public boolean add(AnyType element) {
        add(size(), element);
        return true;
    }

    public void add(int index, AnyType element) {
        if (theItems.length == size()) {
            //+1是针对size()=0的情况(使用remove将数组元素移空了)
            ensureCapacity(size() * 2 + 1);
        }
        for (int i = size(); i > index; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[index] = element;

        theSize++;
    }

    public AnyType remove(int index) {
        AnyType removedElement = theItems[index];
        for (int i = index; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }

        //GC时将其数组末尾标记为垃圾进行回收
        theItems[--theSize] = null;
        return removedElement;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");

        //增强for循环调用的是iterator实现的
        for (AnyType element : this) {
            sb.append(element + " ");
        }
        sb.append("]");

        return sb.toString();
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<AnyType> {
        private int current = 0;
        //迭代器中进行remove操作前必须进行next操作
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            okToRemove = true;
            return theItems[current++];
        }

        @Override
        public void remove() {
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyArrayList.this.remove(--current);
            okToRemove = false;
        }
    }
}

class TestArrayList {
    public static void main(String[] args) {
        MyArrayList<Integer> lst = new MyArrayList<>();

        for (int i = 0; i < 10; i++) {
            lst.add(i);
        }
        for (int i = 20; i < 30; i++) {
            lst.add(0, i);
        }

        lst.remove(0);
        lst.remove(lst.size() - 1);

        System.out.println(lst);

        Iterator<Integer> iterator = lst.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
