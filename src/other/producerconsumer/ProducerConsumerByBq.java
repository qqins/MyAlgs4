package other.producerconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Hello World
 * @date: 2018/6/5 16:33
 * <p>
 * 使用阻塞队列可以使消费者与生产者并行操作
 * 而生产者与生产者(消费者与消费者)之间则是串行的
 */
public class ProducerConsumerByBq {
    private static final Logger logger = LoggerFactory.getLogger(ProducerConsumerByBq.class.getName());
    private static final int CAPACITY = 3;
    private static final LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(CAPACITY);

    public static void main(String[] args) {

        /*Thread producer1 = new Producer("P-1", blockingQueue);
        Thread producer2 = new Producer("P-2", blockingQueue);
        Thread consumer1 = new Consumer("C-1", blockingQueue);
        Thread consumer2 = new Consumer("C-2", blockingQueue);
        Thread consumer3 = new Consumer("C-3", blockingQueue);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();*/

        MyThreadPool mtp = new MyThreadPool();
        for (int i = 1; i < 3; i++) {
            mtp.addTask(new Producer("P-" + i));
        }
        for (int i = 1; i < 3; i++) {
            mtp.addTask(new Consumer("C-" + i));
        }
        mtp.shutdown();

        /*ExecutorService service=Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            service.submit(new Producer("P-"+i,blockingQueue));
        }
        for (int i = 0; i < 3; i++) {
            service.submit(new Consumer("C-"+i,blockingQueue));
        }
        service.shutdownNow();*/
    }

    public static class Producer extends Thread {
        private String name;
        private AtomicInteger productID = new AtomicInteger(0);

        public Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    /**
                     * 看源码, put是加了锁的, 并且全局使用的是同一个阻塞队列
                     * 保证了生产者之间的串行
                     */
                    if (blockingQueue.size() == CAPACITY) {
                        logger.info("队列已满");
                    }
                    logger.info("[" + name + "] Producing value : +" + productID.get());
                    blockingQueue.put(productID.getAndIncrement());
                    /**
                     * 模拟不定期生产
                     * 若不等待一会, 该线程可能会一直占用队列
                     */
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static class Consumer extends Thread {
        private String name;

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if (blockingQueue.isEmpty()) {
                        logger.info("队列已空");
                    }
                    int x = blockingQueue.take();
                    logger.info("[" + name + "] Consuming : -" + x);
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
