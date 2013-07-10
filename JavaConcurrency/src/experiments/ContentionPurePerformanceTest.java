package experiments;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This is also bad way of micro bench marking.
 * @author chandanpr
 *
 */
public class ContentionPurePerformanceTest {

	private static final int THREADS = Runtime.getRuntime()
			.availableProcessors();
	private static final int OPERATIONS = 100;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("THREADS : " + THREADS);
		int operations = OPERATIONS;
		while (operations > 0) {
			long time1 = testContention(new AtomicLong(), operations);
			long time2 = testNoContention(operations);
			System.out.println(operations + "\t\t" + time2 + "\t" + time1);
			operations *= 10;
		}
	}

	private static long testNoContention(int operations) {
		long time = System.currentTimeMillis();
		/**
		 * If assertion is not enabled, this whole stuff might get removed
		 * by JIT.
		 */
		AtomicLong myLong = new AtomicLong();
		for (int i = 0; i < THREADS / 2; i++) {
			myLong.incrementAndGet();
			myLong.decrementAndGet();
		}
		assert myLong.get() == 0;
		return System.currentTimeMillis() - time;
	}

	private static long testContention(final AtomicLong myLong, final int operations)
			throws InterruptedException {
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
						myLong.incrementAndGet();
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
						myLong.decrementAndGet();
					}
					endSignal.countDown();
				};
			}.start();
		}
		Thread.sleep(1000);
		long time = System.currentTimeMillis();
		startSignal.countDown();
		endSignal.await();
		assert myLong.get() == 0;
		return System.currentTimeMillis() - time;
	}

}
