package concurrent_11;

/**
 *  第二种创建线程的方式：实现runnable接口
 */
public class MyThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("线程执行");
    }

    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        myThread2.run();
    }
}
