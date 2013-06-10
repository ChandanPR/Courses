package io.serialization;
import java.io.Serializable;


public class SerializationClass implements Serializable{
	
	public int intValue = 0;
	public long longValue = 0;
	public SerializationClass serializationClass;
	@Override
	public String toString() {
		String str = (serializationClass == null)?"":serializationClass.toString()+"---";
		return str+intValue+":"+longValue;
	}

}
