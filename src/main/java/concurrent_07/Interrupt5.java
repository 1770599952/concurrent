package concurrent_07;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 如何中断一个正在执行任务的线程
 */
public class Interrupt5 {
    private final static int INIT_CAPACITY = 10;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            BlockingQueue blockingQueue = new ArrayBlockingQueue(10);
            for (int i = 0; i < INIT_CAPACITY + 1; i++) {
                try {
                    blockingQueue.put(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.interrupt();
    }
}
