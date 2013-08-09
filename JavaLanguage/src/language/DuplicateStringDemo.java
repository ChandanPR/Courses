package language;

import java.util.ArrayList;
import java.util.List;

public class DuplicateStringDemo {

	public static void main(String[] args) {
		for (int i = 0; i < 400; i++)
			new Thread("Thread " + i) {
				public void run() {
					List<String> strings = new ArrayList<String>();
					for (;;) {
						strings.add(new String(new char[] { 'c', 'h', 'a', 'n',
								'd', 'a', 'n' }));
					}

				};
			}.start();
	}

}
