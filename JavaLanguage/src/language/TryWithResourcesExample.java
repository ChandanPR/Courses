package language;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TryWithResourcesExample {

	public static void main(String[] args) throws IOException {
		tryOlderWay();
		testNormally();
		testWithinLoop();
	}

	private static void testNormally() throws IOException {
		int count = 0;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in))) {
			while (count++ < 5) {
				System.out.println(reader.readLine());
			}
			System.out
					.println("TryWithResourcesExample.testNormally() Exiting");
		}

	}

	private static void testWithinLoop() {
		int count = 0;
		while (count++ < 5) {
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(System.in))) {
				System.out.println(reader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("TryWithResourcesExample.testWithinLoop() Exiting");

	}

	private static void tryOlderWay() {
		int count = 0;
		while (count++ < 5) {
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(System.in));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.err));
				writer.write(reader.readLine());
//				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("TryWithResourcesExample.testWithinLoop() Exiting");

	}

}
