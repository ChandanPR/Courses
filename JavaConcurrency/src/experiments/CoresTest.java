package experiments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoresTest {
	public static void main(String[] args) throws IOException {
		System.out.println("Available Cores : "
				+ Runtime.getRuntime().availableProcessors());
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 100; i++) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("/Users/chandanpr/Downloads/table.csv"))));

			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			
		}
		

	}
}
