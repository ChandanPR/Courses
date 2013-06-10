package io.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class VectorWritingSize {
  public static void main(String[] args) throws IOException {
    test(new LinkedList<String>());
    test(new ArrayList<String>());
    test(new Vector<String>());
  }

  public static void test(List<String> list) throws IOException {
    insertJunk(list);
    for (int i = 0; i < 10; i++) {
      list.add("hello world");
    }
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream out = new ObjectOutputStream(baos);
    out.writeObject(list);
    out.close();
    System.out.println(list.getClass().getSimpleName() +
        " used " + baos.toByteArray().length + " bytes");
  }

  private static void insertJunk(List<String> list) {
    for(int i = 0; i<1000 * 1000; i++) {
      list.add("junk");
    }
    list.clear();
  }
}
