package company.zhaoshang;

/**
 * @author: Hello World
 * @date: 2018/9/25 14:47
 */
/*public class LogSend implements Runnable {

    private static final ThreadLocal<SimpleDateFormat> LOCAL_FORMAT = new ThreadLocal<SimpleDateFormat>();

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LOCAL_FORMAT.set(dateFormat);
    }

    private final String ENCODING = "ENCODING";

    private final String REQUEST_TIME = "REQUEST_TIME";

    private final String REQUEST = "REQUEST_PACKAGE";

    private BlockingQueue<Map<String, Object>> logQueue;

    public LogSend(int sendSize) {
        logQueue = new LinkedBlockingQueue<>(sendSize);// 由于需要频繁删除添加，需要保证队列效率
    }

    @Override
    public void run() {
        while (true) {
            Map<String, Object> logMsg = null;
            try {
                logMsg = logQueue.poll();
                String encoding = (String) logMsg.get(ENCODING);
                logMsg.put(REQUEST, cvByte2String((byte[]) logMsg.get(REQUEST), encoding));
                logMsg.put(REQUEST_TIME, cvDate2String((Date) logMsg.get(REQUEST_TIME)));
                System.out.println(JSON.toJSONString(logMsg));// 输出日志
            } catch (InterruptedException e) {
                break;
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    private Object cvDate2String(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public void add(Map<String, Object> record) {
        try {
            if (!logQueue.offer(record,10, TimeUnit.MILLISECONDS)) {
            }
        } catch (InterruptedException e) {
        }
    }

    private String cvByte2String(byte[] data, String encoding) {
        String result = null;
        if (data != null) {
            try {
                result = new String(data,encoding);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return result;
    }
}*/
