package concurrent_15;

public class VolatileStudy02 {

	volatile int count = 0;
	
	public static void main(String[] args) {
		VolatileStudy02 study02 = new VolatileStudy02();

		Thread thread01 = new Thread(new Runnable() {

			@Override
			public void run() {
				study02.count++;
				while (study02.count != 2) {}
				System.out.println("PAUSE");
			}
		});
		thread01.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(study02.count++);

	}
	
}
