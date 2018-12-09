package concurrent_09;

import java.util.concurrent.TimeUnit;

public class WaitAndNotify {

    public static void main(String[] args) {
        Object lock = new Object();
        Thread t1 = new Thread(new MyWNTest("aa",lock));
        Thread t2 = new Thread(new MyWNTest("bb",lock));
        t1.start();
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread notify = new Thread(() -> {
                System.out.println("notify");
            });
            notify.start();
            synchronized (lock) {
                lock.notifyAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class MyWNTest implements Runnable {
        private final Object lock;
        private String name;

        public MyWNTest(String name,Object lock) {
            this.name = name;
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("start" + name);
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end" + name);
            }
        }
    }
}
