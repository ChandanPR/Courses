package io.serialization;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassWithSVUID implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> stringList  = new ArrayList<String>();
	private ArrayList<String> stringList1  = new ArrayList<String>();
	
	private int intValue = 10;
	private long longValue = 15L;
	private double doubleValue = 1.0;
	private transient Object object = new Object();
	
	public ClassWithSVUID(){
		for(int i=0; i<100; i++){
			stringList.add(""+i);
		}
	}
}
