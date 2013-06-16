package buildingblocks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
	
	public static void main(String[] args) {
		final Lock lock = new ReentrantLock();
		final Condition condition = lock.newCondition();
		
		new Thread(){
			public void run() {
				lock.lock();
				System.out.println("T1 : lock obtained");
				ConditionTest.sleep(1000);
				try{
					System.out.println("T1 : going to wait on condition");
					condition.await();
					System.out.println("T1 : condition wait ended");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
					System.out.println("T1 : lock released");
				}
			};
		}.start();
		
		new Thread(){
			public void run() {
				ConditionTest.sleep(10);
				System.out.println("T2 : trying to get lock");
				lock.lock();
				System.out.println("T2 : lock obtained");
				try{
					condition.signal();
					System.out.println("T2 : signal done");
				}finally{
					lock.unlock();
					System.out.println("T2 : lock released");
				}
			};
		}.start();
	}
	
	private static void sleep(int val) {
		try {
			Thread.sleep(val);
		} catch (InterruptedException e) {
		}
	}

}
