package concurrent_11;

import java.util.concurrent.Callable;

/**
 * 第三种创建线程的方式：实现callable接口
 */
public class MyThread3 implements Callable {

    @Override
    public Object call() throws Exception {
        return "线程执行";
    }

    public static void main(String[] args) {
        MyThread3 myThread3 = new MyThread3();
        try {
            Object object = myThread3.call();
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
