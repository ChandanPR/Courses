package jmx.connectors;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.NotificationBroadcasterSupport;

public class NumberCache extends NotificationBroadcasterSupport implements NumberCacheMBean {

	private int cacheUpdateCount;
	private int cacheHitCount;
	private int resets;

	public int getCacheHitCount() {
		return cacheHitCount;
	}

	public int getCacheUpdateCount() {
		return cacheUpdateCount;
	}

	public void setCacheHitCount(int cacheHitCount) {
		this.cacheHitCount = cacheHitCount;
	}

	public void setCacheUpdateCount(int cacheUpdateCount) {
		this.cacheUpdateCount = cacheUpdateCount;
	}

	@Override
	public void reset() {
		int oldCount = cacheUpdateCount;
		cacheHitCount = 0;
		cacheUpdateCount = 0;
		AttributeChangeNotification notification = new AttributeChangeNotification(
				this, ++resets, System.currentTimeMillis(), "RESET DONE",
				"CacheUpdateCount", "int", oldCount, cacheUpdateCount);
		sendNotification(notification);

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

}
