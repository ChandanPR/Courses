package gc;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class MemoryDetails {

	public static void main(String[] args) {
		MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
		System.out.println(bean.getHeapMemoryUsage());
		System.out.println(bean.getNonHeapMemoryUsage());
		System.out.println(bean.getObjectName());
	}

}
