package io.serialization;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ModifiedObjectReader {
  public static void main(String[] args) throws Exception {
    ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream("smalldata.bin")
        )
    );
    byte[] data;
    while ((data = (byte[]) in.readObject()) != null) {
      System.out.println(data[0]);
    }
    in.close();
  }
}
