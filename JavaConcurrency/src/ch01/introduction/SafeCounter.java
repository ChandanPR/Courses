package ch01.introduction;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SafeCounter extends Counter {
	
	//count @GuardedBy("this")
	
	public synchronized int increment() {
		return super.increment();
	}
	
	public synchronized int getCount() {
		return super.getCount();
	}

}
