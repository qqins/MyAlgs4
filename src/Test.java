import java.lang.reflect.Array;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(1, 3);
        Iterator iterator=map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry=(Map.Entry)iterator.next();
            Object key=entry.getKey();
            System.out.println(key);
            Object value=entry.getValue();
            System.out.println(value);
        }
    }
}
