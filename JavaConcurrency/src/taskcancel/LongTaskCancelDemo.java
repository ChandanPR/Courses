package taskcancel;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LongTaskCancelDemo extends Thread {
	private volatile int count;
	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(8);

	@Override
	public void run() {
		try {
			while (!isInterrupted()) {
				queue.put(get());
			}
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted in run()");
		}
		System.out.println(queue);

	}
	
	public void cancel(){
		interrupt();
	}

	private int get() {
		if (count >= 5) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted in get()");
				interrupt();
			}
		}
		return ++count;
	}

	public static void main(String[] args) throws InterruptedException {
		LongTaskCancelDemo demo = new LongTaskCancelDemo();
		demo.start();
		Thread.sleep(10);
		demo.cancel();
	}

}
