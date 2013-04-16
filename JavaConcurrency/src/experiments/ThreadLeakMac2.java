package experiments;

/**
 * Taken from http://jsr166-concurrency.10961.n7.nabble.com/Latency-in-starting-threads-td9435.html
 * Author Heinz Kabutz
 *
 */
public class ThreadLeakMac2 {

	public static void main(String[] args) throws InterruptedException {
		long threads = 0;
		long max = 0;
		while (true) {
			long time = System.currentTimeMillis();
			Thread thread = new Thread();
			thread.start(); // should finish almost immediately
			time = System.currentTimeMillis() - time;
			thread.join(); // short delay, hopefully
			threads++;
			if (time > max) {
				max = time;
				System.out.println("time = " + time + ", threads = " + threads);
			}
		}
	}

}
