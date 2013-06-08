package search;

import static utils.Utilities.*;
import sorting.HeapSort;

/**
 * 
 * @author chandanpr
 */
public class BinarySearch {
	
	private static boolean search(int[] a, int element){
		System.out.println("Finding Element : "+element);
		assert isSorted(a);
		return binarySearch(a,element) != -1;
	}
	
	private static int binarySearch(int[] a,int element){
		int lo = 0;
		int hi = a.length-1;
		while(lo <= hi){
			int mid = lo+(hi - lo)/2;
			int cmp = element - a[mid];
			if(cmp < 0) hi = mid -1;
			else if(cmp > 0) lo = mid+1;
			else return mid;
			
		}
		return -1;
	}
	
	/**
	 * rank determines how many elements are lesser than given element.
	 * @param a
	 * @param element
	 * @return
	 */
	private static int rank(int[] a,int element){
		System.out.print("Rank of element "+element+" is ");
		int lo = 0;
		int hi = a.length-1;
		while(lo <= hi){
			int mid = lo+(hi - lo)/2;
			int cmp = element - a[mid];
			if(cmp < 0) hi = mid -1;
			else if(cmp > 0) lo = mid+1;
			else return mid;
			
		}
		return lo;
	}
	
	
	public static void main(String[] args) {
		int[] intArray = getIntArray();
		new HeapSort().sort(intArray);
		show(intArray);
		assert search(intArray, getRandomNumberInArray(intArray));
		assert search(intArray, getRandomNumberInArray(intArray));
		assert search(intArray, getRandomNumberInArray(intArray));
		assert search(intArray, getRandomNumberInArray(intArray));
		
		System.out.println(rank(intArray, getRandomNumberInArray(intArray)));
		System.out.println(rank(intArray, getRandomNumberInArray(intArray)));
		System.out.println(rank(intArray, getRandomNumberInArray(intArray)));
		System.out.println(rank(intArray, getRandomNumberInArray(intArray)));
		System.out.println(rank(intArray, getRandomNumberInArray(intArray)));
		
	}

	private static int getRandomNumberInArray(int[] intArray) {
		return intArray[(int)(Math.random()*intArray.length)];
	}

}
