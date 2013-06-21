package experiments;

public class ThreadSafeCloneTest {
	public static void main(String[] args) {
		final ThreadSafe safe = new ThreadSafe();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					int count = 0;
					while (count < 100) {
						safe.setName(Integer.toString(count));
						count++;
					}
				};
			}.start();
		}

		for (int i = 0; i < 10; i++)
			safe.clone();
	}
}

class ThreadSafe implements Cloneable {
	private String name = "";

	public synchronized void setName(String name) {
		this.name = name;
	}

	public synchronized String getName() {
		return name;
	}

	@Override
	public ThreadSafe clone() {
		//Synchronize clone other wise you will end up in error
		ThreadSafe unsafe = null;
		try {
			unsafe = (ThreadSafe) super.clone();
			if(!name.equals(unsafe.name)){
				System.err.println("ERROR");
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return unsafe;
	}
}
