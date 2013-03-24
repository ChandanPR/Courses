package ch01.introduction;

import java.util.concurrent.CopyOnWriteArraySet;

public class CounterTest {

	public static void main(String[] args) {
		final CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
		final Counter counter = new Counter();
		for (int i = 0; i < 100; i++) {
			new Thread() {
				public void run() {
					while (counter.getCount() < 1000) {
						if (!set.add(counter.increment())) {
							throw new IllegalStateException(
									"Duplicate entry found");
						}
					}
				};
			}.start();
		}
	}

}
