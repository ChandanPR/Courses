package directories;

import java.io.File;


public abstract class AbstractDirectorySizeFinder {

	public abstract long getDirectorySize(File file);
}
