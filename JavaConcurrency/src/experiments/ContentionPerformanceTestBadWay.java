package experiments;

import java.util.concurrent.CountDownLatch;

/**
 * This is a bad example of performance measurement.
 * You are trying to measure both performance and contetions.
 * So the time for locking and unlocking also adds up.
 * Just use plain AtomicLong if you want to measure contetion performance.
 * @author chandanr
 *
 */
public class ContentionPerformanceTestBadWay {

	private static final int THREADS = Runtime.getRuntime()
			.availableProcessors();
	private static final int OPERATIONS = 100;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("THREADS : "+THREADS);
		int operations = OPERATIONS;
		while (operations > 0) {
			MyLong myLong = new MyLong();
			long time1 = testContention(myLong,operations);
			long time2 = testNoContention(operations);
			System.out.println(operations+"\t\t"+time2+"\t"+time1+"["+(myLong.getCount() - operations * 2)+"]");
			operations *= 10;
		}
	}
	
	private static long testNoContention(int operations){
		long time = System.currentTimeMillis();
		MyLong myLong = new MyLong();
		for(int i=0; i<THREADS/2;i++){
			myLong.doIncrement();
			myLong.doDecrement();
		}
		assert (myLong.getCount() - operations * 2) == 0;
		return System.currentTimeMillis() - time;
	}

	private static long testContention(final MyLong myLong,final int operations)
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
		long time = System.currentTimeMillis();
		startSignal.countDown();
		endSignal.await();
		return System.currentTimeMillis() - time;
	}

}
