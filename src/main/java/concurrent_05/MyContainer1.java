package concurrent_05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 * 分析下面这个程序，能完成这个功能吗？
 *   实际问题的根源还是在于，监控线程启动while死循环，线程没有时间去更新本地线程缓存与堆内存进行同步。
 * @author liu
 */
public class MyContainer1 {

    List<Object> lists = new ArrayList<Object>();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer1 container1 = new MyContainer1();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                Object newo = new Object();
                container1.add(newo);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Thread(()->{
            while(true){
                if(container1.size() == 5){
                    break;
                }
            }
            System.out.println("监控线程结束");
        })).start();
    }
}
