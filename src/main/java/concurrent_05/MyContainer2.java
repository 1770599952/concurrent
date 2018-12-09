package concurrent_05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *  使用volatile解决
 *      新的问题：单独使用一个线程死循环监控，太浪费资源，有没有更好的办法？
 */
public class MyContainer2 {

    volatile List<Object> lists = new ArrayList<Object>();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer2 container2 = new MyContainer2();

        new Thread(()->{
            System.out.println("执行线程开始");
            for (int i = 0; i < 10; i++) {
                Object newo = new Object();
                container2.add(newo);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("执行线程结束");
        }).start();

        new Thread(new Thread(()->{
            System.out.println("监控线程开始");
            while(true){
                if(container2.size() == 5){
                    break;
                }
            }
            System.out.println("监控线程结束");
        })).start();
    }
}
