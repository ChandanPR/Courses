package io.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class PointTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
//		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("point.bin")));
//		out.writeObject(new Point(15, 10));
//		out.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("point.bin")));
		Point p = (Point)in.readObject();
		in.close();
		
		System.out.println(p.getX());
		System.out.println(p.getY());
//		System.out.println(p.getQuadrant());


	}

}
