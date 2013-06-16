package buildingblocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUnlockTest {

	public static void main(String[] args) {
		final Lock lock = new ReentrantLock();

		new Thread("T1") {
			public void run() {
				sleep1(1000);
				lock(lock, getName());
				sleep1(1000);
				unlock(lock, getName());
			};
		}.start();

		new Thread("T2") {
			public void run() {
				unlock(lock, getName());
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
		try {
			lock.unlock();
		} catch (IllegalMonitorStateException imex) {
			System.out
					.println("IllegalMonitorStateException while unlocking in "
							+ name);
			;
		}
		System.out.println(name + " released lock");
	}
}
