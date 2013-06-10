package io.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class PathApisDemo {
	
	public static void main(String[] args) throws IOException {
		String pathString = "E:/Chandan/Learning/GitHub/Courses/JavaLanguage/src/io/nio/PathApisDemo.java";
		
		Path path = Paths.get(pathString);
		
		System.out.println("FileName :"+path.getFileName());
		System.out.println("FileSystem :"+path.getFileSystem());
		System.out.println("Parent :"+path.getParent());
		System.out.println("Root : "+path.getRoot());
		System.out.println("URI : "+path.toUri());
		System.out.println("AbsolutePath : "+path.toAbsolutePath());
		System.out.println("NormalizePath : "+path.normalize());
		System.out.println("RealPath : "+path.toRealPath(LinkOption.NOFOLLOW_LINKS));
		System.out.println("___________________________________");
		for(Path subPath : path){
			System.out.println(subPath);
		}
		System.out.println("___________________________________");
		System.out.println("Path :"+path);
		
		
		Path path1 = Paths.get("E:/Chandan/Learning/GitHub/Courses/JavaLanguage/src/io/nio/");
		System.out.println("Resolved Path: "+path1.resolve("PathApisDemo.java"));
		
		File fileFromPath = path.toFile();
		Path pathFromFile = fileFromPath.toPath();
		System.out.println("pathFromFile: "+pathFromFile);
		
		//BE CAREFUL ON THE EQUALITY TESTS FOR PATHS
		//IF YOU ARE NOT USING ABSOLUTE PATHS IT IS NOT GUARANTEED THAT equals() and compareTo(..)
		//CONSIDERS THEM AS EQUAL.
		//FOR EQUALITY COMPARISON MAKE SURE PATHS ARE ABSOLUTE AND NORMALIZED
		
		
		//READING ATTRIBUTES
		BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
		System.out.println("Size :"+attributes.size());
		System.out.println("isDirectory :"+attributes.isDirectory());
		System.out.println("isOther :"+attributes.isOther());
		System.out.println("isRegularFile :"+attributes.isRegularFile());
		System.out.println("isSymbolicLink :"+attributes.isSymbolicLink());
		System.out.println("lastAccessTime :"+attributes.lastAccessTime());
		System.out.println("lastModifiedTime :"+attributes.lastModifiedTime());
		System.out.println("creationTime :"+attributes.creationTime());
	}

}
