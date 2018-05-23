import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class.getName());

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println(map.get(1));
    }
}
