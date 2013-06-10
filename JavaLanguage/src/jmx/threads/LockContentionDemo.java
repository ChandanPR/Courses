package jmx.threads;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.CountDownLatch;

public class LockContentionDemo {
	static int n;
	static final CountDownLatch latch = new CountDownLatch(10);

	public static void main(String[] args) {

		final ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		System.out.println("(bean.isCurrentThreadCpuTimeSupported() : "
				+ bean.isCurrentThreadCpuTimeSupported());
		System.out.println("(bean.isObjectMonitorUsageSupported() : "
				+ bean.isObjectMonitorUsageSupported());
		System.out.println("(bean.isSynchronizerUsageSupported() : "
				+ bean.isSynchronizerUsageSupported());
		System.out.println("(bean.isThreadContentionMonitoringEnabled() : "
				+ bean.isThreadContentionMonitoringEnabled());
		System.out.println("(bean.isThreadContentionMonitoringSupported() : "
				+ bean.isThreadContentionMonitoringSupported());
		System.out.println("(bean.isThreadCpuTimeEnabled() : "
				+ bean.isThreadCpuTimeEnabled());
		System.out.println("(bean.isThreadCpuTimeSupported() : "
				+ bean.isThreadCpuTimeSupported());
		bean.setThreadContentionMonitoringEnabled(true);
		final ThreadInfoMap map = new ThreadInfoMap();

		new Thread() {
			public void run() {
				while (true) {
					ThreadInfo[] infos = bean.getThreadInfo(bean
							.getAllThreadIds());
					if (latch.getCount() <= 0) {
						System.out.println(map);
						System.exit(0);
					}
					for (ThreadInfo info : infos) {
						map.update(info);
					}
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}.start();
		startThreads();
	}

	private static void startThreads() {
		final Object lock = new Object();
		for (int i = 0; i < 10; i++) {
			new Thread("My Own Thread " + i) {
				public void run() {
					synchronized (lock) {
						for (int i = 0; i < 10; i++) {
							for (int j = 0; j < 100; j++) {
								for (int k = 0; k < 100; k++) {
									n += i;
								}
							}
							try {
								lock.wait(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					latch.countDown();
				};
			}.start();

		}
	}

}
