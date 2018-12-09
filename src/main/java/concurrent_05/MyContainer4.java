package concurrent_05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 * * 整个通信过程比较繁琐
 */
public class MyContainer4 {
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        MyContainer4 container4 = new MyContainer4();
        Object lock = new Object();
        new Thread(new Thread(() -> {
            synchronized (lock) {
                System.out.println("监控线程开始");
                if (container4.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("监控线程结束");
                lock.notify();
            }
        })).start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("执行线程开始");
                for (int i = 0; i < 10; i++) {
                    Object newo = new Object();
                    container4.add(newo);

                    if (container4.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
