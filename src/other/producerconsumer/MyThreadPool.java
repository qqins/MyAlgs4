package other.producerconsumer;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Hello World
 * @date: 2018/6/5 19:23
 * <p>
 * 详情：https://cloud.tencent.com/developer/article/1014670
 *
 * 线程池的主要处理流程：
 * 1, 提交任务
 * 2, 检查核心线程池是否已满, 若未满, 则创建线程完成任务, 否则
 * 3, 检查任务队列是否已满, 若未满, 则将任务存在队列中, 否则
 * 4, 检查线程池是否已满, 若未满, 则创建线程完成任务, 否则
 * 5, 按照策略处理无法执行的任务
 */
public class MyThreadPool {
    //    private final String TAG = this.getClass().getSimpleName();
    private final String TAG = "qqin";
    /**
     * 获取cpu核心数： Runtime.getRuntime().availableProcessors()
     *
     * corePoolSize：核心线程池数量
     * 在线程数少于核心数量时，有新任务进来就新建一个线程，即使有的线程没事干
     * 等超出核心数量后，就不会新建线程了，空闲的线程就得去任务队列里取任务执行了
     */
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    /**
     * maximumPoolSize：最大线程数量
     * 包括核心线程池数量 + 核心以外的数量
     * 如果任务队列满了，核心线程没有空闲，并且池中线程数小于最大线程数，会再创建新的线程执行任务
     */
    private static final int MAXIMUM_POOL_SIZE = 64;
    /**
     * 核心线程池以外的空闲线程最大存活时间设为1秒
     */
    private static final int KEEP_ALIVE_TIME = 1;

    /**
     * 保存待执行任务的阻塞队列
     * ArrayBlockingQueue：基于数组、有界，按 FIFO（先进先出）原则对元素进行排序
     * LinkedBlockingQueue：基于链表，按FIFO （先进先出） 排序元素
     *      吞吐量通常要高于 ArrayBlockingQueue
     *      Executors.newFixedThreadPool() 使用了这个队列
     * SynchronousQueue：不存储元素的阻塞队列
     *      每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态
     *      吞吐量通常要高于 LinkedBlockingQueue
     *      Executors.newCachedThreadPool使用了这个队列
     * PriorityBlockingQueue：具有优先级的、无限阻塞队列
     *
     * 如果是要求高吞吐量的，可以使用 SynchronousQueue 队列；如果对执行顺序有要求，
     * 可以使用 PriorityBlockingQueue；如果最大积攒的待做任务有上限，可以使用
     * LinkedBlockingQueue。
     */
    private final BlockingQueue<Runnable> mWorkQueue = new LinkedBlockingQueue<>(128);

    /**
     * threadFactory：每个线程创建的地方
     * 可以给线程起个好听的名字，设置个优先级啥的
     */
    private final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, TAG + " #" + mCount.getAndIncrement());
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    };

    /**
     * TimeUnit.SECONDS存活时间的单位为秒
     * *
     * 饱和策略，大家都很忙，咋办呢，有四种策略
     * CallerRunsPolicy：只要线程池没关闭，就直接用调用者所在线程来运行任务
     * AbortPolicy：直接抛出 RejectedExecutionException 异常
     * DiscardPolicy：悄悄把任务放生，不做了
     * DiscardOldestPolicy：把队列里待最久的那个任务扔了，然后再调用 execute() 试试看能行不
     */
    private ThreadPoolExecutor mExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_TIME, TimeUnit.SECONDS, mWorkQueue, DEFAULT_THREAD_FACTORY,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    private static volatile MyThreadPool mInstance = new MyThreadPool();

    public static MyThreadPool getmInstance() {
        return mInstance;
    }

    public void addTask(Runnable runnable) {
        mExecutor.execute(runnable);
    }

    public void shutdownNow() {
        mExecutor.shutdownNow();
    }
}
