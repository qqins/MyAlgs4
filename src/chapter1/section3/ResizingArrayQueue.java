package chapter1.section3;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author: Hello World
 * @date: 2018/5/9 20:59
 */
public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[2];
    private int N;
    private int first;
    private int last;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public int arrayLength() {
        return a.length;
    }
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[(first + i) % a.length];
        }
        a = temp;
        first = 0;
        last = N;
    }

    public void enqueue(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[last++] = item;
        if (last == a.length) {
            //环形数组,到底了从头计数
            last = 0;
        }
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = a[first];
        //避免对象游离，即保存一个不需要的对象的引用
        a[first] = null;
        first++;
        N--;
        if (first == a.length) {
            //环形数组，到底了从头开始
            first = 0;
        }
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = a[(first + i) % a.length];
            i++;
            return item;
        }
    }

    /**
     * tobe.txt中的内容为：to be or not to - be - - that - - - is
     *
     * @param args
     */
    public static void main(String[] args) {
        ResizingArrayQueue<String> raq = new ResizingArrayQueue();
        File file = new File("D:\\_MyFile\\学习\\java\\Algorithms_4th" +
                "\\algs4-data\\tobe.txt");
        try (FileInputStream fis = new FileInputStream(file);
             BufferedReader br = new BufferedReader(new InputStreamReader
                     (fis, "UTF-8"))) {
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String[] str = sb.toString().split("\\s+");
            for (String s : str) {
                if (!s.equals("-")) {
                    raq.enqueue(s);
                } else if (!raq.isEmpty()) {
                    System.out.print(raq.dequeue() + " ");
                }
            }
            System.out.println("(" + raq.size() + " left on queue)");
            System.out.println("队列中剩余的元素为：");
            Iterator<String> iterator = raq.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
