package concurrent_05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * wait会释放锁，notify不会释放锁。
 * 需要注意的是，运用这种特性，必须保证监控线程先执行，也就是先让监控线程监控才可以
 *
 *  新的问题：必须等到执行线程执行结束后，监控线程才能结束，而不是size等于5的时候监控线程结束。
 */
public class MyContainer3 {

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        MyContainer3 container3 = new MyContainer3();
        Object lock = new Object();
        new Thread(new Thread(() -> {
            synchronized (lock) {
                System.out.println("监控线程开始");
                if (container3.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("监控线程结束");
            }
        })).start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("执行线程开始");
                for (int i = 0; i < 10; i++) {
                    Object newo = new Object();
                    container3.add(newo);

                    if (container3.size() == 5) {
                        lock.notify();
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("执行线程结束");
            }
        }).start();

    }
}
