package experiments;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerPerformanceTest {
	
	private static final int COUNT = 100000000;

	public static void main(String[] args) throws InterruptedException {
		final AtomicInteger integer = new AtomicInteger();
		
		for(int i=0; i<1000000; i++){
			integer.incrementAndGet();
		}
		
		for(int i=0; i<1000000; i++){
			integer.decrementAndGet();
		}
		
		System.out.println("Iterations Done");
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch end = new CountDownLatch(4);
		for(int i=0; i<2; i++){
			new Thread(){
				public void run() {
					try {
						start.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for(int i=0; i<1000000; i++){
						integer.incrementAndGet();
					}
					end.countDown();
				}
			}.start();
		}
		
		for(int i=2; i<4; i++){
			new Thread(){
				public void run() {
					try {
						start.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for(int i=0; i<COUNT; i++){
						integer.decrementAndGet();
					}
					end.countDown();
				}
			}.start();
		}
		
		long time = System.currentTimeMillis();
		start.countDown();
		end.await();
		System.out.println("Total Time "+(System.currentTimeMillis() - time));
	}

}
