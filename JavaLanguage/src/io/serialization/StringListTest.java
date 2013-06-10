package io.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StringListTest {
	
	private static final int ITERATIONS = 20;
	private static final int COUNT = 500;
	
	public static void main(String[] args) throws Exception{
//		BetterStringList list = new BetterStringList();
//		//MAX 841 elements
//		for(int i=0; i<10000; i++){
//			list.add(""+i);
//		}
		
		StringList list = new StringList();
		BetterStringList betterList = new BetterStringList();
		for(int i=0; i<841; i++){
			list.add(""+i);
			betterList.add(""+i);
		}
		
		for(int i=0; i<COUNT*1000; i++){
			betterList.add(""+i);
		}
		
//		serializeStringList(list);
		
		long time  = System.currentTimeMillis();
		serializeStringList(list);
		System.out.println(System.currentTimeMillis() - time);
		time  = System.currentTimeMillis();
		deserializeStringList();
		System.out.println(System.currentTimeMillis() - time);
		time  = System.currentTimeMillis();
		serializeBetterStringList(betterList);
		System.out.println(System.currentTimeMillis() - time);
		time  = System.currentTimeMillis();
		deserializeBetterStringList();
		System.out.println(System.currentTimeMillis() - time);
		
		
	}
	
	private static void serializeStringList(StringList list) throws Exception{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream((new File("ser1.bin"))));
		out.writeObject(list);
		out.close();
	}
	
	private static void deserializeStringList() throws Exception{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("ser1.bin")));
		StringList deserializedList = (StringList)in.readObject();
		in.close();
	}
	
	private static void serializeBetterStringList(BetterStringList list) throws Exception{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream((new File("ser2.bin"))));
		out.writeObject(list);
		out.close();
	}
	
	private static void deserializeBetterStringList() throws Exception{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("ser2.bin")));
		BetterStringList deserializedList = (BetterStringList)in.readObject();
		in.close();
	}

}
