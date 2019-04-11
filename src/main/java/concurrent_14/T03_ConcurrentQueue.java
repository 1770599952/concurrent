package concurrent_14;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 双端队列
 */
public class T03_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            strs.offer("a" + i);  //add
        }

        System.out.println(strs);

        System.out.println(strs.size());

        System.out.println(strs.poll());
        System.out.println(strs.size());

        System.out.println(strs.peek());
        System.out.println(strs.size());
        System.out.println(strs);

    }
}
