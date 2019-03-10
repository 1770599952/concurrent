package concurrent_12;

public class MyThread_Wait extends Thread{

    @Override
    public void run() {
        System.out.println("子线程开始执行");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程执行结束");
    }

    /**
     * Causes the current thread to wait until another thread invokes the
     * {@link java.lang.Object#notify()} method or the
     * {@link java.lang.Object#notifyAll()} method for this object.
     * In other words, this method behaves exactly as if it simply
     * performs the call {@code wait(0)}.
     * <p>
     **/
    public static void testWait1(Thread thread){
        try {
            synchronized (thread) {
                thread.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Causes the current thread to wait until either another thread invokes the
     * {@link java.lang.Object#notify()} method or the
     * {@link java.lang.Object#notifyAll()} method for this object, or a
     * specified amount of time has elapsed.
     * */
    public static void testWait2(Thread thread){
        try {
            synchronized (thread) {
                thread.wait(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThread_Wait threadWait = new MyThread_Wait();
        threadWait.start();
        testWait2(threadWait);
        System.out.println("主线程执行结束");
    }
}
