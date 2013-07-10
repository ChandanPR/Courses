package experiments;

/**
 * On notify the longest waiting thread will get the notification
 * But it all depends on whether the thread will be able to acquire the lock for execution.
 * @author chandanr
 *
 */
public class SynchronizedWaitNotifyOrderTest {
	
	public static void main(String[] args) throws InterruptedException {
		final MyLock myLock = new MyLock();
		for (int i = 0; i < 10; i++) {
			new Thread("Thread " + i) {
				public void run() {
					myLock.waitHere();
					System.out.println(getName() + " Completed");
				};
			}.start();
		}
		Thread.sleep(2000);
		for (int i = 0; i < 10; i++) {
//			Thread.sleep(1000);
			myLock.notifyHere();
		}
	}
	
	static class MyLock{
		synchronized void waitHere(){
			System.out.println(Thread.currentThread().getName()+" going to wait");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		synchronized void notifyHere(){
			System.out.println(Thread.currentThread().getName()+" going to notify");
			notify();
		}
	}

}
