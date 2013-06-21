package basics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PreferForEachLoopTest {

	enum NUMBERS {
		ONE, TWO,
	};

	public static void main(String[] args) {
		List<NUMBERS> list = Arrays.asList(NUMBERS.values());
		System.out.println("BAD WAY");
		for (Iterator<NUMBERS> i = list.iterator(); i.hasNext();) {
			for (Iterator<NUMBERS> j = list.iterator(); j.hasNext();) {
				// HA HA - the i.next() is called too many times :)
				System.out.println(i.next() + ":" + j.next());
			}
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("GOOD WAY");
		for (NUMBERS num : list) {
			for (NUMBERS num1 : list) {
				System.out.println(num + ":" + num1);
			}
		}

	}
}
