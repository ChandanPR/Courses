package sorting;

import static utils.Utilities.isSorted;
import static utils.Utilities.less;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ConcurrentMergeSort implements Sorter{

	private static final int MERGE_SORT_CUT_OFF = 7;
	private final ForkJoinPool pool = new ForkJoinPool();
	
	@Override
	public void sort(int[] a) {
		int length = a.length;
		int[] aux = new int[length];
		try{
			pool.invoke(new SortTask(a,aux,0,length-1));
		}finally{
			pool.shutdown();
		}
	}
	
	private class SortTask extends RecursiveAction{
		
		int[] a;
		int[] aux;
		int lo;
		int hi;
		public SortTask(int[] a, int[] aux, int lo, int hi) {
			this.a = a;
			this.aux = aux;
			this.lo = lo;
			this.hi = hi;
		}
		@Override
		protected void compute() {
			if(hi <= lo+MERGE_SORT_CUT_OFF-1) {
//				dumpDetails(lo+":"+hi+"Insertion Sort");
				InsertionSort.sort(a, lo, hi);
				return;
			}
			
			int mid = lo+(hi-lo)/2;
			invokeAll(new SortTask(a,aux,lo,mid),new SortTask(a,aux,mid+1,hi));
//			dumpDetails(lo+":"+hi+"Merge");
			merge(a, aux, lo, mid, hi);
			return;
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
		
		private void dumpDetails(String name){
			System.out.println(name+":"+Thread.currentThread().getName());
		}
		
	}

}
