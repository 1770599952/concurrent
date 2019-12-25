package concurrent_16;

/**
 * 多个线程顺序循环打印
 * 线程A,B,C 顺序循环打印，A,B,C,A,B,C
 */
public class ThreadWork_2 {

    public void execute(int threadNum) {
        setThreadsNum(threadNum);
        for (int i = 1; i <= threadNum; i++) {
            new Thread(new ThreadWork(i)).start();
        }
    }

    public void setThreadsNum(int threadsNum) {
        ThreadWork.threadsNum = threadsNum;
    }

    private static class ThreadWork implements Runnable {

        private volatile static Integer curRunIndex = 1;
        private final static Object lock = new Object();
        private static int threadsNum;
        private int index;

        public ThreadWork(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    try {
                        while (!curRunIndex.equals(index)) {
                            lock.wait();
                        }
                        System.out.println(index);
                        curRunIndex = index % ThreadWork.threadsNum + 1;
                        lock.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
