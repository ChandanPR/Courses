package experiments;

public class LockNotificationTest {

	public static void main(String[] args) {

		final Object object = new Object();

		for (int i = 0; i < 10; i++)
			new Thread("Waiting Thread " + i) {
				public void run() {
					synchronized (object) {
						System.out.println(getName() + " Going to wait");
						try {
							object.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(getName() + " came out of wait");
						System.out.println(getName() + " sleep done");
					}
				}

			}.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread("Notifying Thread") {
			public void run() {
				synchronized (object) {
					for (int i = 0; i < 10; i++) {
						object.notify();
						System.out.println(getName() + " Notifying for " + i
								+ " time");
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}.start();

	}

}
