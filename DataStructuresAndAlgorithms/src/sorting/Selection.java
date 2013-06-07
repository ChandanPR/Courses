package sorting;

import static utils.Utilities.*;
import utils.StandardRandom;

/**
 * Given an array and the kth element to be found out this class
 * finds the kth element using partition of quick sort.
 * @author chandanpr
 *
 */
public class Selection {
	
	private static int select(int[] a, int k){
		StandardRandom.shuffle(a);
		int lo = 0;
		int hi = a.length - 1;
		while(hi > lo){
			int j = partition(a, lo, hi);
			
			if(j < k ) lo = j+1;
			else if( j > k) hi = j-1;
			else return a[k];
		}
		
		return a[k];
	}
	
	private static int partition(int[] a, int lo, int hi){
		int i = lo;
		int j = hi+1;
		
		while(i <= hi){
			while(less(a[++i], a[lo])){
				if(i == hi) break;
			}
			
			while(less(a[lo], a[--j])){
				if(j == lo) break;
			}
			
			if(i >= j) break;
			exchange(a, i, j);
		}
		exchange(a, lo, j);
		return j;
	}
	
	/**
	 * Easiest way to verify is select from 0 to n.
	 * Check if the array is sorted.
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = getIntArray();
		int[] tops = new int[a.length];
		for(int i=0; i<a.length; i++){
			tops[i] = select(a, i);
		}
		assert isSorted(tops);
		show(tops);
	}

}
