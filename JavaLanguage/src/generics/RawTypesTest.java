package generics;

import java.util.ArrayList;
import java.util.List;

public class RawTypesTest {
	
	public static void main(String[] args) {
		testRaw1();
//		testRaw2();
		RawTypesTest.compare("a", 10);
	}
	
	private static <T1,T2> boolean compare(T1 t1, T2 t2){
		return t1.equals(t2);
	}

	private static void testRaw1() {
		List rawList = new ArrayList();
		rawList.add("Test");
		rawList.add("Test2");
		List<String> stringList = rawList;
		for(String str : stringList){
			System.out.println(str);
		}
	}
	
	private static void testRaw2() {
		List rawList = new ArrayList();
		rawList.add("Test");
		rawList.add(10);
		List<String> stringList = rawList;
		for(String str : stringList){
			System.out.println(str);
		}
	}

}
