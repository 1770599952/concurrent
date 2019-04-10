package concurrent_13;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://www.cnblogs.com/zedosu/p/6665306.html
 */
public class MyThreadPoolExecutor_1 {

    private ThreadPoolExecutor pool = null;

    public MyThreadPoolExecutor_1() {
        pool = new ThreadPoolExecutor(1, 2, 10, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1), new MyThreadFactory(), new AbortPolicy());
    }

    private class MyThreadFactory implements ThreadFactory {

        private AtomicInteger countThread = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            String threadName = MyThreadPoolExecutor_1.class.getSimpleName() + countThread.addAndGet(1);
            thread.setName(threadName);
            return thread;
        }
    }

    /**
     * A handler for rejected tasks that throws a
     * {@code RejectedExecutionException}.
     */
    public static class AbortPolicy implements RejectedExecutionHandler {
        /**
         * Creates an {@code AbortPolicy}.
         */
        public AbortPolicy() {
        }

        /**
         * Always throws RejectedExecutionException.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         * @throws RejectedExecutionException always
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " +
                    e.toString());
        }
    }

    public static void main(String[] args) {
        MyThreadPoolExecutor_1 poolExecutor = new MyThreadPoolExecutor_1();
        ExecutorService executorService = poolExecutor.getMyThreadPoolExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " 开始执行。");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private ExecutorService getMyThreadPoolExecutor() {
        return pool;
    }

}
