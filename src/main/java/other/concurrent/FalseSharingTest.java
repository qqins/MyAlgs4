package other.concurrent;

import sun.misc.Contended;

/**
 * @author: Hello World
 * @date: 2018/8/10 9:11
 */
public class FalseSharingTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            Benchmark();
        }
    }

    public static void Benchmark() throws InterruptedException {
        int size = Runtime.getRuntime().availableProcessors();
        SharingLong[] shares = new SharingLong[size];
        for (int i = 0; i < size; i++) {
            shares[i] = new SharingLong();
        }
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            int index = i;
            Runnable r = () -> {
                for (int j = 0; j < 100000000; j++) {
                    shares[index].v++;
                }
            };
            threads[i] = new Thread(r);
            threads[i].start();
        }
        long start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

/**
 * java8 字节填充的官方实现
 * 加Content注解, 需要开启JVM参数:-XX:-RestrictContended
 * 在Run-->Edit Congratulations-->VM options中填写上述JVM参数
 */
@Contended
class SharingLong {
    volatile long v;
}


