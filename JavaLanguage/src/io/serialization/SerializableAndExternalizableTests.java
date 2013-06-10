package io.serialization;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableAndExternalizableTests {

	public static void main(String[] args) {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("temp.data"))) {
			oos.writeObject(new SData("sData"));
			oos.writeObject(new EData("eData"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"temp.data"))) {
			System.out.println(ois.readObject());
			System.out.println(ois.readObject());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static class SData implements Serializable {
		private String sData;

		public SData(){
			System.out.println("SData.SData() Default Constructor");
		}
		
		public SData(String sData) {
			System.out.println("SData.SData() Constructor");
			this.sData = sData;
		}

		@Override
		public String toString() {
			return sData;
		}
	}

	private static class EData implements Externalizable {
		private String eData;

		public EData(){
			System.out.println("EData.EData() Default Constructor");
		}
		
		public EData(String sData) {
			System.out.println("EData.EData() Constructor");
			this.eData = sData;
		}

		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeObject(eData);
		}

		@Override
		public void readExternal(ObjectInput in) throws IOException,
				ClassNotFoundException {
			eData = in.readObject().toString();
		}
		
		@Override
		public String toString() {
			return eData;
		}
	}

}
