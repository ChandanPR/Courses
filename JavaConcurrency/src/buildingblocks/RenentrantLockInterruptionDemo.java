package buildingblocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RenentrantLockInterruptionDemo {

	public static void main(String[] args) {
		final Lock lock = new ReentrantLock();

		new Thread("T1") {
			public void run() {
				try {
					lock(lock, getName());
				} catch (InterruptedException e) {
//					e.printStackTrace();
				}
				System.out.println("t1 lock obtained");
				try {
					while (true) {
						sleep1(1);
					}
				} finally {
					unlock(lock, getName());
				}
			};
		}.start();

		Thread t2 = new Thread("T2") {
			public void run() {
				sleep1(100);
				try {
					lock(lock, getName(), true);
				} catch (InterruptedException e) {
					System.out.println("Interrupted in T2");
				}
				System.out.println("T2 lock obtained");
				try {
					//NOTHING
				} finally {
					//Exception in thread "T2" java.lang.IllegalMonitorStateException
					unlock(lock, getName());
				}
			};
		};
		t2.start();
		sleep1(1000);
		t2.interrupt();
	}

	private static void sleep1(int val) {
		try {
			Thread.sleep(val);
		} catch (InterruptedException e) {
		}
	}

	private static void lock(Lock lock, String name) throws InterruptedException {
		lock(lock, name, false);
	}

	private static void lock(Lock lock, String name, boolean lockInterruptibly) throws InterruptedException {
		System.out.println(name + " trying lock");
		if (lockInterruptibly) {
			lock.lockInterruptibly();
		} else {
			lock.lock();
		}
		System.out.println(name + " obtained lock");
	}

	private static void unlock(Lock lock, String name) {
		System.out.println(name + " trying to release lock");
		lock.unlock();
		System.out.println(name + " released lock");
	}

}
