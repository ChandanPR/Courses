package jmx.connectors;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.management.Attribute;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JMXDemoClient {

	public static void main(String[] args) {
		try {
			
			//CREATES THE rmi CONNECTOR CLIENT URL AND CONNECTS TO IT.
			log("Create an RMI connector client and connect it to the RMI connector server");
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/server");
			JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

			//GETS THE SERVER CONNECTION
			log("Get an MBeanServerConnection");
			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
			waitForEnterPressed();

			//GET THE DOMAINS IN WHICH ANY MBEAN IS CURRENTLY REGISTERED
			log("Domains:");
			String domains[] = mbsc.getDomains();
			for (int i = 0; i < domains.length; i++) {
				log("Domain[" + i + "] = " + domains[i]);
			}
			waitForEnterPressed();

			//GET DEFAULT DOMAIN
			String domain = mbsc.getDefaultDomain();
			log("Default Domain :"+domain);
			
			//CREATE NumberCache MEBAN OBJECT
			ObjectName mbeanObjectName = new ObjectName(domain+ ":type=jmx.connectors.NumberCache,name=2");
			log("Create SimpleStandard MBean...");
			mbsc.createMBean("jmx.connectors.NumberCache", mbeanObjectName,null, null);
			waitForEnterPressed();

			//PRINTS ALL REGISTERED MBEAN DETAILS
			log("MBean count = " + mbsc.getMBeanCount());
			log("Query MBeanServer MBeans:");
			Set names = mbsc.queryNames(null, null);
			for (Iterator i = names.iterator(); i.hasNext();) {
				log("ObjectName = " + i.next());
			}
			waitForEnterPressed();

			// -------------------------------
			// Manage the NumberCache MBean
			// -------------------------------
			log("Perform GET operations on NumberCache");
			log("cacheUpdateCount = " + mbsc.getAttribute(mbeanObjectName, "CacheUpdateCount"));
			log("cacheHitCount = " + mbsc.getAttribute(mbeanObjectName, "CacheHitCount"));
			log("Perform SET operations on NumberCache");
			mbsc.setAttribute(mbeanObjectName, new Attribute("CacheHitCount", 10));
			mbsc.setAttribute(mbeanObjectName, new Attribute("CacheUpdateCount", 100));
			log("Perform GET operations on NumberCache after set");
			log("cacheUpdateCount = " + mbsc.getAttribute(mbeanObjectName, "CacheUpdateCount"));
			log("cacheHitCount = " + mbsc.getAttribute(mbeanObjectName, "CacheHitCount"));

			//INTERACTING THROUGH PROXY INSTEAD OF DIRECT SERVER CONNECTION
			NumberCacheMBean proxy = JMX.newMBeanProxy(mbsc, mbeanObjectName,
					NumberCacheMBean.class, true);
			log("nbCacheUpdates = " + proxy.getCacheUpdateCount());
			log("nbCacheHits = " + proxy.getCacheHitCount());
			waitForEnterPressed();
			log("Close the connection to the server");
			jmxc.close();
			log("Bye! Bye!");
		} catch (Exception e) {
			logError(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void log(String msg) {
		System.out.println(msg);
	}
	
	private static void logError(String msg) {
		System.err.println(msg);
	}

	private static void waitForEnterPressed() {
		try {
			log("\nPress <Enter> to continue...");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
