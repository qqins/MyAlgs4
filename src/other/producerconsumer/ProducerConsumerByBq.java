package other.producerconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Hello World
 * @date: 2018/6/5 16:33
 */
public class ProducerConsumerByBq {
    private static final Logger logger = LoggerFactory.getLogger(ProducerConsumerByBq.class.getName());
    private static final int CAPACITY = 2;

    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(CAPACITY);
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
            mtp.addTask(new Producer("P-" + i, blockingQueue));
        }
        for (int i = 1; i < 4; i++) {
            mtp.addTask(new Consumer("C-" + i, blockingQueue));
        }
        mtp.shutdownNow();

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
        private LinkedBlockingQueue<Integer> blockingQueue;
        private String name;
        private AtomicInteger productID = new AtomicInteger(0);

        public Producer(String name, LinkedBlockingQueue<Integer> queue) {
            this.name = name;
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    blockingQueue.put(productID.get());
                    logger.info("[" + name + "] Producing value : +" + productID.get());
                    productID.getAndIncrement();
                    /**
                     * 模拟不定期生产
                     */
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static class Consumer extends Thread {
        private LinkedBlockingQueue<Integer> blockingQueue;
        private String name;

        public Consumer(String name, LinkedBlockingQueue<Integer> queue) {
            this.name = name;
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
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
