package experiments;

import java.util.concurrent.atomic.AtomicLong;

public class MyLong extends AtomicLong{
	private long count = 0;

	public synchronized long getCount() {
		return count;
	}
	
	public long doIncrement() {
		for (;;) {
			count();
			long current = get();
			long next = current + 1;
			if (compareAndSet(current, next))
				return next;
		}
	}

	private synchronized void count() {
		count++;
	}

	public long doDecrement() {
		for (;;) {
			count();
			long current = get();
			long next = current - 1;
			if (compareAndSet(current, next))
				return next;
		}
	}

}
