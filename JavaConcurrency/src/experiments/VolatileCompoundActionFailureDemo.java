package experiments;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class VolatileCompoundActionFailureDemo {

	private volatile int uniqueIndex;

	public int getUniqueIndex() {
		return uniqueIndex++;
	}

	public static void main(String[] args) {
		new VolatileCompoundActionFailureDemo().test();
	}

	private void test() {
		final Set<Integer> set = Collections
				.synchronizedSet(new HashSet<Integer>());
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					while (true) {
						int k = getUniqueIndex();
						if (!set.add(k)) {
							System.err.println("DUPLICTE ELEMENT : "+k);
							System.exit(-1);
						}
					}
				};
			}.start();
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println("SHUTTING DOWN");
			}
		});
	}

}
