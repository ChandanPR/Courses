package jmx.standard;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class MXBeanMain {

	public static void main(String[] args) throws Exception{
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		ObjectName name = new ObjectName("jmx.standard:type=TicketBookerImpl");

		TicketBookerImpl mbean = new TicketBookerImpl();

		mbs.registerMBean(mbean, name);

		System.out.println("Waiting forever...");
		Thread.sleep(Long.MAX_VALUE);
	}
}
