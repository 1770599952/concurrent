package concurrent_13;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue 有界队列，以下均为有界队列的测试
 */
public class BlockingQueue_2 {

    private final static int INIT_CAPACITY = 10;

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(10);
        poolTest(blockingQueue);
    }

    /**
     * pool：移除队列的头部元素，如果队列为空，则阻塞等待特定的时间。
     *
     * @param blockingQueue
     */
    private static void poolTest(BlockingQueue blockingQueue) {
        try {
            Object poolObj = blockingQueue.poll(1000, TimeUnit.MILLISECONDS);
            System.out.println("blockingQueue.pool block result:" + poolObj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * take:移除队列的头部元素，如果队列为空，则阻塞等待，当前过程可以被打断。
     *
     * @param blockingQueue
     */
    private static void takeTest(BlockingQueue blockingQueue) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < INIT_CAPACITY + 1; i++) {
                boolean offerResult = false;
                try {
                    offerResult = blockingQueue.offer(i, 2000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("blockingQueue.offer block result:" + offerResult);
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Object takeObj = blockingQueue.take();
            System.out.println("take obj :" + takeObj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * boolean offer(E e, long timeout, TimeUnit unit)
     * throws InterruptedException;
     * offer实现阻塞添加。
     * 如果队列已满，则阻塞一个特定的时间进行等待
     *
     * @param blockingQueue
     */
    private static void offerBlockTest(BlockingQueue blockingQueue) {
        for (int i = 0; i < INIT_CAPACITY + 1; i++) {
            boolean offerResult = false;
            try {
                offerResult = blockingQueue.offer(i, 1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("blockingQueue.offer block result:" + offerResult);
        }
    }

    /**
     * 如何打算put操作的阻塞。
     *
     * @param blockingQueue
     */
    private static void putInterruptedTest(BlockingQueue blockingQueue) {
        Thread thread = new Thread(() -> {
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


    /**
     * 如果队列未满，则直接添加成功，如果队列已满，则阻塞等待队列等空闲容量。
     *
     * @param blockingQueue
     */
    private static void putTest(BlockingQueue blockingQueue) {
        for (int i = 0; i < INIT_CAPACITY + 1; i++) {
            try {
                blockingQueue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 如果添加成功，则直接返回true。如果queue已满，则返回false。
     *
     * @param blockingQueue
     */
    private static void offerTest(BlockingQueue blockingQueue) {
        for (int i = 0; i < INIT_CAPACITY + 1; i++) {
            boolean offerResult = blockingQueue.offer(i);
            System.out.println("blockingQueue.offer result:" + offerResult);
        }
    }

    /**
     * 如果添加成功，则直接返回true。如果queue已满，则抛出异常。
     *
     * @param blockingQueue
     */
    private static void addTest(BlockingQueue blockingQueue) {
        for (int i = 0; i < INIT_CAPACITY + 1; i++) {
            boolean addResult = blockingQueue.add(i);
            System.out.println("blockingQueue.add result:" + addResult);
        }
    }
}
