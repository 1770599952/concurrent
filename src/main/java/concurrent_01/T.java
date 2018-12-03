package concurrent_01;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字，使一个变量在多个线程间可见。
 *  A B线程都用到一个变量，java默认是A线程中保留一份拷贝，这样如果B线程修改了该变量，则A线程未必知道
 *  使用volatile关键字，会让所有线程都会读到变量的修改值
 *
 *  在下面的代码中，running是存在于堆内存的t对象中，
 *  当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去
 *  读取堆内存，(因为我们使用了while死循环，导致了当前线程没有空闲去刷新running内存，如果在循环中进行等待的话，则线程会空闲时间去刷新running)
 *  这样，当主线程修改running的值的时候，t1线程感知不到，所以不会停止运行。
 *
 *  使用volatile，将强制所有线程都会去读取running的值。
 *
 *  volatile并不能保证多个线程共同修改running变量所带来的一致性问题，也就是说volatile不能替代sychronized
 */
public class T {
    volatile boolean running = true;

    private void m() {
        System.out.println("m start!");


        while (running) {
//            try {
//                TimeUnit.MILLISECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        T t = new T();

        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
