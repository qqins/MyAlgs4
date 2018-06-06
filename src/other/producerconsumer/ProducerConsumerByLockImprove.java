package other.producerconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Hello World
 * @date: 2018/6/6 17:18
 */
public class ProducerConsumerByLockImprove {
    private static final Logger logger = LoggerFactory.getLogger(ProducerConsumerByLock.class.getName());
    private static final int CAPACITY = 3;
    private static final Queue<Integer> queue = new LinkedList<>();

    private static final Lock PRODUCE = new ReentrantLock();
    private static final Condition NOT_FULL = PRODUCE.newCondition();
    private static final Lock CONSUMER = new ReentrantLock();
    private static final Condition NOT_EMPTY = CONSUMER.newCondition();
    private static final AtomicInteger count = new AtomicInteger(0);

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
        private int c = -1;

        public Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    PRODUCE.lockInterruptibly();
                    while (queue.size() == CAPACITY) {
                        logger.info("队列已满");
                        NOT_FULL.await();
                    }
                    logger.info("[" + name + "] Producing value : +" + productID);
                    queue.offer(productID.getAndIncrement());
                    c = count.getAndIncrement();
                    if (c + 1 < CAPACITY) {
                        NOT_FULL.signal();
                    }
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    PRODUCE.unlock();
                }
                if (c == 0) {
                    try {
                        CONSUMER.lockInterruptibly();
                        NOT_EMPTY.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        CONSUMER.unlock();
                    }
                }
            }
        }
    }

    public static class Consumer implements Runnable {
        private String name;
        private int c = -1;

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    CONSUMER.lockInterruptibly();
                    while (queue.isEmpty()) {
                        logger.info("队列已空");
                        NOT_EMPTY.await();
                    }
                    int x = queue.poll();
                    logger.info("[" + name + "] Consuming : -" + x);
                    c = count.decrementAndGet();
                    if (c > 0) {
                        NOT_EMPTY.signal();
                    }
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    CONSUMER.unlock();
                }
                if (c == CAPACITY) {
                    try {
                        PRODUCE.lockInterruptibly();
                        NOT_FULL.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        PRODUCE.unlock();
                    }
                }
            }
        }
    }
}
