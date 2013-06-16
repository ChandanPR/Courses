package buildingblocks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	Runnable ac1 = new Runnable() {
		public void run() {
			System.out.println("AC1 completed");
		};
	};

	private CyclicBarrier b1 = new CyclicBarrier(2, ac1);
	
	private void testB1(){
		//NOT THE RIGHT WAY BE CAREFUL
		int c = 1;
		b1Test(c);
		b1Test(c = c+2);
		b1Test(c = c+2);
	}

	private void b1Test(int c) {
		for(int i=1; i<=2; i++){
			new Thread("B1T"+i+"::::"+c){
				public void run() {
					System.out.println(getName()+" Started");
					sleep1((int)(Math.random()*1000));
					try {
						b1.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
					System.out.println(getName()+" DONE");
					
				};
			}.start();
		}
	}
	
	public static void main(String[] args) {
		new CyclicBarrierTest().testB1();
	}
	
	private static void sleep1(int val) {
		try {
			Thread.sleep(val);
		} catch (InterruptedException e) {
		}
	}

}
