package buildingblocks;

import java.util.concurrent.Semaphore;

public class SemaPhoreTests {
	
	public static void main(String[] args) throws InterruptedException {
		//NON FAIR - NO FIFO
		StringBuffer in = new StringBuffer();
		testSemaphore(false,in);
		Thread.sleep(1000);
		System.out.println();
		System.out.println(in);
		System.out.println("______________________");
		//NON FAIR - FIFO
		in = new StringBuffer();
		testSemaphore(true,in);
		Thread.sleep(1000);
		System.out.println();
		System.out.println(in);
	}

	private static void testSemaphore(boolean fair,final StringBuffer in) {
		final Semaphore semaphore = new Semaphore(5,fair);
		for(int i=0; i<10; i++){
			final int j = i;
			new Thread(){
				public void run() {
					try {
						in.append(j+",");
						semaphore.acquire();
						System.out.print(j+",");
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				};
			}.start();
		}
	}

}
