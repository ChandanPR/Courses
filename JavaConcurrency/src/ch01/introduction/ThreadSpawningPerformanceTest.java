package ch01.introduction;

public class ThreadSpawningPerformanceTest {
	static void test(final int threadCount) {
		Thread[] tt = new Thread[threadCount];
		long t0 = System.nanoTime();
		for (int i = 0; i < tt.length; i++) {
			tt[i] = new Thread() {
				public void run() {
				}
			};
		}
		System.out.print(threadCount+"\t\t"+(System.nanoTime() - t0) * 1E-9+ " s.\t\t");
		t0 = System.nanoTime();
		for (int i = 0; i < tt.length; i++) {
			tt[i].start();
		}
		System.out.println((System.nanoTime() - t0) * 1E-9+ "s.");
	}

	public static void main(String[] kr) throws InterruptedException {
		System.out.println("ThreadCount\t\tCreationTime\t\tStartTime");
		int[] threadCount = new int[] { 1, 2, 10, 100, 1000, 10000, 100000 };
		int trialCount = 2;
		for (int j = 0; j < trialCount; j++) {
			for (int i = 0; i < threadCount.length; i++) {
				test(threadCount[i]);
			}
		}
	}
}
