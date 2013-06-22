package io.serialization;

import java.io.PrintStream;

public class PrintingTest {
	
	public static void main(String[] args) throws InterruptedException {
		PrintStream[] printers = new PrintStream[]{System.err, System.out};
		String[] name = new String[]{"err","out"};
		for(int i=0; i<10; i++){
			printers[i%2].println("Printing Using "+name[i%2]);
		}
		
		printers[0].flush();printers[1].flush();
		Thread.sleep(1000);
		int[] ints = new int[]{1,2};
		
		for(int i=0; i<10; i++){
			printers[i%2].print(ints[i%2]);
		}
	}

}
