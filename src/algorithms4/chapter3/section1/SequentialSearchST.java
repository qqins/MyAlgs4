package algorithms4.chapter3.section1;

/**
 * @author: Hello World
 * @date: 2018/6/20 21:28
 */
public class SequentialSearchST<Key,Value> {
    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key,Value val,Node next) {
            this.key=key;
            this.val=val;
            this.next=next;
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
                x.val=val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    public static void main(String[] args) {
        SequentialSearchST<Character,Integer> searchST=new SequentialSearchST();
        searchST.put('S',0);
        searchST.put('E',1);
        searchST.put('A',2);
        searchST.put('A',8);
        System.out.println(searchST.get('A'));
    }
}
