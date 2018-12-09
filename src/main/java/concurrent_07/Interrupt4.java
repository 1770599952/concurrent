package concurrent_07;

import java.util.concurrent.TimeUnit;

/**
 * 这个时候有需要我们的Interrupt了
 */
public class Interrupt4 {
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
        thread.interrupt();

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
