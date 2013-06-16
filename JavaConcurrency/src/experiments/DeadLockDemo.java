package experiments;

/**
 * 
 * 
  
 Three options to detect deadlock
 1. ThreadMXBean
 2. kill -3 or CTRL+BREAK
 3. jstack processid
 
 * 
 * 
 Deadlock using intrinsic monitors.
 The threads involved in deadlock are in blocking state.
 
 Chandans-MacBook-Pro:bin chandanpr$ jstack 1409
2013-06-16 14:48:16
Full thread dump Java HotSpot(TM) 64-Bit Server VM (23.6-b03 mixed mode):

"Attach Listener" daemon prio=5 tid=0x00007fade3801000 nid=0x4107 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"DestroyJavaVM" prio=5 tid=0x00007fade2000800 nid=0x90b waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Thread-1" prio=5 tid=0x00007fade20cd800 nid=0x4c03 waiting for monitor entry [0x000000010d554000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at experiments.DeadLockDemo$2.run(DeadLockDemo.java:24)
	- waiting to lock <0x00000007e460ea98> (a java.lang.Object)
	- locked <0x00000007e460eaa8> (a java.lang.Object)

"Thread-0" prio=5 tid=0x00007fade20cc800 nid=0x4b03 waiting for monitor entry [0x000000010d451000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at experiments.DeadLockDemo$1.run(DeadLockDemo.java:12)
	- waiting to lock <0x00000007e460eaa8> (a java.lang.Object)
	- locked <0x00000007e460ea98> (a java.lang.Object)

"Service Thread" daemon prio=5 tid=0x00007fade209b000 nid=0x4903 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread1" daemon prio=5 tid=0x00007fade209a000 nid=0x4803 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" daemon prio=5 tid=0x00007fade2098800 nid=0x4703 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" daemon prio=5 tid=0x00007fade206d000 nid=0x4603 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" daemon prio=5 tid=0x00007fade2055000 nid=0x3703 in Object.wait() [0x000000010cbc2000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000007e4565798> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:135)
	- locked <0x00000007e4565798> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:151)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:177)

"Reference Handler" daemon prio=5 tid=0x00007fade2054800 nid=0x3603 in Object.wait() [0x000000010cabf000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000007e4565320> (a java.lang.ref.Reference$Lock)
	at java.lang.Object.wait(Object.java:503)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:133)
	- locked <0x00000007e4565320> (a java.lang.ref.Reference$Lock)

"VM Thread" prio=5 tid=0x00007fade2052000 nid=0x3503 runnable 

"GC task thread#0 (ParallelGC)" prio=5 tid=0x00007fade200e800 nid=0x3103 runnable 

"GC task thread#1 (ParallelGC)" prio=5 tid=0x00007fade200f000 nid=0x3203 runnable 

"GC task thread#2 (ParallelGC)" prio=5 tid=0x00007fade200f800 nid=0x3303 runnable 

"GC task thread#3 (ParallelGC)" prio=5 tid=0x00007fade2010000 nid=0x3403 runnable 

"VM Periodic Task Thread" prio=5 tid=0x00007fade2066800 nid=0x4a03 waiting on condition 

JNI global references: 113


Found one Java-level deadlock:
=============================
"Thread-1":
  waiting to lock monitor 0x00007fade183c960 (object 0x00000007e460ea98, a java.lang.Object),
  which is held by "Thread-0"
"Thread-0":
  waiting to lock monitor 0x00007fade183b5b0 (object 0x00000007e460eaa8, a java.lang.Object),
  which is held by "Thread-1"

Java stack information for the threads listed above:
===================================================
"Thread-1":
	at experiments.DeadLockDemo$2.run(DeadLockDemo.java:24)
	- waiting to lock <0x00000007e460ea98> (a java.lang.Object)
	- locked <0x00000007e460eaa8> (a java.lang.Object)
"Thread-0":
	at experiments.DeadLockDemo$1.run(DeadLockDemo.java:12)
	- waiting to lock <0x00000007e460eaa8> (a java.lang.Object)
	- locked <0x00000007e460ea98> (a java.lang.Object)

Found 1 deadlock.
 *
 */
public class DeadLockDemo {
	public static void main(String[] args) {
		final Object[] locks = {new Object(),new Object()};
		
		new Thread(){
			public void run() {
				synchronized (locks[0]) {
					sleep1(1000);
					synchronized (locks[1]) {
						System.out.println("0-----1");
					}
					
				}
			};
		}.start();
		
		new Thread(){
			public void run() {
				synchronized (locks[1]) {
					sleep1(1000);
					synchronized (locks[0]) {
						System.out.println("1-----0");
					}
					
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

}
