package utils;

import java.util.Random;
import static utils.Utilities.*;

public class StandardRandom {
	
	private static Random random;
	
	static{
		random = new Random(System.currentTimeMillis());
	}
	
	private StandardRandom(){
	}
	
	public static double random(){
		return uniform();
	}

	public static double uniform() {
		return random.nextDouble();
	}
	
	public static int uniform(int n){
		return random.nextInt(n);
	}
	
	public static void shuffle(int[] a){
		int length = a.length;
		for(int i=0; i<length; i++){
			int r = uniform(i+1);
			exchange(a, i, r);
		}
	}

}
