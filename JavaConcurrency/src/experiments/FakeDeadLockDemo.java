package experiments;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * 
  
 Three options to detect deadlock
 1. ThreadMXBean
 2. kill -3 or CTRL+BREAK
 3. jstack processid
 
 * This demonstrates a fake dead lock
 * Since we are using tryLock for the second lock, the deadlock is temporary.
 * But the JVM implementations won't be able to recognize whether the deadlock is temporary or not.
 * 
 * If the threads involved in deadlock are in TIMED_WAITING state then you can assume the deadlock is temporary.
 * 
 * The log information from jstack is as below:
 * Look the threads involved in the deadlock are in TIMED_WAITING state.
 * 
 * 
 Chandans-MacBook-Pro:bin chandanpr$ jstack 1438
2013-06-16 15:02:32
Full thread dump Java HotSpot(TM) 64-Bit Server VM (23.6-b03 mixed mode):

"Attach Listener" daemon prio=5 tid=0x00007f922c804000 nid=0x390b waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"DestroyJavaVM" prio=5 tid=0x00007f922c072800 nid=0x90b waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"T1" prio=5 tid=0x00007f922c071800 nid=0x4c03 waiting on condition [0x000000010f681000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000007e460f080> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
	at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:226)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireNanos(AbstractQueuedSynchronizer.java:929)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.tryAcquireNanos(AbstractQueuedSynchronizer.java:1245)
	at java.util.concurrent.locks.ReentrantLock.tryLock(ReentrantLock.java:445)
	at experiments.FakeDeadLockDemo$1.run(FakeDeadLockDemo.java:18)

"T0" prio=5 tid=0x00007f922c071000 nid=0x4b03 waiting on condition [0x000000010f57e000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000007e460f0b0> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
	at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:226)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireNanos(AbstractQueuedSynchronizer.java:929)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.tryAcquireNanos(AbstractQueuedSynchronizer.java:1245)
	at java.util.concurrent.locks.ReentrantLock.tryLock(ReentrantLock.java:445)
	at experiments.FakeDeadLockDemo$1.run(FakeDeadLockDemo.java:18)

"Service Thread" daemon prio=5 tid=0x00007f922c03c800 nid=0x4903 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread1" daemon prio=5 tid=0x00007f922c03c000 nid=0x4803 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" daemon prio=5 tid=0x00007f922c03a000 nid=0x4703 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" daemon prio=5 tid=0x00007f922c001800 nid=0x4603 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" daemon prio=5 tid=0x00007f922b889800 nid=0x3703 in Object.wait() [0x000000010ecea000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000007e4565798> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:135)
	- locked <0x00000007e4565798> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:151)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:177)

"Reference Handler" daemon prio=5 tid=0x00007f922b888800 nid=0x3603 in Object.wait() [0x000000010ebe7000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000007e4565320> (a java.lang.ref.Reference$Lock)
	at java.lang.Object.wait(Object.java:503)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:133)
	- locked <0x00000007e4565320> (a java.lang.ref.Reference$Lock)

"VM Thread" prio=5 tid=0x00007f922b886000 nid=0x3503 runnable 

"GC task thread#0 (ParallelGC)" prio=5 tid=0x00007f922b842800 nid=0x3103 runnable 

"GC task thread#1 (ParallelGC)" prio=5 tid=0x00007f922b843000 nid=0x3203 runnable 

"GC task thread#2 (ParallelGC)" prio=5 tid=0x00007f922b843800 nid=0x3303 runnable 

"GC task thread#3 (ParallelGC)" prio=5 tid=0x00007f922b844800 nid=0x3403 runnable 

"VM Periodic Task Thread" prio=5 tid=0x00007f922c008800 nid=0x4a03 waiting on condition 

JNI global references: 113


Found one Java-level deadlock:
=============================
"T1":
  waiting for ownable synchronizer 0x00000007e460f080, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
  which is held by "T0"
"T0":
  waiting for ownable synchronizer 0x00000007e460f0b0, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
  which is held by "T1"

Java stack information for the threads listed above:
===================================================
"T1":
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000007e460f080> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
	at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:226)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireNanos(AbstractQueuedSynchronizer.java:929)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.tryAcquireNanos(AbstractQueuedSynchronizer.java:1245)
	at java.util.concurrent.locks.ReentrantLock.tryLock(ReentrantLock.java:445)
	at experiments.FakeDeadLockDemo$1.run(FakeDeadLockDemo.java:18)
"T0":
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000007e460f0b0> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
	at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:226)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireNanos(AbstractQueuedSynchronizer.java:929)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.tryAcquireNanos(AbstractQueuedSynchronizer.java:1245)
	at java.util.concurrent.locks.ReentrantLock.tryLock(ReentrantLock.java:445)
	at experiments.FakeDeadLockDemo$1.run(FakeDeadLockDemo.java:18)

Found 1 deadlock.
 *
 */
public class FakeDeadLockDemo {

	public static void main(String[] args) {
		final Lock[] locks = { new ReentrantLock(), new ReentrantLock() };
		for (int i = 0; i < 2; i++) {
			final int j = i;
			new Thread("T" + i) {
				@Override
				public void run() {
					locks[j % 2].lock();
					try {
						if (locks[(j + 1) % 2].tryLock(3600, TimeUnit.SECONDS))
							System.out.println("Thread " + getName()
									+ " got both the locks");
					} catch (InterruptedException e) {
						System.err.println("Thread " + getName()
								+ " failed to get second lock");
					} finally {
						try {
							locks[(j + 1) % 2].unlock();
						} catch (IllegalMonitorStateException imex) {
							System.err.println("Thread " + getName()
									+ " doesn't own the second lock to unlock");
						}
						locks[j % 2].unlock();
					}
				}
			}.start();
		}
	}

}
