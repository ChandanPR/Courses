package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsWildCardDemo {
	
	public static void main(String[] args) {
		List<Integer> ints = Arrays.<Integer>asList(1,2,3);
		List<? extends Number> list = ints;
		for(Number num : list){
			System.out.println(num);
		}
		//Doesn't work
//		list.add(10);
		
		List<? super Number> list1 = new ArrayList<Number>();
		list1.add(10);
		list1.add(100d);
		list1.add(10.0f);
		//Doesn't work
//		for(Number num : list1){
//			System.out.println(num);
//		}
		
		//You can use an interface also
		List<? super Comparable<?>> list2 = new ArrayList<>();
		List<? extends Comparable<?>> list3 = new ArrayList<>();
		
		
		List<?> intsList = new ArrayList<Integer>();
		for(Object obj : intsList){
			
		}
		//Doesn't work
//		intsList.add(10);
	}

}
