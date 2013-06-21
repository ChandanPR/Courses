package basics;

import java.util.Random;

public class RandomTest {

	private static final Random rnd = new Random();

	// Common but deeply flawed!
	static int random(int n) {
		return Math.abs(rnd.nextInt()) % n;
	}

	public static void main(String[] args) {
		testRandom((int)Math.pow(2, 50));
		testRandom((int)Math.pow(3, 50));
		testRandom(2 * (Integer.MAX_VALUE / 3));
		
		testRealRandom((int)Math.pow(2, 50));
		testRealRandom((int)Math.pow(3, 50));
		testRealRandom(2 * (Integer.MAX_VALUE / 3));
	}

	private static void testRandom(int n) {
		int low = 0;
		for (int i = 0; i < 1000000; i++)
			if (random(n) < n / 2)
				low++;
		System.out.println(n+":"+low);
	}
	
	private static void testRealRandom(int n) {
		int low = 0;
		Random random = new Random();
		for (int i = 0; i < 1000000; i++)
			if (random.nextInt(n) < n / 2)
				low++;
		System.out.println("Real Random : "+n+":"+low);
	}

}
