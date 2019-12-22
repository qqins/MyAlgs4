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
 * @date: 2018/6/6 11:25
 *
 * 这种方法与使用synchronized关键字方法是一样的
 * 使用了一把锁, 不能同时生产和消费
 */
public class ProducerConsumerByLock {
    private static final Logger logger = LoggerFactory.getLogger(ProducerConsumerByLock.class.getName());
    private static final int CAPACITY = 3;
    private static final Queue<Integer> queue = new LinkedList<>();
    private static final Lock lock = new ReentrantLock();
    private static final Condition fullCondition = lock.newCondition();
    private static final Condition emptyCondition = lock.newCondition();

    public static void main(String[] args) {
        MyThreadPool pool=new MyThreadPool();
        for (int i = 1; i < 3; i++) {
            pool.addTask(new Producer("P-"+i));
        }
        for (int i = 1; i <3 ; i++) {
            pool.addTask(new Consumer("C-"+i));
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
                lock.lock();
                try {
                    while (queue.size() == CAPACITY) {
                        logger.info("队列已满");
                        fullCondition.await();
                    }
                    logger.info("[" + name + "] Producing value : +" + productID);
                    queue.offer(productID.getAndIncrement());
                    emptyCondition.signalAll();
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
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
                lock.lock();
                try {
                    while (queue.isEmpty()) {
                        logger.info("队列已空");
                        emptyCondition.await();
                    }
                    int x = queue.poll();
                    logger.info("[" + name + "] Consuming : -" + x);
                    fullCondition.signalAll();
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
