package experiments;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockLeakDemo {

	public static void main(String[] args) throws InterruptedException {
		final Map<Object, Boolean> lockMap = new WeakHashMap<>();
		for (;;) {
			Thread t = new Thread() {
				public void run() {
					for (int i = 0; i < 100000; i++){
						ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
						lock.writeLock().lock();
						lockMap.put(lock, Boolean.TRUE);
					}
				};
			};
			t.start();
			t.join();
			System.gc();System.gc();System.gc();System.gc();System.gc();System.gc();System.gc();
			Thread.sleep(1000);
			System.gc();System.gc();System.gc();System.gc();System.gc();System.gc();System.gc();
			System.out.println(lockMap.size());
		}
	}

}
