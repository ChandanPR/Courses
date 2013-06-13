package io.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;


public class FileTreeVisitorDemo {

	
	public static void main(String[] args) throws IOException {
		String pathString = "E:/Chandan/Learning/GitHub/Courses/JavaLanguage/src";
		FileVisitor<Path> visitor = new MyFileVisitor<>();
		Files.walkFileTree(Paths.get(pathString), visitor);
	}
	
	private static class MyFileVisitor<T> extends SimpleFileVisitor<T>{

		@Override
		public FileVisitResult preVisitDirectory(T dir,
				BasicFileAttributes attrs) throws IOException {
			FileVisitResult result = super.preVisitDirectory(dir, attrs);
			System.out.println("preVisitDirectory() : "+dir);
			return result;
		}

		@Override
		public FileVisitResult visitFile(T file, BasicFileAttributes attrs)
				throws IOException {
			FileVisitResult result = super.visitFile(file, attrs);
			System.out.println("visitFile() : "+file);
			return result;
		}

		@Override
		public FileVisitResult visitFileFailed(T file, IOException exc)
				throws IOException {
			FileVisitResult result = super.visitFileFailed(file, exc);
			System.out.println("visitFileFailed() : "+file);
			return result;
		}

		@Override
		public FileVisitResult postVisitDirectory(T dir, IOException exc)
				throws IOException {
			FileVisitResult result = super.postVisitDirectory(dir, exc);
			System.out.println("postVisitDirectory() : "+dir);
			return result;
		}
		
	}
}
