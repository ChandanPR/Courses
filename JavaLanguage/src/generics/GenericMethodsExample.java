package generics;

import java.util.ArrayList;
import java.util.List;

public class GenericMethodsExample {

	public static <E> List<E> buildList(E... arr){
		ArrayList<E> list = new ArrayList<>();
		for(E e: arr){
			list.add(e);
		}
		return list;
	}
	
	public static void main(String[] args) {
		
		List<Integer> integerList = buildList(1,2,3);
		System.out.println(integerList);
		
//		Compilation Error
//		Multiple markers at this line
//		- Type mismatch: cannot convert from List<Number&Comparable<?>> to List<Number>
//		- Type safety: A generic array of Number&Comparable<?> is created for a varargs parameter
//		List<Number>  numberList = buildList(1,2.5,4,0.5);
		
		
		List<? extends Number>  generalNumberList = buildList(1,2.5,4,0.5);
		System.out.println(generalNumberList);
		List<? extends Comparable<?>>  generalComparableList = buildList(1,2.5,4,0.5);
		System.out.println(generalComparableList);
		List<? extends Object>  generalObjectList = buildList(1,2.5,4,0.5);
		System.out.println(generalObjectList);
		
		//No Warning this time.
		List<? extends Number>  numberList = GenericMethodsExample.<Number>buildList(1,2.5,4,0.5);
		System.out.println(numberList);
		List<? extends Comparable<?>>  comparableList = GenericMethodsExample.<Comparable<?>>buildList(1,2.5,4,0.5);
		System.out.println(comparableList);
		List<? extends Object>  objectList = GenericMethodsExample.<Object>buildList(1,2.5,4,0.5);
		System.out.println(objectList);
		
		//No Warning this time.
		List<Number>  numberList1 = GenericMethodsExample.<Number>buildList(1,2.5,4,0.5);
		System.out.println(numberList1);
		List<Comparable<?>>  comparableList1 = GenericMethodsExample.<Comparable<?>>buildList(1,2.5,4,0.5);
		System.out.println(comparableList1);
		List<Object>  objectList1 = GenericMethodsExample.<Object>buildList(1,2.5,4,0.5);
		System.out.println(objectList1);
		
	}

}
