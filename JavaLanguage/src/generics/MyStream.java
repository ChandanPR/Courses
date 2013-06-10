package generics;

import java.io.BufferedInputStream;

public class MyStream<T>  extends BufferedInputStream{
	T[] array;
	
	public MyStream(int size){
		//Doesn't compile
//		array = new T[size];
		super(null);
	}

}
