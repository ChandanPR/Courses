package gc;

public class DeoptimizationDemo {
	private static final int COUNT = Integer.MAX_VALUE;

	public static void main(String[] args) {
		A a = new A();
		for (int i = 0; i < COUNT; i++) {
			a.testLoop();
		}
		System.out.println("SECOND LOOP");
//		a = new B();
		for (int i = 0; i < COUNT; i++) {
			a.testLoop();
		}
	}

	static class A {
		public int testLoop() {
			int counter = 0;
			for (int i = 0; i < COUNT; i++) {
				counter++;
			}
			return counter;
		}
	}

	static class B extends A {
		public int testLoop() {
			int counter = 0;
			for (int i = 0; i < COUNT; i++) {
				for (int j = 0; j < COUNT; j++) {
					counter++;
				}
			}
			return counter;
		}
	}

}
