package basics;

import java.util.Arrays;
import java.util.Iterator;

public class InheritanceExamples {
	
	public static void main(String[] args) {
		Boo x = new Boo();
		
//		Doo a = x;
//		Little f = (Woo)x;
		
//		Object[] o = {new Integer(34),new String("blue"),new Boolean(false), new Integer(18),};
//		Arrays.sort(o);
//		for (int i = 0; i < o.length; i++) {
//			System.out.println(o);
//		}
		
//		Object o = new int[]{2,5,9};
//		int[] monster = (int[])o;
//		for (int i : monster) {
//			System.out.println(i);
//		}
		
		char[] c = {'h','e','l','l','o'};
		char[]c1 = c.clone();
		c1[4] = '!';
		System.out.println(c1);
		
		String abc = "abc";
		
		abc += "def";
		
		int size = 100;
		String[][] sample = new String[size][];
		sample[0][0] = "A";
		System.out.println(sample[0][0]);
	}

}

interface Little{}
class Doo implements Little{}
class Boo extends Doo{}
class Woo extends Boo{}
