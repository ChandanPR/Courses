package experiments;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockBugDemo {

	public static void main(String[] args) throws InterruptedException {
		final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		Thread t1 = new LegalThread(1) {
			@Override
			public void run() {
				try {
					lock.readLock().lock();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		t1.start();
		t1.join();

		Thread t2 = new LegalThread(t1.getId()) {// t1 was terminated; according
													// to the Thread.getId()
													// specification one can
													// reuse id of t1
			// Thread t2 = new LegalThread(2) { //use this line of code to break
			// the trick
			@Override
			public void run() {
				try {
					lock.readLock().unlock();// the trick
					System.out.println("the trick was successfully performed");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		t2.start();
		t2.join();
	}

	static class LegalThread extends Thread {
		private final long id;

		public LegalThread(long id) {
			this.id = id;
		}

		@Override
		public long getId() {
			return id;
		}
	}

}
