package concurrent_07;

import java.util.concurrent.TimeUnit;

/**
 * 上一种中断标志进行中断还是有一些问题的，比如当前线程阻塞？
 *
 */
public class Interrupt3 {
    private volatile static boolean on = false;

    public static void main(String[] args) {
        Thread thread = new Thread(new MyInterrupt());
        thread.start();
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
                try {
                    Thread.sleep(1000000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
