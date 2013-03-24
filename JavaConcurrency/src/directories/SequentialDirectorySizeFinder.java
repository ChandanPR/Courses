package directories;

import java.io.File;

public class SequentialDirectorySizeFinder extends AbstractDirectorySizeFinder {

	@Override
	public long getDirectorySize(File file) {
//		System.out.println(file.getName());
		if (file.isFile()) {
			return file.length();
		} else {
			long size = 0;
			File[] files = file.listFiles();
//			System.out.println(files);
			if (files != null && files.length > 0) {
				for (File fileName : files) {
					size += getDirectorySize(fileName);
				}
			}
			return size;
		}
	}

}
