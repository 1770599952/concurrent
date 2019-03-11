package concurrent_12;

/**
 * notify : 唤醒等待对象锁的线程
 */
public class MyThread_Notify extends Thread{

    public static void main(String[] args) {
        MyThread_Notify notify = new MyThread_Notify();
        notify.start();
        try {
            notify.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (notify) {
            notify.notify();
        }
        System.out.println("主线程执行结束");
    }

    @Override
    public synchronized void run() {
        System.out.println("子线程开始执行");
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程执行结束");
    }
}
