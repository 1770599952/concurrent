package concurrent_10;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * ConcurrentLinkedQueue
 */
public class TicketSeller3 {
    static Queue<String> tickets = new ConcurrentLinkedDeque<String>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("NO." + i);
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<10; i++) {
            new Thread(()->{
                while(true) {
                    String s = tickets.poll();
                    if(s == null) break;
                    else System.out.println("销售了--" + s);
                }
            }).start();
        }
    }
}
