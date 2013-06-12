package taskcancel;

import java.lang.Thread.UncaughtExceptionHandler;
/**
 * ORDER:
 * setUncaughtExceptionHandler
 * ThreadGroup - overridded method
 * setDefaultUncaughtExceptionHandler
 * @author chandanpr
 */
public class ThreadExceptionHandler {
	
	
	public static void main(String[] args) {
		Thread t1 = new Thread(){
			public void run() {
				setUncaughtExceptionHandler(new MyExceptionHandler1());
				setDefaultUncaughtExceptionHandler(new MyExceptionHandler2());
				throw new RuntimeException();
			}
		};
		t1.start();
		
		Thread t2 = new Thread(new MyThreadGroup(),"Test"){
			public void run() {
				setUncaughtExceptionHandler(new MyExceptionHandler1());
				setDefaultUncaughtExceptionHandler(new MyExceptionHandler2());
				throw new RuntimeException();
			}
		};
		t2.start();
	}
	
	
	private static class MyExceptionHandler1 implements UncaughtExceptionHandler{
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("MyExceptionHandler1");
		}
	}
	
	private static class MyExceptionHandler2 implements UncaughtExceptionHandler{
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("MyExceptionHandler2");
		}
	}
	
	private static class MyThreadGroup extends ThreadGroup{
		public MyThreadGroup() {
			super("Test");
		}

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("MyThreadGroup");
			super.uncaughtException(t, e);
		}
	}

}
