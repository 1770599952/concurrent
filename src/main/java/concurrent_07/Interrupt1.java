package concurrent_07;

/**
 * 本目录用于探讨Java中Interrupt的作用
 * 1.interrupt:在一个线程中调用另一个线程的interrupt方法，会为另一个线程标记为中断状态。interrupt不能中断在运行中的线程，它只能改变线程的中断状态。
 * 2.isInterrupt：用于判断当前线程的中断状态。
 * 3.interrupted：是Thraed的static方法，用于恢复线程的中断状态。
 *
 *
 */
public class Interrupt1 {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyInterrupt());
        thread.start();
        thread.interrupt();
    }

    private static class MyInterrupt implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Yes,I am interruted,but I am still running");
                } else {
                    System.out.println("not yet interrupted");
                }
            }
        }
    }
}
