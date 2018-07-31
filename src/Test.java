import com.sun.jmx.remote.internal.ArrayQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class.getName());
    private static final AtomicInteger ai = new AtomicInteger();
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(ai.getAndDecrement());
        System.out.println(ai.get());
    }
}
