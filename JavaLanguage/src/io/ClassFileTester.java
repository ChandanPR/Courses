package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ClassFileTester {
	
	public static void main(String[] args) {
		byte[] code = new byte[]{(byte)0XCA,(byte)0XFE,(byte)0XBA,(byte)0XBE};
		try(FileInputStream fis = new FileInputStream("./bin/io/ClassFileTester.class")){
			byte[] b = new byte[4];
			if(fis.read(b) != -1){
				if(Arrays.equals(code, b)){
					System.out.println("Class File");
				}else{
					System.err.println("Not a class file");
				}
			}else{
				System.err.println("Not a class file");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
