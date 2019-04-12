package concurrent_10;

import java.util.LinkedList;
import java.util.List;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 * @author liu
 */
public class TicketSeller1 {

    /**
     * 火车票容器
     */
    static List<String> tikets = new LinkedList<String>();

    static {
        for (int i = 0; i < 10000; i++) {
            tikets.add("NO." + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tikets.size() > 0) {
                    System.out.println("销售了：" + tikets.remove(0));
                }
            }).start();
        }

    }

}
