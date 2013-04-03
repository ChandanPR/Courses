package jmx.connectors;

import java.io.IOException;

import javax.management.Attribute;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class JMXServer {

	public static void main(String[] args) {
		try {
			// Instantiate the MBean server
			//
			log(">>> Create the MBean server");
			MBeanServer mbs = MBeanServerFactory.createMBeanServer();
			waitForEnterPressed();

			// Get default domain
			//
			log(">>> Get the MBean server's default domain");
			String domain = mbs.getDefaultDomain();
			log("Default Domain = " + domain);
			
			for(String domainName : mbs.getDomains()){
				log(domainName);
			}
			
			waitForEnterPressed();

			// Create and register the SimpleStandard MBean
			//
			String mbeanClassName = "jmx.connectors.SimpleStandard";
			String mbeanObjectNameStr = domain + ":type=" + mbeanClassName
					+ ",name=1";
			ObjectName mbeanObjectName = createSimpleMBean(mbs, mbeanClassName,
					mbeanObjectNameStr);
			waitForEnterPressed();

			// Get and display the management information exposed by the
			// SimpleStandard MBean
			//
			printMBeanInfo(mbs, mbeanObjectName, mbeanClassName);
			waitForEnterPressed();

			// Manage the SimpleStandard MBean
			//
			manageSimpleMBean(mbs, mbeanObjectName, mbeanClassName);
			waitForEnterPressed();

			// Create and register the SimpleDynamic MBean
			//
			mbeanClassName = "jmx.connectors.SimpleDynamic";
			mbeanObjectNameStr = domain + ":type=" + mbeanClassName + ",name=1";
			mbeanObjectName = createSimpleMBean(mbs, mbeanClassName,
					mbeanObjectNameStr);
			waitForEnterPressed();

			// Get and display the management information exposed by the
			// SimpleDynamic MBean
			//
			printMBeanInfo(mbs, mbeanObjectName, mbeanClassName);
			waitForEnterPressed();

			// Manage the SimpleDynamic MBean
			//
			manageSimpleMBean(mbs, mbeanObjectName, mbeanClassName);
			waitForEnterPressed();

			// Create an RMI connector server
			//
			log("Create an RMI connector server");
			JMXServiceURL url = new JMXServiceURL(
					"service:jmx:rmi:///jndi/rmi://localhost:9999/server");
			JMXConnectorServer cs = JMXConnectorServerFactory
					.newJMXConnectorServer(url, null, mbs);

			// Start the RMI connector server
			//
			log("Start the RMI connector server");
			cs.start();
			log("The RMI connector server successfully started");
			log("and is ready to handle incoming connections");
			log("Start the client on a different window and");
			log("press <Enter> once the client has finished");
			waitForEnterPressed();

			// Stop the RMI connector server
			//
			log("Stop the RMI connector server");
			cs.stop();
			System.out.println("Bye! Bye!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ObjectName createSimpleMBean(MBeanServer mbs,
			String mbeanClassName, String mbeanObjectNameStr) {
		log("Create the " + mbeanClassName + " MBean within the MBeanServer");
		log("ObjectName = " + mbeanObjectNameStr);
		try {
			ObjectName mbeanObjectName = ObjectName
					.getInstance(mbeanObjectNameStr);
			mbs.createMBean(mbeanClassName, mbeanObjectName);
			return mbeanObjectName;
		} catch (Exception e) {
			logError("Could not create the " + mbeanClassName + " MBean !!!");
			e.printStackTrace();
			logError("EXITING...");
			System.exit(1);
		}
		return null;
	}

	private static void printMBeanInfo(MBeanServer mbs,
			ObjectName mbeanObjectName, String mbeanClassName) {
		log("Retrieve the management information for the " + mbeanClassName);
		log("    MBean using the getMBeanInfo() method of the MBeanServer");
		MBeanInfo info = null;
		try {
			info = mbs.getMBeanInfo(mbeanObjectName);
		} catch (Exception e) {
			logError("!!! Could not get MBeanInfo object for " + mbeanClassName
					+ " !!!");
			e.printStackTrace();
			return;
		}
		log("CLASSNAME: " + info.getClassName());
		log("DESCRIPTION: " + info.getDescription());
		log("ATTRIBUTES");
		MBeanAttributeInfo[] attrInfo = info.getAttributes();
		if (attrInfo.length > 0) {
			for (int i = 0; i < attrInfo.length; i++) {
				log(" ** NAME: " + attrInfo[i].getName());
				log("    DESCR: " + attrInfo[i].getDescription());
				log("    TYPE: " + attrInfo[i].getType() + "READ: "
						+ attrInfo[i].isReadable() + "WRITE: "
						+ attrInfo[i].isWritable());
			}
		} else
			log(" ** No attributes **");
		log("CONSTRUCTORS");
		MBeanConstructorInfo[] constrInfo = info.getConstructors();
		for (int i = 0; i < constrInfo.length; i++) {
			log(" ** NAME: " + constrInfo[i].getName());
			log("    DESCR: " + constrInfo[i].getDescription());
			log("    PARAM: " + constrInfo[i].getSignature().length
					+ " parameter(s)");
		}
		log("OPERATIONS");
		MBeanOperationInfo[] opInfo = info.getOperations();
		if (opInfo.length > 0) {
			for (int i = 0; i < opInfo.length; i++) {
				log(" ** NAME: " + opInfo[i].getName());
				log("    DESCR: " + opInfo[i].getDescription());
				log("    PARAM: " + opInfo[i].getSignature().length
						+ " parameter(s)");
			}
		} else
			log(" ** No operations ** ");
		log("NOTIFICATIONS");
		MBeanNotificationInfo[] notifInfo = info.getNotifications();
		if (notifInfo.length > 0) {
			for (int i = 0; i < notifInfo.length; i++) {
				log(" ** NAME: " + notifInfo[i].getName());
				log("    DESCR: " + notifInfo[i].getDescription());
				String notifTypes[] = notifInfo[i].getNotifTypes();
				for (int j = 0; j < notifTypes.length; j++) {
					log("    TYPE: " + notifTypes[j]);
				}
			}
		} else
			log(" ** No notifications **");
	}

	private static void manageSimpleMBean(MBeanServer mbs,
			ObjectName mbeanObjectName, String mbeanClassName) {

		log(">>> Manage the " + mbeanClassName + " MBean using its attributes ");
		log("    and operations exposed for management");

		try {
			// Get attribute values
			printSimpleAttributes(mbs, mbeanObjectName);

			// Change State attribute
			log("    Setting State attribute to value \"new state\"...");
			Attribute stateAttribute = new Attribute("State", "new state");
			mbs.setAttribute(mbeanObjectName, stateAttribute);

			// Get attribute values
			printSimpleAttributes(mbs, mbeanObjectName);

			// Invoking reset operation
			log("    Invoking reset operation...");
			mbs.invoke(mbeanObjectName, "reset", null, null);

			// Get attribute values
			printSimpleAttributes(mbs, mbeanObjectName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printSimpleAttributes(MBeanServer mbs,
			ObjectName mbeanObjectName) {
		try {
			log("    Getting attribute values:");
			String State = (String) mbs.getAttribute(mbeanObjectName, "State");
			Integer NbChanges = (Integer) mbs.getAttribute(mbeanObjectName,
					"NbChanges");
			log("State     = \"" + State + "\"");
			log("NbChanges = " + NbChanges);
		} catch (Exception e) {
			logError("!!! Could not read attributes !!!");
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

	private static void waitForEnterPressed() {
		try {
			log("Press <Enter> to continue...");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void log(String string) {
		System.out.println(string);
	}

	private static void logError(String string) {
		System.err.println(string);
	}

}
