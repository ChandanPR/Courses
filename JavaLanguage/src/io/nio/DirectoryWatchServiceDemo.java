package io.nio;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class DirectoryWatchServiceDemo {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String pathString = "E:/Chandan/Learning/GitHub/Courses/JavaLanguage/src/io/nio";
		Path path = Paths.get(pathString);
		WatchService watchService = path.getFileSystem().newWatchService();
		path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		while(true){
			WatchKey key = watchService.take();
			for(WatchEvent<?> event : key.pollEvents()){
				System.out.println("DirectoryWatchServiceDemo.main()");
				System.out.println(event);
			}
			key.reset();
		}
	}

}
