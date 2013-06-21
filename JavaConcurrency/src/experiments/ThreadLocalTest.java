package experiments;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {

	private static final ThreadLocal<Integer> COUNT = new ThreadLocal<Integer>() {

		AtomicInteger integer = new AtomicInteger();

		public Integer initialValue() {
			return integer.incrementAndGet();
		};

	};
	
	
	public static void main(String[] args) {
		new Thread("REMOVER"){
			public void run() {
				while(true){
					int oldCount = COUNT.get();
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					COUNT.remove();
					System.out.println(getName()+":"+oldCount+":"+COUNT.get());
					
				}
			};
		}.start();
		
		new Thread("GETTER"){
			public void run() {
				while(true){
					int oldCount = COUNT.get();
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(getName()+":"+oldCount+":"+COUNT.get());
				}
			};
		}.start();
	}

}
