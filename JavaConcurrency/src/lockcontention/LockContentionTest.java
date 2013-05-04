package lockcontention;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class LockContentionTest {
	private static final AtomicInteger INTEGER = new AtomicInteger();
	private static final AtomicInteger[] INTEGERS = new AtomicInteger[4];
	private static final CountDownLatch contentionLatch = new CountDownLatch(4);
	private static final CountDownLatch nonContentionLatch = new CountDownLatch(
			4);

	public static void main(String[] args) throws InterruptedException {
		testContention();
		testWithoutContention();
	}

	private static void testWithoutContention() {
		long time = System.currentTimeMillis();
		for (int i = 0; i < 4; i++) {
			final int index = i;
			new Thread() {
				public void run() {
					synchronized (INTEGERS[index]) {
						while (INTEGERS[index].get() > 0) {
							INTEGERS[index].incrementAndGet();
						}
						nonContentionLatch.countDown();
					}
				};
			}.start();
		}
		try {
			nonContentionLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("LockContentionTest.testWithoutContention() "
				+ (System.currentTimeMillis() - time));
	}

	private static void testContention() throws InterruptedException {
		long time = System.currentTimeMillis();
		for (int i = 0; i < 4; i++) {
			INTEGERS[i] = new AtomicInteger();
			new Thread() {
				public void run() {
					synchronized (INTEGER) {
						while (INTEGER.get() > 0) {
							INTEGER.incrementAndGet();
						}
						contentionLatch.countDown();
					}
				};
			}.start();
		}
		try {
			contentionLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("LockContentionTest.testContention()"
				+ (System.currentTimeMillis() - time));
	}

}
