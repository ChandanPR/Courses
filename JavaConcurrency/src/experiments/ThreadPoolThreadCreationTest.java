package experiments;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolThreadCreationTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ThreadPoolExecutor service = (ThreadPoolExecutor)Executors.newFixedThreadPool(10, new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				System.out.println("Creating Thread");
				return new Thread(r);
			}
		});
		for(int i=0; i<20; i++){
			Future<Integer> f = service.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					System.out.println(Thread.currentThread().getName()+":"+service.getPoolSize()+":"+service.getActiveCount());
					Thread.sleep(1000);
					return 1;
				}
			});
			f.get();
		}
		service.shutdown();
	}

}
