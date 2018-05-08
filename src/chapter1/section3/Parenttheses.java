package chapter1.section3;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Parenttheses {
    private static class Stack<Item> implements Iterable<Item> {
        private Node first;
        private int N;

        private class Node {
            Item item;
            Node next;
        }

        public void push(Item item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            N++;
        }

        public Item pop() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }

        public Item peek() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            return first.item;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public int size() {
            return N;
        }

        @Override
        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        private class ListIterator implements Iterator<Item> {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {

            }
        }
    }

    //1.3.4
    public static boolean isComplete(String str) {
        if (str.length() == 0) {
            return false;
        }
        Stack<Character> sta = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '[') {
                sta.push('[');
            }
            if (str.charAt(i) == '(') {
                sta.push('(');
            }
            if (str.charAt(i) == '{') {
                sta.push('{');
            }
            if (str.charAt(i) == ']') {
                if (sta.isEmpty()) {
                    return false;
                }
                if (sta.pop() != '[') {
                    return false;
                }
            }
            if (str.charAt(i) == ')') {
                if (sta.isEmpty()) {
                    return false;
                }
                if (sta.pop() != '(') {
                    return false;
                }
            }
            if (str.charAt(i) == '}') {
                if (sta.isEmpty()) {
                    return false;
                }
                if (sta.pop() != '{') {
                    return false;
                }
            }
        }
        /**
         * 若完全匹配，则栈中元素必定全部弹出
         * 对于"[({"这种情况就可以视为false
         */
        return sta.isEmpty();
    }

    //1.3.9
    public static void completion() {
        String str = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        String[] strings = str.split("\\s+");
        Stack<String> fh = new Stack<>();
        Stack<String> num = new Stack<>();
        Queue<String> result = new ArrayDeque<>();
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("+")) {
                fh.push("+");
            } else if (strings[i].equals("-")) {
                fh.push("-");
            } else if (strings[i].equals("*")) {
                fh.push("*");
            } else if (strings[i].equals("/")) {
                fh.push("/");
            } else if (strings[i].equals(")")) {
                String s1 = num.pop();
                String s2 = num.pop();
                String f = fh.pop();
                String s = "(" + s2 + f + s1 + ")";
                num.push(s);
            } else {
                num.push(strings[i]);
            }
        }
        System.out.println(num.pop());
    }

    public static void main(String[] args) {
        String str = "[()]{}{[()()]()}";
        String str1 = "";
        System.out.println(isComplete(str1));
        completion();
    }
}
