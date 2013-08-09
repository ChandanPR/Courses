package gc;

import java.util.ArrayList;
import java.util.List;

/**
 * java -verbose:gc -Xms40m -Xmx40m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=filepath
 * @author chandanr
 *
 */

public class HeapAnalysisWithoutThreadsAsRootsDemo {
	private static List<String> strings = new ArrayList<String>();

	public static void main(String[] args) {
		for (;;) {
			new Thread() {
				public void run() {
					strings.add(new String(new char[] { 'c', 'h', 'a', 'n',
							'd', 'a', 'n' }));
				}

			}.start();
		}
	}
	
	

//	public static void main(String[] args) {
//		new Thread() {
//			public void run() {
//				for (;;) {
//					strings.add(new String(new char[] { 'c', 'h', 'a', 'n',
//							'd', 'a', 'n' }));
//				}
//
//			}
//		}.start();
//	}

}
