package directories;

import java.io.File;

public class ConcurrentDirectorySizeFinder extends AbstractDirectorySizeFinder {

	@Override
	public long getDirectorySize(File file) {
		return 0;
	}

}
