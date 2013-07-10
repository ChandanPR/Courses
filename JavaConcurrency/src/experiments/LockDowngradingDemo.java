package experiments;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDowngradingDemo {
	
	public static void main(String[] args) {
		final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
		
		new Thread("WRITER to Reader"){
			public void run() {
				rwLock.writeLock().lock();
				System.out.println(getName()+" has Write Lock");
				rwLock.readLock().lock();
				System.out.println(getName()+" has Read Lock");
				try{
					
				}finally{
					rwLock.writeLock().unlock();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					rwLock.readLock().unlock();
				}
			};
		}.start();
		
		new Thread("WRITER"){
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(getName()+" trying for Write Lock");
				rwLock.writeLock().lock();
				System.out.println(getName()+" has Write Lock");
				try{
					
				}finally{
					rwLock.writeLock().unlock();
				}
			};
		}.start();
		
	}

}
