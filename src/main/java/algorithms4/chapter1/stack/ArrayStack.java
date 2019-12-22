package algorithms4.chapter1.stack;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ArrayStack<String> s = new ArrayStack<>();
        File file = new File("D:\\_MyFile\\学习\\java\\Algorithms_4th" +
                "\\algs4-data\\tobe.txt");
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            /**
             * 若指定byte长度为1024，当文件的长度达不到时，会在byte剩余的位置
             * 填充\u0000即空字符, "\u0000".length=1, 而 "".length=1。
             * 所以如果指定byte长度为1024，最后所得strings的长度为15而不是14
             * 第15个元素即byte剩余长度组成的\u0000，即空字符。
             */
            byte[] bytes = new byte[(int) file.length()];
            StringBuffer sb = new StringBuffer();
            while ((bis.read(bytes)) != -1) {
                /**
                 * 由于bis是输入流，所以要将其转为string
                 */
                sb.append(new String(bytes, "UTF-8"));
            }
            System.out.println(sb.toString());
            String[] strings = sb.toString().split("\\s+");
            System.out.println(Arrays.toString(strings));
            System.out.println(strings.length);
            for (String str : strings) {
                if (!str.equals("-")) {
                    s.push(str);
                } else if (!s.isEmpty()) {
                    System.out.print(s.pop() + " ");
                }
            }
            System.out.println("(" + s.size() + " left on stack)");
            System.out.println("剩余的元素为：");
            Iterator<String> iterator = s.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next()+" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
