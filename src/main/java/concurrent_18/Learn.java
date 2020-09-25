package concurrent_18;

import java.util.concurrent.CountDownLatch;

/**
     * 我们提供了一个类：
     * <p>
     * public class Foo {
     *   public void first() { print("first"); }
     *   public void second() { print("second"); }
     *   public void third() { print("third"); }
     * }
     * 三个不同的线程将会共用一个 Foo 实例。
     * <p>
     * 线程 A 将会调用 first() 方法
     * 线程 B 将会调用 second() 方法
     * 线程 C 将会调用 third() 方法
     * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行
     * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Learn {
    public static void main(String[] args) throws Exception {
        Foo foo = new Foo();
        Thread thread1 = new Thread(() -> {
            try {
                foo.first(() ->
                        System.out.println("first")
                );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                foo.second(() ->
                        System.out.println("second")
                );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                foo.third(() ->
                        System.out.println("third")
                );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Foo {
    CountDownLatch one = new CountDownLatch(1);
    CountDownLatch two = new CountDownLatch(1);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        one.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        one.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        two.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        two.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}