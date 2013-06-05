package sorting;

import static utils.Utilities.isSorted;
import static utils.Utilities.show;

public class ValidityRunner {
	
	public static void main(String[] args) {
		int COUNT = 10;
		checkValidity(new MergeSort(), COUNT);
		checkValidity(new QuickSort(), COUNT);
		checkValidity(new HeapSort(), COUNT);
		checkValidity(new InsertionSort(), COUNT);
		checkValidity(new BottomUpMergeSort(), COUNT);
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
