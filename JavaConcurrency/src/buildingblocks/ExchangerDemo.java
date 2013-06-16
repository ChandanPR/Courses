package buildingblocks;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
	public static void main(String[] args) {

		final Exchanger<String> exchanger = new Exchanger<>();
		for (int i = 1; i <= 2; i++) {
			final int j = i;
			new Thread("T" + i) {
				public void run() {
					while (true) {
						try {
							if (j % 2 == 0) {
								sleep1(5000);
							}
							System.out
									.print("exchnage in " + getName() + " : ");
							System.out.println(exchanger.exchange(getName()));
							sleep1(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
			}.start();
		}

	}

	private static void sleep1(int val) {
		try {
			Thread.sleep(val);
		} catch (InterruptedException e) {
		}
	}
}
