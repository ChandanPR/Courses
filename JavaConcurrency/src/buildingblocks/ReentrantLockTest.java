package buildingblocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	public static void main(String[] args) {
		final Lock lock = new ReentrantLock();
		new Thread("T1") {
			public void run() {
				int count = 1;
				while (count < 10) {
					lock(lock, getName());
					sleep1(100);
					count++;
				}

				count = 1;
				while (count < 10) {
					unlock(lock, getName());
					sleep1(100);
					count++;
				}
			};
		}.start();

		new Thread("T2") {
			public void run() {
				int count = 1;
				while (count < 10) {
					lock(lock, getName());
					sleep1(100);
					count++;
				}

				count = 1;
				while (count < 10) {
					unlock(lock, getName());
					sleep1(100);
					count++;
				}
			};
		}.start();
	}

	private static void sleep1(int val) {
		try {
			Thread.sleep(val);
		} catch (InterruptedException e) {
		}
	}

	private static void lock(Lock lock, String name) {
		System.out.println(name + " trying lock");
		lock.lock();
		System.out.println(name + " obtained lock");
	}

	private static void unlock(Lock lock, String name) {
		System.out.println(name + " trying to release lock");
		lock.unlock();
		System.out.println(name + " released lock");
	}

}
