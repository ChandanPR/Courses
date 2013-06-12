package taskcancel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomizedThreadPoolExecutor extends ThreadPoolExecutor {

	private ArrayList<Runnable> runningTasks = new ArrayList<>();
	public CustomizedThreadPoolExecutor() {
		super(4, 4, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		runningTasks.add(r);
		super.beforeExecute(t, r);
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		runningTasks.remove(r);
		super.afterExecute(r, t);
	}

	private static class MyRunnable implements Runnable{
		private final int id;
		MyRunnable(int id){
			this.id = id;
		}
		public void run() {
			if(id > 5 && id <10){
				sleep(Integer.MAX_VALUE);
			}
			sleep(5);
		}
		
		@Override
		public String toString() {
			return Integer.toString(id);
		}
		
	}
	
	private static void sleep(int val) {
		try {
			Thread.sleep(val);
		} catch (InterruptedException e) {
		}
	}

	
	@Override
	public List<Runnable> shutdownNow() {
		List<Runnable> pending =  super.shutdownNow();
		System.out.println("Pending tasks : "+pending);
		System.out.println("Running Tasks : "+runningTasks);
		return runningTasks;
	}
	
	@Override
	protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
		return new MyFutureTask<T>(runnable, value);
	}
	
	private class MyFutureTask<T> extends FutureTask<T>{
		private final Runnable runnable;
		public MyFutureTask(Runnable runnable, T result) {
			super(runnable, result);
			this.runnable = runnable;
		}
		
		@Override
		public String toString() {
			return runnable.toString();
		}
	}
	
	
	public static void main(String[] args) {
		ExecutorService service = new CustomizedThreadPoolExecutor();
		for(int i=0; i<20; i++){
			service.submit(new MyRunnable(i));
		}
		sleep(500);
		service.shutdownNow();
	}

}
