package experiments;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Signal notifies the Thread waiting for the longest time.
 * But it all depends on whether the thread will be able to acquire the lock for execution.
 * @author chandanr
 *
 */
public class ConditionTest2 {

	public static void main(String[] args) throws InterruptedException {
		final MyLock myLock = new MyLock();
		final Condition condition = myLock.newCondition();
		for (int i = 0; i < 10; i++) {
			new Thread("Thread " + i) {
				public void run() {
					myLock.lock();
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						myLock.unlock();
					}
					System.out.println(getName() + " Completed");
				};
			}.start();
		}
		Thread.sleep(2000);
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			myLock.lock();
			try {
				condition.signal();
			} finally {
				myLock.unlock();
			}
		}
	}

	static class MyLock extends ReentrantLock {
		@Override
		public Condition newCondition() {
			return new ConditionWrapper(super.newCondition());
		}
	}

	static class ConditionWrapper implements Condition {

		final Condition condition;

		ConditionWrapper(Condition condition) {
			this.condition = condition;
		}

		@Override
		public void await() throws InterruptedException {
			System.out.println(Thread.currentThread().getName() + "await()");
			condition.await();
		}

		@Override
		public void awaitUninterruptibly() {
			System.out.println(Thread.currentThread().getName()
					+ "awaitUninterruptibly()");
			condition.awaitUninterruptibly();
		}

		@Override
		public long awaitNanos(long nanosTimeout) throws InterruptedException {
			System.out.println(Thread.currentThread().getName()
					+ "awaitNanos()");
			return condition.awaitNanos(nanosTimeout);
		}

		@Override
		public boolean await(long time, TimeUnit unit)
				throws InterruptedException {
			System.out.println(Thread.currentThread().getName()
					+ "await(long time, TimeUnit unit)");
			return condition.await(time, unit);
		}

		@Override
		public boolean awaitUntil(Date deadline) throws InterruptedException {
			System.out.println(Thread.currentThread().getName() + "awaitUntil");
			return condition.awaitUntil(deadline);
		}

		@Override
		public void signal() {
//			System.out.println(Thread.currentThread().getName() + "signal");
			condition.signal();
		}

		@Override
		public void signalAll() {
			System.out.println(Thread.currentThread().getName() + "signalAll");
			condition.signalAll();
		}

	}

}
