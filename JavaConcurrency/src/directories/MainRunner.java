package directories;

import java.io.File;

public class MainRunner {
	
	public static void main(String[] args) {
		System.out.println("START");
		checkPerformance("Sequential", new SequentialDirectorySizeFinder());
	}
	
	static void checkPerformance(String name,AbstractDirectorySizeFinder sizeFinder){
		long time = System.nanoTime();
		long length = sizeFinder.getDirectorySize(new File("/Users"));
		System.out.println(name+"\t\t"+length+"\t\t"+ ((System.nanoTime() - time) / 1.0e9));
	}

}
