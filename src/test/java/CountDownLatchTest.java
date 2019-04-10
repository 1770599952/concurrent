import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        try {
           boolean result =  countDownLatch.await(10000,TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("countdownlatch");
    }
}
