package sorting;

import static utils.Utilities.*;

/**
 * One more trick to avoid copying (doesn't save space) is switch the roles of aux and input array a 
 * @author chandanpr
 */

public class MergeSort implements Sorter {

	private static final int INSERTION_SORT_CUT_OFF = 7;
	@Override
	public void sort(int[] a) {
		int length = a.length;
		int[] aux = new int[length];
		sort(a,aux,0,length-1);
	}
	
	public void sort(int[] a, int[] aux, int lo, int hi){
		if(hi <= lo+INSERTION_SORT_CUT_OFF-1) {
			InsertionSort.sort(a, lo, hi);
			return;
		}
		
		int mid = lo+(hi-lo)/2;
		sort(a,aux,lo,mid);
		sort(a,aux,mid+1,hi);
		//OPTIMIZATION TO AVOID MERGING
		if(less(a[mid], a[mid+1])){
			return;
		}
		merge(a, aux, lo, mid, hi);
	}

	private void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);
		for (int i = lo; i <= hi; i++) {
			aux[i] = a[i];
		}

		int i = lo;
		int j = mid + 1;

		//BE CAREFUL: aux WILL BE INPUT ARRAY AND a WILL BE FINAL ARRAY
		for (int k = lo; k <= hi; k++) {
			if (i > mid)	a[k] = aux[j++];
			else if (j > hi)	a[k] = aux[i++];
			else if (less(aux[j], aux[i]))	a[k] = aux[j++];
			else	a[k] = aux[i++];
		}
		assert isSorted(a, lo, hi);
	}

}
