package concurrent_11;

/**
 *  第一种创建线程的方式 ： 继承thread类
 */
public class MyThread1 extends Thread{

    @Override
    public void run() {
        System.out.println("线程执行！");
    }

    public static void main(String[] args) {
        MyThread1 myThread = new MyThread1();
        myThread.start();
    }
}
