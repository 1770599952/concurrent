package concurrent_17;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;

public class MyThreadExecutor {

    private int coreSize;
    private int maxSize;
    private int keepAliveTime;
    private ThreadFactory threadFactory;
    private RejectStrategy rejectStrategy;
    private BlockingQueue<Runnable> taskQueue;
    private List<WorkThread> workThreads;

    public MyThreadExecutor(int coreSize, int maxSize, int keepAliveTime) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.keepAliveTime = keepAliveTime;
        this.threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
        this.rejectStrategy = new RejectStrategy();
        this.taskQueue = new ArrayBlockingQueue<Runnable>(10);
        this.workThreads = new ArrayList<WorkThread>();
    }

    public void execute(Runnable task) {
        synchronized (taskQueue) {
            if (workThreads.size() <= coreSize) {
                WorkThread thread = new WorkThread();
                workThreads.add(thread);
                thread.start();
            }
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    private class WorkThread extends Thread {

        private boolean isRunning = true;

        @Override
        public void run() {
            Runnable task = null;
            while (isRunning) {
                try {
                    synchronized (taskQueue) {
                        while (isRunning && taskQueue.isEmpty()) {
                            taskQueue.wait(20);
                        }
                        task = taskQueue.take();
                    }
                    task.run();
                    task = null;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
