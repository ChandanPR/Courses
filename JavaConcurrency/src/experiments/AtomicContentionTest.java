package experiments;

import java.util.concurrent.CountDownLatch;

public class AtomicContentionTest {
	private static final int THREADS = Runtime.getRuntime()
			.availableProcessors();
	private static final int OPERATIONS = 100;

	public static void main(String[] args) throws InterruptedException {
		int operations = OPERATIONS;
		while (operations > 0) {
			System.out.print("Operations : " + operations + " Contentions : ");
			for (int i = 0; i < 10; i++) {
				testContention(operations);
			}
			System.out.println();
			operations *= operations;
		}
	}

	private static void testContention(final int operations)
			throws InterruptedException {
		final MyLong myLong = new MyLong();
		final CountDownLatch startSignal = new CountDownLatch(1);
		final CountDownLatch endSignal = new CountDownLatch(THREADS);
		for (int i = 0; i < THREADS / 2; i++) {
			new Thread() {
				public void run() {
					try {
						startSignal.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (int i = 0; i < operations; i++) {
						myLong.doIncrement();
					}
					endSignal.countDown();
				};
			}.start();
		}
		for (int i = 0; i < THREADS / 2; i++) {
			new Thread() {
				public void run() {
					try {
						startSignal.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (int i = 0; i < operations; i++) {
						myLong.doDecrement();
					}
					endSignal.countDown();
				};
			}.start();
		}

		Thread.sleep(1000);
		startSignal.countDown();
		endSignal.await();
		System.out.print((myLong.getCount() - operations * 2) + ",");
	}

}
