package io.serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationReferenceErrorDemo {

	static class Data implements Serializable {
		String name = "Data";

		@Override
		public String toString() {
			return name;
		}
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		writeTheSameObjectTest();
		System.err.println("_________________________________________________");
		writeTheAlternateObjectTest();

	}

	private static void writeTheAlternateObjectTest() throws IOException,
			FileNotFoundException, ClassNotFoundException {
		Data[] data = new Data[]{new Data(), new Data()};
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("data.bin")))

		) {
			for (int i = 0; i < 10; i++) {
				System.out.println("Writing : " + data[i%2]);
				out.writeObject(data[i%2]);
				data[i%2].name = "Data " + i;
			}
		}

		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("data.bin")))

		) {
			for (int i = 0; i < 10; i++) {
				System.out.println("Reading : " + in.readObject());
			}
		}
	}

	private static void writeTheSameObjectTest() throws IOException,
			FileNotFoundException, ClassNotFoundException {
		Data data = new Data();
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("data.bin")))

		) {
			for (int i = 0; i < 10; i++) {
				System.out.println("Writing : " + data);
				out.writeObject(data);
				out.reset();
				data.name = "Data " + i;
			}
		}

		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("data.bin")))

		) {
			for (int i = 0; i < 10; i++) {
				System.out.println("Reading : " + in.readObject());
			}
		}
	}

}
