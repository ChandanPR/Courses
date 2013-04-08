package jmx.connectors;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import javax.management.Attribute;
import javax.management.AttributeChangeNotification;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;

public class DynamicNumberCache extends NotificationBroadcasterSupport
		implements DynamicMBean {

	private HashMap<Integer, Integer> cache = new HashMap<>();
	private int cacheHits;
	private int cacheUpdates;

	public DynamicNumberCache() {
		buildMBeanInfo();
	}

	public Integer getInteger(int num) {
		Integer numInterger = cache.get(num);
		if (numInterger == null) {
			numInterger = num;
			cache.put(numInterger, numInterger);
			cacheUpdates++;
		}else{
			cacheHits++;
		}
		return numInterger;
	}

	public int getCacheHits() {
		return cacheHits;
	}

	public int getCacheUpdates() {
		return cacheUpdates;
	}

	public void reset() {
		cacheHits = 0;
		cacheUpdates = 0;
		cache.clear();
	}

	private String dClassName = this.getClass().getName();
	private String dDescription = "Simple implementation of a dynamic MBean.";
	private MBeanAttributeInfo[] dAttributes = new MBeanAttributeInfo[2];
	private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
	private MBeanNotificationInfo[] dNotifications = new MBeanNotificationInfo[3];
	private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[2];
	private MBeanInfo dMBeanInfo = null;

	private void buildMBeanInfo() {
		dAttributes[0] = new MBeanAttributeInfo("CacheHits",
				"java.lang.Integer", "Cache Hit Count", true, false, false);
		dAttributes[1] = new MBeanAttributeInfo("CacheUpdates",
				"java.lang.Integer",
				"Number of times the Cache has been updated", true, false,
				false);

		@SuppressWarnings("rawtypes")
		Constructor[] constructors = this.getClass().getConstructors();
		dConstructors[0] = new MBeanConstructorInfo("Constructs a "
				+ "DynamicNumberCache object", constructors[0]);

		dOperations[0] = new MBeanOperationInfo("reset",
				"reset CacheHits and CacheUpdates "
						+ "attributes to their initial values", null, "void",
				MBeanOperationInfo.ACTION);
		MBeanParameterInfo[] params = { new MBeanParameterInfo("num", "int",
				"Input Number") };
		dOperations[1] = new MBeanOperationInfo("reset",
				"reset CacheHits and CacheUpdates "
						+ "attributes to their initial values", params, "void",
				MBeanOperationInfo.ACTION);

		dNotifications[0] = new MBeanNotificationInfo(
				new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE },
				AttributeChangeNotification.class.getName(),
				"This notification is emitted when the CacheHits is update");
		dNotifications[1] = new MBeanNotificationInfo(
				new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE },
				AttributeChangeNotification.class.getName(),
				"This notification is emitted when the CacheUpdates is update");
		dNotifications[2] = new MBeanNotificationInfo(
				new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE },
				AttributeChangeNotification.class.getName(),
				"This notification is emitted when the reset is called");

		dMBeanInfo = new MBeanInfo(dClassName, dDescription, dAttributes,
				dConstructors, dOperations, dNotifications);
	}

	@Override
	public Object getAttribute(String attribute)
			throws AttributeNotFoundException, MBeanException,
			ReflectionException {

		if (attribute == null) {
			throw new RuntimeOperationsException(new IllegalArgumentException(
					"Attribute Should not be null"));
		}

		if ("CacheHits".equals(attribute)) {
			return getCacheHits();
		}

		if ("CacheUpdates".equals(attribute)) {
			return getCacheUpdates();
		}
		throw new AttributeNotFoundException("Cannot find " + attribute
				+ " attribute in " + dClassName);
	}
	

	@Override
	public void setAttribute(Attribute attribute)
			throws AttributeNotFoundException, InvalidAttributeValueException,
			MBeanException, ReflectionException {
		
		throw new RuntimeOperationsException(new IllegalArgumentException(
				"No Setter Operations available"));
		
//		if (attribute == null) {
//			throw new RuntimeOperationsException(new IllegalArgumentException(
//					"Attribute Should not be null"));
//		}
//		
//		String name = attribute.getName();
//        Object value = attribute.getValue();
//        if (name == null) {
//            throw new RuntimeOperationsException(
//                  new IllegalArgumentException("Attribute name cannot be null"),
//                  "Cannot invoke the setter of " + dClassName +
//                  " with null attribute name");
//        }
//
//		if ("CacheHits".equals(name)) {
//			checkValue(name, value);
//		}
//
//		if ("CacheUpdates".equals(attribute)) {
//			checkValue(name, value);
//		}
//		throw new AttributeNotFoundException("Cannot find " + attribute
//				+ " attribute in " + dClassName);

	}

	private void checkValue(String name,Object value) {
		try {
			if(!Class.forName("java.lang.Integer").isAssignableFrom(value.getClass())){
				throw new RuntimeOperationsException(new RuntimeException("Cannot set attribute " + name + " to a " + 
			            value.getClass().getName() +
			            " object, String expected"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		if (attributes == null) {
            throw new RuntimeOperationsException(
                new IllegalArgumentException("attributeNames[] cannot be null"),
                "Cannot invoke a getter of " + dClassName);
        }
        AttributeList resultList = new AttributeList();

        if (attributes.length == 0)
            return resultList;
        
        for (int i = 0 ; i < attributes.length ; i++) {
            try {        
                Object value = getAttribute((String) attributes[i]);     
                resultList.add(new Attribute(attributes[i],value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultList;
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		throw new RuntimeOperationsException(new IllegalArgumentException(
				"No Setter Operations available"));
//		if (attributes == null) {
//            throw new RuntimeOperationsException(
//                      new IllegalArgumentException(
//                                 "AttributeList attributes cannot be null"),
//                      "Cannot invoke a setter of " + dClassName);
//        }
//        AttributeList resultList = new AttributeList();
//
//        if (attributes.isEmpty())
//            return resultList;
//
//        for (Iterator<Object> i = attributes.iterator(); i.hasNext();) {
//            Attribute attr = (Attribute) i.next();
//            try {
//                setAttribute(attr);
//                String name = attr.getName();
//                Object value = getAttribute(name); 
//                resultList.add(new Attribute(name,value));
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return resultList;
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature)
			throws MBeanException, ReflectionException {
		
		if (actionName == null) {
            throw new RuntimeOperationsException(
                 new IllegalArgumentException("Operation name cannot be null"), 
                 "Cannot invoke a null operation in " + dClassName);
        }
		
		if (actionName.equals("reset")) {
            reset();
            return null;
        }else if (actionName.equals("getInteger")) {
        	checkValue("num", params[0]);
            return getInteger((int)params[0]);
        } else { 
            // Unrecognized operation name
            //
            throw new ReflectionException(
                                new NoSuchMethodException(actionName), 
                                "Cannot find the operation " + actionName +
                                " in " + dClassName);
        }
		
	}

	@Override
	public MBeanInfo getMBeanInfo() {
		return dMBeanInfo;
	}

}
