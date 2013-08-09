package experiments;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 
 * 
 
 2013-07-23 12:29:09
Full thread dump Java HotSpot(TM) Client VM (25.0-b41 mixed mode):

"DestroyJavaVM" #10 prio=5 os_prio=0 tid=0x011cc800 nid=0x1418 waiting on condition [0x00000000]
   java.lang.Thread.State: RUNNABLE

"Thread 2" #9 prio=5 os_prio=0 tid=0x14435c00 nid=0x8bc waiting on condition [0x14abf000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x043cf8b0> (a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:834)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:868)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1197)
	at java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock.lock(ReentrantReadWriteLock.java:944)
	at office.WriteUpgradeDeadLockDemo$1.run(WriteUpgradeDeadLockDemo.java:24)

"Thread 1" #8 prio=5 os_prio=0 tid=0x14433000 nid=0x534 waiting on condition [0x1492f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x043cf8b0> (a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:834)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:868)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1197)
	at java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock.lock(ReentrantReadWriteLock.java:944)
	at office.WriteUpgradeDeadLockDemo$1.run(WriteUpgradeDeadLockDemo.java:24)

"Service Thread" #7 daemon prio=9 os_prio=0 tid=0x143bb400 nid=0x1cc4 runnable [0x00000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread0" #6 daemon prio=9 os_prio=2 tid=0x143b9c00 nid=0x1ea0 waiting on condition [0x00000000]
   java.lang.Thread.State: RUNNABLE

"Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x143b5400 nid=0xc9c waiting on condition [0x00000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x143b2000 nid=0x1c54 runnable [0x00000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=1 tid=0x1437d400 nid=0x19e8 in Object.wait() [0x1473f000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x04375a98> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:135)
	- locked <0x04375a98> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:151)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:189)

"Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x14378800 nid=0x1470 in Object.wait() [0x1463f000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x04375598> (a java.lang.ref.Reference$Lock)
	at java.lang.Object.wait(Object.java:502)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:156)
	- locked <0x04375598> (a java.lang.ref.Reference$Lock)

"VM Thread" os_prio=2 tid=0x14375800 nid=0x1924 runnable 

"VM Periodic Task Thread" os_prio=2 tid=0x143d6000 nid=0x558 waiting on condition 

JNI global references: 5




 * @author chandanr
 *
 */

public class WriteUpgradeDeadLockDemo {

	public static void main(String[] args) throws InterruptedException {

		final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
		final CountDownLatch start = new CountDownLatch(1);
		for (int i = 1; i <2; i++)
			new Thread("Thread " + i) {
				public void run() {
					rwl.readLock().lock();
					System.out.println(getName() + " obtained Read Lock");
					try {
						start.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(getName() + " trying write Lock");
					rwl.writeLock().lock();
					System.out.println(getName() + " obtained write Lock");
				};

			}.start();
			TimeUnit.SECONDS.sleep(1);
			start.countDown();
	}

}
