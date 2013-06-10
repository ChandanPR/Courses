package io.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialVersionPerformanceTest {

	public static void main(String[] args) throws Exception {
		measure(new ClassWithoutUID());
		measure(new ClassWithSVUID());
	}
	
	private static void measure(Object object) throws Exception{
		long time = System.currentTimeMillis();
		serialize(object);
		System.out.println(object.getClass().getName()+(System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		deserialize();
		System.out.println(object.getClass().getName()+(System.currentTimeMillis() - time));
	}

	private static void serialize(Object obj) throws Exception {
		for (int i = 0; i < 100; i++) {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream((new File("ser1.bin"))));
			out.writeObject(obj);
			out.close();
		}
	}

	private static void deserialize() throws Exception {
		for (int i = 0; i < 100; i++) {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					new File("ser1.bin")));
			in.readObject();
			in.close();
		}
		
	}

}
