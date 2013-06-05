package sorting;

import static utils.Utilities.isSorted;
import static utils.Utilities.less;

public class BottomUpMergeSort implements Sorter{

	@Override
	public void sort(int[] a) {
		int length = a.length;
		int[] aux = new int[length];
		for(int size = 1; size < length; size += size){
			for(int lo = 0; lo < length - size; lo += 2*size){
				merge(a,aux,lo,lo+size-1,Math.min(lo+2*size-1, length-1));
			}
		}
		
	}
	
	private void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);
		for (int i = lo; i <= hi; i++) {
			aux[i] = a[i];
		}

		int i = lo;
		int j = mid + 1;

		for (int k = lo; k <= hi; k++) {
			if (i > mid)	a[k] = aux[j++];
			else if (j > hi)	a[k] = aux[i++];
			else if (less(aux[j], aux[i]))	a[k] = aux[j++];
			else	a[k] = aux[i++];
		}
		assert isSorted(a, lo, hi);
	}

}
