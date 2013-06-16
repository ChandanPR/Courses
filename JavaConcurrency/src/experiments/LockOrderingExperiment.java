package experiments;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * With Java 5 locks, it is the responsibility of the 
 * programmer to release the locks in the LIFO manner.
 * This problem was never there with intrinsic locks.
 * @author chandanpr
 *
 */
public class LockOrderingExperiment {
	
	
	public static void main(String[] args) {
		final Lock[] locks = { new ReentrantLock(), new ReentrantLock() };
		
		new Thread(){
			public void run() {
				locks[0].lock();
				locks[1].lock();
				try{
					System.out.println("BOTH LOCKS ARE HELD");
				}finally{
					locks[0].unlock();
					locks[1].unlock();
				}
			};
		}.start();
	}

}
