package taskcancel;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureCancelDemo {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(1);
		final AtomicInteger integer = new AtomicInteger();
		Future<Integer> f = service.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				while (!Thread.currentThread().isInterrupted()) {
					if(integer.incrementAndGet() > 100000000)
						return integer.get();
				}
				return null;
			}
		});

		Integer integer2 = null;
		try {
			integer2 = f.get(100, TimeUnit.MILLISECONDS);
			System.out.println("Done :"+integer2);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
			throw e;
		} catch (ExecutionException e) {
			System.out.println("Execution Exception");
		} catch (TimeoutException e) {
			System.out.println("TimeOutException");
			f.cancel(true);
		}
		System.out.println("Done :"+integer2);
		service.shutdown();
	}

}
