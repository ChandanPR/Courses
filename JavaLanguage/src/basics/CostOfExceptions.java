package basics;

public class CostOfExceptions {

	public static void main(String[] args) {
		int count = 1000;
		long time = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				new Object();
			}
		}
		System.out.println("Toal Time for Object creation: " + (System.currentTimeMillis() - time));
		
		time = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				new MyOwnException();
			}
		}
		System.out.println("Toal Time for MyOwnException creation: " + (System.currentTimeMillis() - time));
		
		time = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				new Exception();
			}
		}
		System.out.println("Toal Time for Exception creation: " + (System.currentTimeMillis() - time));
	}

	static class MyOwnException extends RuntimeException {
		@Override
		//DON'T FILL THE STACK TRACE
		public synchronized Throwable fillInStackTrace() {
			return this;
		}
	}

}
