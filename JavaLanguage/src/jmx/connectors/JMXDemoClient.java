package jmx.connectors;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.InvalidAttributeValueException;
import javax.management.JMX;
import javax.management.ListenerNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
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
			
			performSimpleNumberCacheOperations(mbsc, domain);
			waitForEnterPressed();
			
			performDynamicNumberCacheOperations(mbsc, domain);
			waitForEnterPressed();
			log("Close the connection to the server");
			jmxc.close();
			log("Bye! Bye!");
		} catch (Exception e) {
			logError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	private static void performDynamicNumberCacheOperations(
			MBeanServerConnection mbsc, String domain)
			throws MalformedObjectNameException, ReflectionException,
			InstanceAlreadyExistsException, MBeanRegistrationException,
			MBeanException, NotCompliantMBeanException, IOException,
			AttributeNotFoundException, InstanceNotFoundException,
			InvalidAttributeValueException, ListenerNotFoundException {
		//CREATE NumberCache MEBAN OBJECT
		ObjectName mbeanObjectName = new ObjectName(domain+ ":type=jmx.connectors.DynamicNumberCache,name=1");
		log("Create SimpleStandard MBean...");
		mbsc.createMBean("jmx.connectors.DynamicNumberCache", mbeanObjectName,null, null);
		waitForEnterPressed();

		//PRINTS ALL REGISTERED MBEAN DETAILS
		log("MBean count = " + mbsc.getMBeanCount());
		log("Query MBeanServer MBeans:");
		Set<ObjectName> names = mbsc.queryNames(null, null);
		for (Iterator<ObjectName> i = names.iterator(); i.hasNext();) {
			log("ObjectName = " + i.next());
		}
		waitForEnterPressed();

		// -------------------------------
		// Manage the NumberCache MBean
		// -------------------------------
		log("Perform GET operations on DynamicNumberCache");
		log("CacheHits = " + mbsc.getAttribute(mbeanObjectName, "CacheHits"));
		log("CacheUpdates = " + mbsc.getAttribute(mbeanObjectName, "CacheUpdates"));
		waitForEnterPressed();
		
		log("Perform getInteger operations on DynamicNumberCache");
		for(int i=1;i<11; i++)
			mbsc.invoke(mbeanObjectName, "getInteger", new Object[]{i}, null);
		waitForEnterPressed();
		
		log("Perform GET operations on DynamicNumberCache after getInteger");
		log("CacheHits = " + mbsc.getAttribute(mbeanObjectName, "CacheHits"));
		log("CacheUpdates = " + mbsc.getAttribute(mbeanObjectName, "CacheUpdates"));
		waitForEnterPressed();
		
		
		log("Perform getInteger operations on NumberCache");
		for(int i=1;i<11; i++)
			mbsc.invoke(mbeanObjectName, "getInteger", new Object[]{i}, null);
		log("Perform GET operations on NumberCache after getInteger");
		log("CacheHits = " + mbsc.getAttribute(mbeanObjectName, "CacheHits"));
		log("CacheUpdates = " + mbsc.getAttribute(mbeanObjectName, "CacheUpdates"));
		waitForEnterPressed();

		ClientListener listener = new ClientListener();
		mbsc.addNotificationListener(mbeanObjectName, listener, null, null);
		mbsc.invoke(mbeanObjectName, "getInteger", new Object[]{100}, null);
		sleep(2000);
		mbsc.invoke(mbeanObjectName, "getInteger", new Object[]{100}, null);
		sleep(2000);
		mbsc.invoke(mbeanObjectName, "reset", null, null);
		sleep(2000);
		mbsc.removeNotificationListener(mbeanObjectName, listener);
	}

	private static void performSimpleNumberCacheOperations(
			MBeanServerConnection mbsc, String domain)
			throws MalformedObjectNameException, ReflectionException,
			InstanceAlreadyExistsException, MBeanRegistrationException,
			MBeanException, NotCompliantMBeanException, IOException,
			AttributeNotFoundException, InstanceNotFoundException,
			InvalidAttributeValueException, ListenerNotFoundException {
		//CREATE NumberCache MEBAN OBJECT
		ObjectName mbeanObjectName = new ObjectName(domain+ ":type=jmx.connectors.NumberCache,name=2");
		log("Create SimpleStandard MBean...");
		mbsc.createMBean("jmx.connectors.NumberCache", mbeanObjectName,null, null);
		waitForEnterPressed();

		//PRINTS ALL REGISTERED MBEAN DETAILS
		log("MBean count = " + mbsc.getMBeanCount());
		log("Query MBeanServer MBeans:");
		Set<ObjectName> names = mbsc.queryNames(null, null);
		for (Iterator<ObjectName> i = names.iterator(); i.hasNext();) {
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
		ObjectName serverMbeanObject = new ObjectName(domain+ ":type=jmx.connectors.NumberCache,name=1");
		ClientListener listener = new ClientListener();
		NumberCacheMBean proxy = JMX.newMBeanProxy(mbsc, serverMbeanObject,
				NumberCacheMBean.class, true);
		log("Perform GET operations on Server NumberCache");
		log("nbCacheUpdates = " + proxy.getCacheUpdateCount());
		log("nbCacheHits = " + proxy.getCacheHitCount());
		log("Perform SET operations on Server NumberCache");
		proxy.setCacheHitCount(58);
		proxy.setCacheUpdateCount(49);
		log("Perform GET operations on Server NumberCache");
		log("nbCacheUpdates = " + proxy.getCacheUpdateCount());
		log("nbCacheHits = " + proxy.getCacheHitCount());
		mbsc.addNotificationListener(serverMbeanObject, listener, null, null);
		log("Perform reset operations on Server NumberCache");
//			mbsc.invoke(serverMbeanObject, "reset", null, null);
		proxy.reset();
		log("Perform GET operations on Server NumberCache");
		log("nbCacheUpdates = " + proxy.getCacheUpdateCount());
		log("nbCacheHits = " + proxy.getCacheHitCount());
		sleep(2000);
		mbsc.removeNotificationListener(serverMbeanObject, listener);
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
	
	private static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
