package jmx.standard;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class JMXNotification extends NotificationBroadcasterSupport implements
		JMXNotificationMBean {

	private int count = 0;
	private int sequenceNumber = 1;

	@Override
	public void setCount(int count) {
		int oldCount = count;
		this.count = count;
		System.out.println("Current Count " + this.count);

		Notification n = new AttributeChangeNotification(this,
				sequenceNumber++, System.currentTimeMillis(), "Count changed",
				"Count", "int", oldCount, this.count);

		sendNotification(n);
	}
	
	@Override 
    public MBeanNotificationInfo[] getNotificationInfo() { 
        String[] types = new String[] { 
            AttributeChangeNotification.ATTRIBUTE_CHANGE 
        }; 
        String name = AttributeChangeNotification.class.getName(); 
        String description = "An attribute of this MBean has changed"; 
        MBeanNotificationInfo info = 
            new MBeanNotificationInfo(types, name, description); 
        return new MBeanNotificationInfo[] {info}; 
    } 

	@Override
	public int getCount() {
		return count;
	}

}
