package company.zhaoshang;

import java.util.*;

/**
 * @author: Hello World
 * @date: 2018/9/25 16:22
 */
class S1 {
    private String[] m1 = {"a", "b", "c", "d"};

    Iterator<String> getMenu() {
        return new Itr(m1);
    }
}

class Itr implements Iterator<String> {
    int cursor;       // index of next element to return
    private String[] arr;
    private int size = 0;

    public Itr(String[] arr) {
        this.arr = arr;
        if (arr != null) {
            this.size = arr.length;
        }
    }

    public boolean hasNext() {
        return cursor < size;
    }

    public String next() {
        int i = cursor;
        if (i >= size)
            throw new NoSuchElementException();
        cursor++;
        return arr[i];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("不支持该方法");
    }
}

class S2 {
    private List<String> m2 = Arrays.asList("e", "f", "g", "h");

    Iterator<String> getMenu() {
        return new Itr((String[]) m2.toArray());
    }
}

public class TraversalMenu {
    public void WriteMenu(Iterator<String> itr) {
        while (itr.hasNext()) {
            String menuItem = itr.next();
            System.out.println(menuItem);
        }
    }

    public static void main(String[] args) {
        TraversalMenu traversalMenu = new TraversalMenu();
        S1 s1 = new S1();
        S2 s2 = new S2();
        traversalMenu.WriteMenu(s1.getMenu());
        traversalMenu.WriteMenu(s2.getMenu());

    }

}
