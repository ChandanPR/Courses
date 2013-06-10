package io.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationClassTest {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		SerializationClass serializationClass = new SerializationClass();
		serializationClass.intValue = 30;
		serializationClass.longValue = 40;
		SerializationClass serializationSecondClass = new SerializationClass();
		serializationClass.serializationClass = serializationSecondClass;
		System.out.println("serializationClass  :"+serializationClass);
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream((new File("ser.bin"))));
		out.writeObject(serializationClass);
		out.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("ser.bin")));
		SerializationClass otherSerializationClass = (SerializationClass)in.readObject();
		in.close();
		System.out.println("otherSerializationClass  :"+otherSerializationClass);
	}

}
