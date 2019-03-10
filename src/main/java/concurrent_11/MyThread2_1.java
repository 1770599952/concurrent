package concurrent_11;

/**
 * 异常无法跨线程传播，所以默认情况下，主线程无法捕捉子线程抛出的异常
 */
public class MyThread2_1 extends Thread{

    @Override
    public void run() {
        throw new RuntimeException("子线程异常");
    }

    public static void main(String[] args) {
        MyThread2_1 myThread2_1 =  new MyThread2_1();
        myThread2_1.start();
        myThread2_1.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程名：" + t.getName());
                System.out.println("线程异常：" + e.getMessage());
            }
        });
    }
}

