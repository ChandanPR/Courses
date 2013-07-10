package experiments;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLBugDemo {

	public static void main(String[] args) {
		final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		final CyclicBarrier barrier = new CyclicBarrier(2);

		Thread t1 = new EvilThread() {
			public void run() {
				try {
					lock.readLock().lock();
					System.out.println("T1 Locked");
					barrier.await();
					// T2 locks
					barrier.await();
					// T3 locks
					// T3 unlocks
					barrier.await();
					// T2 unlocks
					barrier.await();
					lock.readLock().unlock();
					System.out.println("T1 Unlocked");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		Thread t2 = new EvilThread() {
			public void run() {
				try {
					// T1 locks
					barrier.await();
					lock.readLock().lock();
					System.out.println("T2 Locked");
					barrier.await();
					// T3 locks
					// T3 unlocks
					barrier.await();
					lock.readLock().unlock();
					System.out.println("T2 Unlocked");
					barrier.await();
					// T1 unlocks
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		Thread t3 = new EvilThread() {
			public void run() {
				try {
					// T1 locks
					barrier.await();
					// T2 locks
					barrier.await();
					System.out.println("T3 Locked");
					lock.readLock().lock();
					lock.readLock().unlock();
					System.out.println("T3 Unlocked");
					barrier.await();
					// T2 unlocks
					barrier.await();
					// T3 unlocks
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};

		t1.start();
		t2.start();
		t3.start();
	}

	static class EvilThread extends Thread {
		public long getId() {
			return 42L;
		}
	}

}
