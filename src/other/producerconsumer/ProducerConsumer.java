package other.producerconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Hello World
 * @date: 2018/6/6 10:16
 *
 * 使用了同一把锁, 同一时刻只有一个线程工作
 */
public class ProducerConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ProducerConsumer.class.getName());
    private static final int CAPACITY = 3;
    private static final Queue<Integer> queue = new LinkedList<>();
    private static final Object LOCK=new Object();

    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool();
        for (int i = 1; i < 3; i++) {
            pool.addTask(new Producer("P-" + i));
        }
        for (int i = 1; i < 3; i++) {
            pool.addTask(new Consumer("C-" + i));
        }
        pool.shutdown();
    }

    private static class Producer implements Runnable {
        private String name;
        private static AtomicInteger productID = new AtomicInteger(0);

        public Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    /**
                     * 用while来判断队列是否已满, 避免被唤醒后队列仍为满
                     * 下面进行添加时就会出错
                     */
                    while (queue.size() == CAPACITY) {
                        try {
                            logger.info("队列已满");
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    logger.info("[" + name + "] Producing value : +" + productID);
                    queue.offer(productID.getAndIncrement());
                    LOCK.notifyAll();
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class Consumer implements Runnable {
        private String name;

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    while (queue.isEmpty()) {
                        logger.info("队列已空");
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int x = queue.poll();
                    logger.info("[" + name + "] Consuming : -" + x);
                    LOCK.notifyAll();
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
