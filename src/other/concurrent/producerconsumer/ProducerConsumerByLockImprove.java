package other.concurrent.producerconsumer;

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
 *
 * 对条件锁的改进, 参考BlockingQueue中put, take方法
 * 使用了两把锁, 可以同时进行生产和消费
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
                    /**
                     * 若当前队列未满, 则唤醒其余生产者线程进行生产
                     * 此时count实际为c+1
                     * 或者将if中条件改为quene.size()<CAPACITY
                     */
                    if (c + 1 < CAPACITY) {
                        NOT_FULL.signalAll();
                    }
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    PRODUCE.unlock();
                }
                /**
                 * 在进行本次生产之前, 若c==0表明之前队列为空, 即消费者线程
                 * 全部在等待唤醒
                 * 则需要唤醒消费者进行消费
                 */
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
                    c = count.getAndDecrement();
                    /**
                     * 若此时队列非空, 则唤醒其余被挂起的线程起来干活
                     * 因为此时count先赋值再减1, 所以此时count实际为
                     * c-1, 队列非空时, 即c-1>0
                     * 或者将if中条件改为!queue.isEmpty()
                     */
                    if (c > 1) {
                        NOT_EMPTY.signalAll();
                    }
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    CONSUMER.unlock();
                }
                /**
                 * 若消费之前c==CAPACITY, 则表明生产线程全部被挂起, 等待被唤醒
                 */
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
