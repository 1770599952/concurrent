package concurrent_02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *   volatile并不能保证多个线程共同修改running变量所带来的一致性问题，也就是说volatile不能替代sychronized
 *
 *   因为count++不是原子操作
 *      1.读取主内存中count的值，赋值给一个局部变量tmp
 *      2.tmp+1
 *      3.将tmp赋值给count
 *
 *   如果线程A，B同时执行count++操作，A线程执行到第二步，这是切换B线程完毕，count值为1，这时切换到线程A，执行第三步
 *   结果count = 1
 */
public class T {
    volatile int count = 0;

    private void m() {
        for (int i = 0; i < 10000; i++) {
          //  AtomicInteger.getAndIncrement();
            count++;
        }
    }

    public static void main(String[] args) {
        T t = new T();
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }
        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}
