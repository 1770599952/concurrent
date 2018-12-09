package concurrent_06;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized如果出现异常就会自动释放锁
 * ReentrantLock:必须手动加锁和释放锁。
 */
public class ReentrantLock2 {
    Lock lock = new ReentrantLock();

    private void m1() {
        try {
            lock.lock(); //synchronized(this)
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);

                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void m2() {
        lock.lock();
        System.out.println("m2 ...");
        lock.unlock();
    }
    public static void main(String[] args) {
        ReentrantLock2 reentrantLock2 = new ReentrantLock2();
        new Thread(reentrantLock2::m1);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(reentrantLock2::m2);
    }
}
