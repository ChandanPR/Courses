package io;

import java.io.Console;

public class ConsoleTest {
	
	public static void main(String[] args) {
		Console console = System.console();
		//WHEN JVM INVOKED FROM IDE OR INVOKED IN BACKGROUND 
		//CONSOLE WILL BE NULL
		if(console == null){
			System.err.println("CONSOLE IS NULL");
			return;
		}
		
		System.out.println(console.readLine());
		System.out.println(console.readLine());
//		expects a format string. throws NPE
//		System.out.println(console.readLine(null));
		
		//Doesn't echo the characters to console
		System.out.println(new String(console.readPassword()));
		
		//NOT ABLE TO SEE ANY DIFF??!! BOTH WORKS FINE.
		String str = "å, ä, and ö";
		System.out.println("String scandString = "+str);
		console.printf("String scandString = "+str);
	}

}
