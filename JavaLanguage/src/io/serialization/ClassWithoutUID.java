package io.serialization;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassWithoutUID implements Serializable{
	
	private ArrayList<String> stringList  = new ArrayList<String>();
	private ArrayList<String> stringList1  = new ArrayList<String>();
	
	private int intValue = 10;
	private long longValue = 15L;
	private double doubleValue = 1.0;
	private transient Object object = new Object();
	
	public ClassWithoutUID(){
		for(int i=0; i<100; i++){
			stringList.add(""+i);
		}
	}
}
