package concurrent_17;

import java.util.Random;
import java.util.UUID;

public class MyThreadExecutorTest {
    public static void main(String[] args) {
        MyThreadExecutor executor = new MyThreadExecutor(3, 3, 3);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println(UUID.randomUUID());
            });
        }
    }
}
