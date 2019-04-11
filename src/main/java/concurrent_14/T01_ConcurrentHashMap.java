package concurrent_14;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913
 * http://www.educity.cn/java/498061.html
 * 阅读concurrentskiplistmap
 */
public class T01_ConcurrentHashMap {
    public static void main(String[] args) {
        /**
         * 高并发并且排序的容器
         */
        Map<String, String> map = new ConcurrentSkipListMap<String, String>();

        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    map.put("num" + r.nextInt(10000), "num" + r.nextInt(10000));
                    latch.countDown();
                }
            });
        }

        Arrays.asList(ths).forEach(t -> t.start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        //https://github.com/EduMoral/edu/blob/master/concurrent/src/yxxy/c_025/T01_ConcurrentMap.java
    }
}
