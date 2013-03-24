package computations;

public class MainRunner {
	private static final int MAX_COUNT = 100000000;
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Parts\t\tCount\t\tTime");
		checkPerformance(new ConcurrentPrimeCounter(64),64);
		checkPerformance(new ConcurrentPrimeCounter(32),32);
		checkPerformance(new ConcurrentPrimeCounter(16),16);
		checkPerformance(new ConcurrentPrimeCounter(8),8);
		checkPerformance(new ConcurrentPrimeCounter(4),4);
		checkPerformance(new ConcurrentPrimeCounter(2),2);
		checkPerformance(new ConcurrentPrimeCounter(1),1);
		checkPerformance(new SequentialPrimeCounter(),1);
	}
	
	static void checkPerformance(AbstractPrimeCounter counter,int parts){
		long time = System.nanoTime();
		int count = counter.computeAllPrimes(MAX_COUNT);
		System.out.println(parts+"\t\t"+count+"\t\t"+ ((System.nanoTime() - time) / 1.0e9));
	}
}
