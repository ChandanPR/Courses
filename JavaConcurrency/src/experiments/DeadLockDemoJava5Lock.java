package experiments;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 
 Three options to detect deadlock
 1. ThreadMXBean
 2. kill -3 or CTRL+BREAK
 3. jstack processid
 
 * 
 * 
 * 

Chandans-MacBook-Pro:bin chandanpr$ jstack 1460
2013-06-16 15:13:48
Full thread dump Java HotSpot(TM) 64-Bit Server VM (23.6-b03 mixed mode):

"Attach Listener" daemon prio=5 tid=0x00007f9acd006800 nid=0x3a0b waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"DestroyJavaVM" prio=5 tid=0x00007f9acd005800 nid=0x90b waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"T1" prio=5 tid=0x00007f9acc09a000 nid=0x4c03 waiting on condition [0x000000010e287000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000007e46af7f8> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:186)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:834)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:867)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1197)
	at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:214)
	at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:290)
	at experiments.DeadLockDemoJava5Lock$1.run(DeadLockDemoJava5Lock.java:19)

"T0" prio=5 tid=0x00007f9acc099000 nid=0x4b03 waiting on condition [0x000000010e184000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000007e46af828> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:186)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:834)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:867)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1197)
	at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:214)
	at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:290)
	at experiments.DeadLockDemoJava5Lock$1.run(DeadLockDemoJava5Lock.java:19)

"Service Thread" daemon prio=5 tid=0x00007f9acc05e000 nid=0x4903 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread1" daemon prio=5 tid=0x00007f9acc05d000 nid=0x4803 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" daemon prio=5 tid=0x00007f9acc05b800 nid=0x4703 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" daemon prio=5 tid=0x00007f9acc058800 nid=0x4603 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" daemon prio=5 tid=0x00007f9acd000800 nid=0x3703 in Object.wait() [0x000000010d8f3000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000007e4605798> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:135)
	- locked <0x00000007e4605798> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:151)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:177)

"Reference Handler" daemon prio=5 tid=0x00007f9acc042000 nid=0x3603 in Object.wait() [0x000000010d7f0000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000007e4605320> (a java.lang.ref.Reference$Lock)
	at java.lang.Object.wait(Object.java:503)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:133)
	- locked <0x00000007e4605320> (a java.lang.ref.Reference$Lock)

"VM Thread" prio=5 tid=0x00007f9acc03f000 nid=0x3503 runnable 

"GC task thread#0 (ParallelGC)" prio=5 tid=0x00007f9acc810000 nid=0x3103 runnable 

"GC task thread#1 (ParallelGC)" prio=5 tid=0x00007f9acc810800 nid=0x3203 runnable 

"GC task thread#2 (ParallelGC)" prio=5 tid=0x00007f9acc811000 nid=0x3303 runnable 

"GC task thread#3 (ParallelGC)" prio=5 tid=0x00007f9acc812000 nid=0x3403 runnable 

"VM Periodic Task Thread" prio=5 tid=0x00007f9acc042800 nid=0x4a03 waiting on condition 

JNI global references: 113


Found one Java-level deadlock:
=============================
"T1":
  waiting for ownable synchronizer 0x00000007e46af7f8, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
  which is held by "T0"
"T0":
  waiting for ownable synchronizer 0x00000007e46af828, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
  which is held by "T1"

Java stack information for the threads listed above:
===================================================
"T1":
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000007e46af7f8> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:186)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:834)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:867)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1197)
	at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:214)
	at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:290)
	at experiments.DeadLockDemoJava5Lock$1.run(DeadLockDemoJava5Lock.java:19)
"T0":
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000007e46af828> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:186)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:834)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:867)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1197)
	at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:214)
	at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:290)
	at experiments.DeadLockDemoJava5Lock$1.run(DeadLockDemoJava5Lock.java:19)

Found 1 deadlock.
 *
 */
public class DeadLockDemoJava5Lock {

	public static void main(String[] args) {
		final Lock[] locks = { new ReentrantLock(), new ReentrantLock() };
		for (int i = 0; i < 2; i++) {
			final int j = i;
			new Thread("T" + i) {
				@Override
				public void run() {
					locks[j % 2].lock();
					System.out.println("Thread " + getName()
							+ " got the first lock");
					sleep1(1000);
					locks[(j + 1) % 2].lock();
					try {
						System.out.println("Thread " + getName()
								+ " got both the locks");
					} finally {
						locks[(j + 1) % 2].unlock();
						locks[j % 2].unlock();
					}
				}
			}.start();
		}
	}
	
	private static void sleep1(int val) {
		try {
			Thread.sleep(val);
		} catch (InterruptedException e) {
		}
	}

}
