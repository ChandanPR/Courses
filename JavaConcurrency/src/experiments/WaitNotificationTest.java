package experiments;

import java.util.concurrent.TimeUnit;

public class WaitNotificationTest {

	public static void main(String[] args) {
		final Object object = new Object();

		for (int i = 0; i < 2; i++)
			new Thread("Waiting Thread "+i) {
				public void run() {
					synchronized (object) {
						while (true) {
							System.out.println(getName() + " Going to wait");
							sleep1(1);
							try {
								object.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println(getName() + " came out of wait");
							sleep1(5);
							System.out.println(getName() + " sleep done");
						}
					}
				}

			}.start();

		new Thread("Notifying Thread") {
			public void run() {
				sleep1(1);
				while (true) {
					synchronized (object) {
						object.notifyAll();
						sleep1(5);
						System.out.println(getName() + " sleep done");
					}
				}
			}

		}.start();
	}

	private static void sleep1(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

}
