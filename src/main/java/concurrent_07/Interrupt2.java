package concurrent_07;

import java.util.concurrent.TimeUnit;

/**
 *  既然只能改变中断状态，那么我们该如何正确的中断一个线程呢？
 */
public class Interrupt2 {
    private volatile static boolean on = false;

    public static void main(String[] args) {
        Thread thread = new Thread(new MyInterrupt());
        thread.start();
        thread.interrupt();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        on = true;
    }
    private static class MyInterrupt implements Runnable {
        @Override
        public void run() {
            while (!on) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Yes,I am interruted,but I am still running");
                } else {
                    System.out.println("not yet interrupted");
                }
            }
        }
    }
}
