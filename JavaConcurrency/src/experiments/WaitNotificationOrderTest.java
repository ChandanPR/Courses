package experiments;

import java.util.concurrent.TimeUnit;

public class WaitNotificationOrderTest {
	static int count = 0;

	public static void main(String[] args) {
		final InternalLock lock = new InternalLock();

		for (int i = 0; i < 2; i++) {
			final int j = i;
			new Thread() {
				public void run() {
					lock.waitHere(j);
				};
			}.start();
		}
		
		new Thread(){
			public void run() {
				int i = 2;
				while(i-- > 0){
					synchronized (lock) {
						System.out.println("NOTIFYING");
						lock.notify();
					}
				}
			};
		}.start();
	}

	static class InternalLock {

		public void waitHere(int index) {
			synchronized (this) {
				System.out.println(index + " going to wait");
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(index + " done wait");
			}
		}
	}
	
	private static void sleep1(int time) {
		try {
			TimeUnit.NANOSECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

}
