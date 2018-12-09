package concurrent_05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * syschronied + wait + notify太过于笨重
 * <p>
 * 解决方案：CountDownLatch不涉及锁定，当count的值为零时当前线程继续运行
 */
public class MyContainer5 {
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        MyContainer5 container5 = new MyContainer5();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Thread(() -> {
            System.out.println("监控线程开始");
            if (container5.size() != 5) {
                try {
                    latch.await();
                    //也可以指定等待时间
                    //latch.await(5000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("监控线程结束");
        })).start();

        new Thread(() -> {
            System.out.println("执行线程开始");
            for (int i = 0; i < 10; i++) {
                Object newo = new Object();
                container5.add(newo);

                if (container5.size() == 5) {
                    latch.countDown();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("执行线程结束");

        }).start();

    }
}
