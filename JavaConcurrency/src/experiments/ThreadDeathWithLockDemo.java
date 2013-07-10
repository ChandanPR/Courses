package experiments;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadDeathWithLockDemo {

	public static void main(String[] args) throws InterruptedException {
		final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
		Thread t1 = new Thread("Writer Thread") {
			public void run() {
				System.out.println(getName() + " Getting lock");
				rwLock.writeLock().lock();
				System.out.println(getName() + " obtained lock");
			};
			
		};
		t1.start();
		t1.join();

		new Thread("Reader Thread") {
			public void run() {
				System.out.println(getName() + " Getting lock");
				rwLock.readLock().lock();
				System.out.println(getName() + " obtained lock");
			};
			
		}.start();

		new Thread("Writer Thread 1") {
			public void run() {
				System.out.println(getName() + " Getting lock");
				rwLock.writeLock().lock();
				System.out.println(getName() + " obtained lock");
			};

		}.start();
	}

}
