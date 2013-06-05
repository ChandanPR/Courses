package sorting;

import static utils.Utilities.*;
import static utils.StandardRandom.*;

public class QuickSort implements Sorter {
	private static final int CUT_OFF = 10;

	public QuickSort() {
	}

	@Override
	public void sort(int[] a) {
		shuffle(a);
		sort(a, true);
	}
	
	public void sort(int[] a,boolean threeway) {
		shuffle(a);
		if(threeway)
			sort3Way(a, 0, a.length - 1);
		else
			sort(a, 0, a.length-1);
	}
	
	private void sort3Way(int[] a, int lo, int hi){
		if(lo >= hi) return;
		int v = a[lo];
		int lt = lo, gt = hi;
		int i = lo;
		while(i <= gt){
			if(less(a[i], v)){
				exchange(a, lt++, i++);
			}else if(less(v, a[i])){
				exchange(a, i, gt--);
			}else{
				i++;
			}
		}
		sort3Way(a, lo, lt-1);
		sort3Way(a, gt+1, hi);
	}

	private void sort(int[] a, int lo, int hi) {
		if (hi <= lo + CUT_OFF - 1) {
			InsertionSort.sort(a, lo, hi);
			return;
		}
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private int partition(int[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		while (true) {
			while (less(a[++i], a[lo])) {
				if (i == hi)
					break;
			}

			while (less(a[lo], a[--j])) {
				if (j == lo)
					break;
			}

			if (i >= j)
				break;
			exchange(a, i, j);
		}
		exchange(a, lo, j);
		return j;
	}

}
