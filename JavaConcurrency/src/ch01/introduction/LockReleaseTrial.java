package ch01.introduction;

public class LockReleaseTrial {

	public synchronized void op1() throws InterruptedException {
		while (true) {
			System.out.print("OP1OP1OP1OP1OP1OP1OP1OP1");
			System.out.println();
			// Thread.sleep(1000);
		}
	}

	public void op2() {
		while (true) {
			for (int i = 0; i < 100; i++)
				System.out.println("OP2OP2OP2OP2OP2OP2OP2OP2OP2OP2OP2OP2OP2OP2OP2");
			synchronized (this) {
				System.out.println("OP 2 lock obtained");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final LockReleaseTrial lrt = new LockReleaseTrial();
		new Thread() {
			public void run() {
				try {
					lrt.op1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		Thread.sleep(500);
		for (int i = 0; i < 50; i++)
			new Thread() {
				public void run() {
					lrt.op2();
				};
			}.start();
	}
}