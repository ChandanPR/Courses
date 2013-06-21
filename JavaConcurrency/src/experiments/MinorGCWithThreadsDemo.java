package experiments;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * -verbose:gc -XX:+PrintGCApplicationStoppedTime
 * @author chandanpr
 *
 */
public class MinorGCWithThreadsDemo {
	private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
	private static final int REPEATS = PROCESSORS*10;
	private static final int COUNT = 10000;

	public static void main(String[] args) throws InterruptedException {
		long withSingleThread = testWithSingleThread();
		long withLotOfThreads = testWithLotOfThreads();
		System.err.println(withSingleThread+":"+withLotOfThreads);
		System.err.println(withSingleThread-withLotOfThreads);
	}

	private static long testWithLotOfThreads() throws InterruptedException {
		
		long time = System.nanoTime();
		final CountDownLatch latch = new CountDownLatch(PROCESSORS);
		for(int i=0; i<REPEATS; i++){
			new Thread(){
				public void run() {
					for(int i=0; i<COUNT; i++){
						List<Object> list = new ArrayList<>();
						for(int j=0; j<COUNT; j++){
							list.add(new Object());
						}
					}
					latch.countDown();
				};
			}.start();
		}
		latch.await();
		return System.nanoTime() - time;
	}
	
	private static long testWithSingleThread() throws InterruptedException {
		long time = System.nanoTime();
		for(int i=0; i<REPEATS; i++){
			for(int k=0; k<COUNT; k++){
				List<Object> list = new ArrayList<>();
				for(int j=0; j<COUNT; j++){
					list.add(new Object());
				}
			}
		}
		return System.nanoTime() - time;
	}
}
