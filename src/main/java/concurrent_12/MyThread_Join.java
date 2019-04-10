package concurrent_12;

public class MyThread_Join extends Thread {

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("子线程开始执行");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程执行结束");
        }
    }

    /**
     * Waits for this thread to die.
     *
     * <p> An invocation of this method behaves in exactly the same
     * way as the invocation
     */
    public static void testJoin(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThread_Join myThreadJoin = new MyThread_Join();
        myThreadJoin.start();
        testJoin(myThreadJoin);
        System.out.println("父线程结束");
    }
}
