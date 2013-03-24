package lockcontention;

import java.util.concurrent.atomic.AtomicInteger;

public class LockContentionTest {
	private static final AtomicInteger INTEGER = new AtomicInteger();

	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(10000);
		for (int i = 0; i < 4; i++) {
			new Thread() {
				public void run() {
					while (true){
						synchronized (INTEGER) {
							INTEGER.incrementAndGet();
						}
					}
				};
			}.start();
		}

	}

}
