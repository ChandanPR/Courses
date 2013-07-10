package gc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * -verbose:gc -XX:+PrintGCApplicationStoppedTime
 * -XX:+PrintGCApplicationConcurrentTime -XX:+PrintSafepointStatistics
 * -XX:PrintSafepointStatisticsCount=0 -verbose:gc
 * -XX:+PrintGCApplicationStoppedTime -XX:+PrintSafepointStatistics
 * -XX:PrintSafepointStatisticsCount=1 -verbose:gc
 * -XX:+PrintGCApplicationStoppedTime -XX:+PrintSafepointStatistics
 * -XX:PrintSafepointStatisticsCount=1 -XX:+TraceSafepointCleanupTime
 * 
 * -XX:+PrintSafepointStatistics -XX:PrintSafepointStatisticsCount=0 These two
 * will print safepoint information on every safepoint operation.
 * 
 * -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime The
 * former will print the amount of time the JVM spent executing Java threads
 * between safepoint operations. The latter will print the amount of time the
 * JVM spent stopped in a JVM safepoint.
 * 
 * 
 * CHECK FOR THESE FLAGS: -server -Xms8G -Xmx8G -XX:PermSize=420m
 * -XX:MaxPermSize=420m -Xmn1536m -XX:SurvivorRatio=1 -verbose:gc
 * -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps
 * -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime
 * -XX:+PrintReferenceGC -XX:+PrintGCDetails -XX:+PrintAdaptiveSizePolicy
 * -XX:+PrintFlagsFinal -XX:+PrintTenuringDistribution -XX:+UseConcMarkSweepGC
 * -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled
 * -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses
 * 
 * @author chandanpr
 * 
 */
public class MinorGCWithThreadsDemo {
	private static final int PROCESSORS = Runtime.getRuntime()
			.availableProcessors();
	private static final int REPEATS = PROCESSORS * 10 * 100;
	private static final int COUNT = 10000;

	public static void main(String[] args) throws InterruptedException {
		// long withSingleThread = testWithSingleThread();
		long withLotOfThreads = testWithLotOfThreads();
		// System.err.println("withSingleThread : "+withSingleThread);
		System.err.println("withLotOfThreads : " + withLotOfThreads);
	}

	private static long testWithLotOfThreads() throws InterruptedException {

		long time = System.nanoTime();
		final CountDownLatch latch = new CountDownLatch(PROCESSORS);
		for (int i = 0; i < REPEATS; i++) {
			new Thread() {
				public void run() {
					for (int i = 0; i < COUNT; i++) {
						List<Object> list = new ArrayList<>();
						for (int j = 0; j < COUNT; j++) {
							// list.add(new Object());
							new Object();
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
		for (int i = 0; i < REPEATS; i++) {
			for (int k = 0; k < COUNT; k++) {
				List<Object> list = new ArrayList<>();
				for (int j = 0; j < COUNT; j++) {
					new Object();
				}
			}
		}
		return System.nanoTime() - time;
	}
}
