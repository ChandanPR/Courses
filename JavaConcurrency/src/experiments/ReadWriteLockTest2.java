package experiments;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest2 {
	
	public static void main(String[] args) {
		
		final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
		
		new Thread("READER 1"){
			public void run() {
				rwLock.readLock().lock();
				System.out.println(getName()+" obtained read lock");
				try{
					sleep1(5000);
				}finally{
					rwLock.readLock().unlock();
					System.out.println(getName()+" released read lock");
				}
			};
		}.start();
		
		new Thread("READER 2"){
			public void run() {
				rwLock.readLock().lock();
				System.out.println(getName()+" obtained read lock");
				try{
					sleep1(5000);
				}finally{
					rwLock.readLock().unlock();
					System.out.println(getName()+" released read lock");
				}
			};
		}.start();
		
		new Thread("WRITER"){
			public void run() {
				sleep1(100);
				rwLock.writeLock().lock();
				System.out.println(getName()+" obtained write lock");
				try{
					sleep1(5000);
				}finally{
					rwLock.writeLock().unlock();
					System.out.println(getName()+" released write lock");
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
