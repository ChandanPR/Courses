package compilationflags;

import static java.lang.System.out;
public class CompilationFlagsDemo {

	public static final long WARMUP_ITERATIONS = 100L * 1000L;
	public static final long ITERATIONS = 500L * 1000L * 1000L;
	
	public static void main(String[] args) throws InterruptedException {
		long val = 0;
		Operation operation = new Operation();
		
		//WARM UP
		for(int i=0; i<100; i++){
			val = operation.increment(val, WARMUP_ITERATIONS);
			val = 0;
		}
		Thread.sleep(1000); //COOLING
		
		out.println("START MEASURING");
		long time = System.currentTimeMillis();
		val = operation.increment(val, ITERATIONS);
		out.println("Time : "+(System.currentTimeMillis() - time));
		out.println(val);
	}

}

class Operation {
	public long increment(long val, long count) {
		for (long i = 0; i < count; i++) {
			val++;
		}
		return val;
	}
}
