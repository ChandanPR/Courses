package sorting;

import static utils.Utilities.isSorted;
import static utils.Utilities.show;

public class ValidityRunner {
	
	public static void main(String[] args) {
//		checkValidity(new MergeSort(), 10000);
//		checkValidity(new QuickSort(), 10000);
		checkValidity(new HeapSort(), 10000);
	}
	
	
	private static void checkValidity(Sorter sorter,int n){
		int[] a = new int[n];
		for(int i=0; i<n; i++){
			a[i] = n-i;
		}
		show(a);
		sorter.sort(a);
		assert isSorted(a);
		show(a);
	}

}
