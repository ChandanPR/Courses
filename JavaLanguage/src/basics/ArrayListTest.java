package basics;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
	
	public static void main(String... args) {
		List<Integer> list = new ArrayList<>();
		addToList(list);
		System.out.println(list.get(0));
		Integer i = list.get(0);
	}
	
	public static void addToList(List list){
		list.add("0067");
	}

}
