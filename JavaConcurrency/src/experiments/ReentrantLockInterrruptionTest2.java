package experiments;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInterrruptionTest2 {
	
	public static void main(String[] args) throws InterruptedException {
		final ReentrantLock lock = new ReentrantLock();
		Thread t = new Thread(){
			@Override
			public void run() {
				lock.lock();
				System.out.println("T holds lock");
				try{
					while(true){
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					System.out.println("T interreupted");
				}finally{
					lock.unlock();
				}
			}
		};
		
		Thread t1 = new Thread(){
			@Override
			public void run() {
				lock.lock();
				System.out.println("T1 holds lock");
				try{
					while(true){
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					System.out.println("T1 interreupted");
				}finally{
					lock.unlock();
				}
			}
		};
		t.start();
		t1.start();
		Thread.sleep(1000);
		System.out.println("Interrupt t");
		t.interrupt();
		System.out.println("Interrupt t1");
		t1.interrupt();
	}

}
