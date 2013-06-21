package experiments;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class ContentionTestForMap {
	
	public static void main(String[] args) throws InterruptedException {
		Map<Boolean, Boolean> synchronizedMap = Collections.synchronizedMap(new HashMap<Boolean, Boolean>());
		System.out.println("Time taken with sync map : "+testPerformance(synchronizedMap));
		Map<Boolean, Boolean> concurrentMap = new ConcurrentHashMap<>();
		System.out.println("Time taken with concurrentMap : "+testPerformance(concurrentMap));
	}

	private static long testPerformance(
			final Map<Boolean, Boolean> synchronizedMap) throws InterruptedException {
		int threadCount = 2;
		long time = System.currentTimeMillis();
		final CountDownLatch latch = new CountDownLatch(threadCount);
		for(int i=0;i<threadCount; i++){
			new Thread(){
				public void run() {
					int count = 10000;
					for(int i=0; i<count; i++){
						for(int j=0; j<count; j++){
							synchronizedMap.put(Boolean.TRUE, Boolean.TRUE);
						}
					}
					latch.countDown();
				};
			}.start();
		}
		latch.await();
		return System.currentTimeMillis()-time;
	}

}
