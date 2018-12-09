package concurrent_06;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock还可以指定为公平锁
 */
public class ReentrantLock5 extends Thread {
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 reentrantLock5 = new ReentrantLock5();
        Thread th1=new Thread(reentrantLock5);
        Thread th2=new Thread(reentrantLock5);
        th1.start();
        th2.start();
    }
}
