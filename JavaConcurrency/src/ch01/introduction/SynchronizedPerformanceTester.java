package ch01.introduction;

public class SynchronizedPerformanceTester {
	
	private static final long REPEAT_COUNT = 1000000;
	
	private synchronized void testSyncMethod(){
		
	}
	
	private void testSyncBlock(){
		synchronized (this) {
			
		}
	}
	
	public void testSyncMethodPerformance(){
		long time = System.nanoTime();
		for(long i=0; i<REPEAT_COUNT; i++){
			testSyncMethod();
		}
		System.out.println("Time taken for sync method : "+((System.nanoTime()-time)/1.0e9));
	}
	
	public void testSyncBlockPerformance(){
		long time = System.nanoTime();
		for(long i=0; i<REPEAT_COUNT; i++){
			testSyncBlock();
		}
		System.out.println("Time taken for sync block : "+((System.nanoTime()-time)/1.0e9));
	}
	
	public static void main(String[] args) {
		SynchronizedPerformanceTester synchronizedPerformanceTester = new SynchronizedPerformanceTester();
		synchronizedPerformanceTester.testSyncBlockPerformance();
		synchronizedPerformanceTester.testSyncMethodPerformance();
	}

}
