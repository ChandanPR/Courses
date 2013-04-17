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
		long constructionMax = 0;
		while (true) {
			long time = System.currentTimeMillis();
			Thread thread = new Thread();
			time = System.currentTimeMillis() - time;
			threads++;
			
			if (time > constructionMax || time > 1000) {
				constructionMax = time;
				System.out.println("Construction Time = " + time + ", threads = " + threads);
			}
			
			time = System.currentTimeMillis();
			thread.start(); // should finish almost immediately
			time = System.currentTimeMillis() - time;
			thread.join(); // short delay, hopefully
			if (time > max) {
				max = time;
				System.out.println("time = " + time + ", threads = " + threads);
			}
		}
	}

}
