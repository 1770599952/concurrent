package concurrent_12;

/**
 * 线程的五种状态
 * 1.新建 :新建线程但并未启动的状态
 * 2.就绪 :执行start，或者CPU时间片用完
 * 3.阻塞 ：
 * 同步阻塞：锁被其它线程占用
 * 主动阻塞：线程让出CPU的执行权，sleep，join
 * 等待阻塞：wait
 * 4.运行
 * 5.终止
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("线程执行");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行结束");
    }

    /**
     * 就绪状态
     */
    public void startThread() {
        start();
    }

    /**
     * 阻塞状态（主动阻塞） - sleep ：不释放锁
     */
    public void blockThread1() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 阻塞状态（等待阻塞） - wait ：释放锁
     */
    public void blockThread2() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 阻塞状态（主动阻塞） - join ：主线程等到子线程执行完在执行,父线程重新运行，都是由底层的调度引起的。
     * 本质也是wait
     */
    public void blockThraed3() {
        try {
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        waitTest(myThread);
        System.out.println("主线程执行");
    }

    public static void joinTest(Thread thread){
        synchronized (thread){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void waitTest(Thread thread){
        synchronized (thread){
            try {
                thread.wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
