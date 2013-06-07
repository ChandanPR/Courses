package sorting;

import static utils.Utilities.*;
import utils.StandardRandom;

public class AllSorts {

	private static int[] selectionSort(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i; j < n; j++) {
				if (less(a[j], a[min])) {
					min = j;
				}
			}
			exchange(a, i, min);
		}
		show(a);
		return a;
	}

	private static int[] insertionSort(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = i; j > 0; j--) {
				if (less(a[j], a[j - 1])) {
					exchange(a, j, j - 1);
				} else {
					break;
				}
			}

		}
		show(a);
		return a;
	}

	private static int[] shellSort(int[] a) {
		int n = a.length;
		int h = 1;
		while (h < n / 3) {
			h = 3 * h + 1;
		}

		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h; j -= h) {
					if (less(a[j], a[j - h])) {
						exchange(a, j, j - h);
					}
				}
			}
			h = h / 3;
		}
		show(a);
		return a;
	}

	private static int[] mergeSort(int[] a, boolean copy) {
		int[] aux = new int[a.length];
		if (copy) {
			mergeSort(a, aux, 0, a.length - 1);
		} else {
			mergeSortWithoutCopy(a, aux, 0, a.length - 1);
		}
		show(a);
		return a;
	}

	private static void mergeSort(int[] a, int[] aux, int lo, int hi) {
		if (lo >= hi)
			return;
		int mid = lo + (hi - lo) / 2;
		mergeSort(a, aux, lo, mid);
		mergeSort(a, aux, mid + 1, hi);
		// OPTIMIZATION TO AVOID MERGING
		if (less(a[mid], a[mid + 1])) {
			return;
		}
		merge(a, aux, lo, mid, hi);
	}

	private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);

		for (int i = lo; i <= hi; i++) {
			aux[i] = a[i];
		}

		int i = lo;
		int j = mid + 1;

		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}

		assert isSorted(a, lo, hi);
	}
	
	
	private static void mergeSortWithoutCopy(int[] a, int[] aux, int lo, int hi) {
		if (lo >= hi)
			return;
		int mid = lo + (hi - lo) / 2;
		mergeSortWithoutCopy(aux, a, lo, mid);
		mergeSortWithoutCopy(aux, a, mid + 1, hi);
		// OPTIMIZATION TO AVOID MERGING
		if (less(aux[mid], aux[mid + 1])) {
			return;
		}
		mergeWithoutCopy(aux, a, lo, mid, hi);
	}

	private static void mergeWithoutCopy(int[] a, int[] aux, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);

		int i = lo;
		int j = mid + 1;

		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				aux[k] = a[j++];
			else if (j > hi)
				aux[k] = a[i++];
			else if (less(a[j], a[i]))
				aux[k] = a[j++];
			else
				aux[k] = a[i++];
		}

		assert isSorted(a, lo, hi);
	}

	private static int[] quickSort(int[] a, boolean threeway) {
		StandardRandom.shuffle(a);
		if (threeway) {
			quickSortThreeWay(a, 0, a.length - 1);
		} else {
			quickSort(a, 0, a.length - 1);
		}
		show(a);
		return a;
	}

	private static void quickSort(int[] a, int lo, int hi) {
		if (lo >= hi)
			return;
		int j = partition(a, lo, hi);
		quickSort(a, lo, j - 1);
		quickSort(a, j + 1, hi);

	}

	private static int partition(int[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;

		while (true) {
			while (less(a[++i], a[lo])) {
				if (i == hi)
					break;
			}

			while (less(a[lo], a[--j])) {
				// REDUNDANT CHECK
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

	private static void quickSortThreeWay(int[] a, int lo, int hi) {
		if (lo >= hi)
			return;
		int lt = lo;
		int gt = hi;
		int i = lo;
		int v = a[lo];
		while (i <= gt) {
			int cmp = a[i] - v;
			if (cmp < 0)
				exchange(a, lt++, i++);
			else if (cmp > 0)
				exchange(a, i, gt--);
			else
				i++;
		}
		quickSortThreeWay(a, lo, lt - 1);
		quickSortThreeWay(a, gt + 1, hi);
	}


	public static void main(String[] args) {
		assert isSorted(selectionSort(getIntArray()));
		assert isSorted(insertionSort(getIntArray()));
		assert isSorted(shellSort(getIntArray()));
		assert isSorted(mergeSort(getIntArray(), true));
		// assert isSorted(mergeSort(getIntArray(), false));
		assert isSorted(quickSort(getIntArray(), false));
		assert isSorted(quickSort(getIntArray(), true));
	}

}
