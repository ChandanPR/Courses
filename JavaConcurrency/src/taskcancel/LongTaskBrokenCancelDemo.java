package taskcancel;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LongTaskBrokenCancelDemo extends Thread {
	private volatile int count;
	private volatile boolean cancel;
	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

	@Override
	public void run() {
		try {
			while (!cancel) {
				queue.put(get());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(queue);

	}

	private int get() {
		if (count >= 5) {
			try {
				Thread.sleep(1000);
//				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return ++count;
	}
	
	public static void main(String[] args) throws InterruptedException {
		LongTaskBrokenCancelDemo demo = new LongTaskBrokenCancelDemo();
		demo.start();
		Thread.sleep(1);
//		Thread.sleep(10);
		demo.cancel = true;
	}

}
