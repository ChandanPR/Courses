package experiments;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {
	private static final int COUNT = 1000;
	private static final int THREADS = 200;

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch end = new CountDownLatch(2*THREADS);
		final AtomicLong al = new AtomicLong();
		for (int i = 0; i < THREADS; i++) {
			new Thread() {
				public void run() {
					try {
						start.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (int i = 0; i < COUNT; i++) {
						al.incrementAndGet();
					}
					end.countDown();
				};
			}.start();
		}

		for (int i = 0; i < THREADS; i++) {
			new Thread() {
				public void run() {
					try {
						start.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (int i = 0; i < COUNT; i++) {
						al.decrementAndGet();
					}
					end.countDown();
				};
			}.start();
		}
		start.countDown();
		end.await();
		System.out.println(al);
	}

}
