package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsTest {
	
	public static void main(String[] args) {
		
		List<Integer> ints = Arrays.asList(1,2,3);
//		Compilation Error
//		List<Number> nums = ints;
		
//		List<? extends Number> nums = ints;
//		nums.add(null);
//		Compilation Error
//		nums.add(3);
//		nums.add(Integer.getInteger("3"));
//		nums.add((Number)new Integer(129));
		
		//If the list is declared as ? extends Number, then you have to add only Number objects.
		List<Number> numberList = new ArrayList<>();
		List<Double> doubles = Arrays.asList(1.0,2.4,3.6);
		numberList.addAll(ints);
		numberList.addAll(doubles);
		
		//Doesn't compile. Serializable, Comparable both are possible. Ambiguity.
//		List<Object> objects = Arrays.asList("1",2);
		List<Number> objects = Arrays.<Number>asList(1,2);
		List<? super Integer> integers = objects;
		for(Object obj : integers){
			System.out.println(obj);
		}
		
		
		List<? extends Number> nums = ints;
		for(Number num : nums){
			System.out.println(num);
		}
		
		
	}

}
