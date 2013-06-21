package experiments;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class RaceConditionDemo {

	private int value = 0;
	private static AtomicLong raceConditions = new AtomicLong();

	public void add(int v) {
		int temp = value;
		value += v;
		if (value != temp + v) {
			raceConditions.incrementAndGet();
		}
	}

	public void test() throws InterruptedException {
		int threadCount = 10;
		final CountDownLatch latch = new CountDownLatch(threadCount*2);
		for (int i = 0; i < threadCount; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 10000; j++) {
						add(1);
					}
					latch.countDown();
				};
			}.start();
		}
		
		for (int i = 0; i < threadCount; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 10000; j++) {
						add(-1);
					}
					latch.countDown();
				};
			}.start();
		}
		latch.await();
		System.out.println(raceConditions.get()+":"+value);
	}
	
	public static void main(String[] args) throws InterruptedException {
		new RaceConditionDemo().test();
	}

}
