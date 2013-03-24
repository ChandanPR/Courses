package computations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentPrimeCounter extends AbstractPrimeCounter {

	private int parts;

	public ConcurrentPrimeCounter(){
		this(4);
	}
	
	public ConcurrentPrimeCounter(int parts){
		super("Concurrent Prime Counter");
		this.parts = parts;
	}
	
	
	@Override
	public int countPrimes(int high) {
		int poolSize = Runtime.getRuntime().availableProcessors();
		ExecutorService service = Executors.newFixedThreadPool(poolSize);
		int chunksPerPartition = high/parts;
		List<Callable<Integer>> tasks = new ArrayList<>();
		for(int i=0; i<parts; i++){
			final int min = i*chunksPerPartition+1;
			final int max = (i == parts-1)? high : min + chunksPerPartition-1;
			
//			System.out.println(min+","+max);
			tasks.add(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					return countPrimesInRange(min, max);
				}
			});
		}
		int count = 0;
		try {
			for(Future<Integer> result : service.invokeAll(tasks)){
				count += result.get();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}finally{
			service.shutdown();
		}
		return count;
	}

}
