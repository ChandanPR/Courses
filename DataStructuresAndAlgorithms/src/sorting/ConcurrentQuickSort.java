package sorting;

import static utils.Utilities.exchange;
import static utils.Utilities.less;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import utils.StandardRandom;
import utils.Utilities;


public class ConcurrentQuickSort {
	
	private final ForkJoinPool pool = new ForkJoinPool();

	
	private void sort(int[] a){
		long time  = System.currentTimeMillis();
		StandardRandom.shuffle(a);
		System.out.println("COUNT : "+a.length);
		System.out.println("Shuffling: "+(System.currentTimeMillis() - time));
		time  = System.currentTimeMillis();
		try{
			pool.invoke(new SortTask(a,0,a.length-1));
		}finally{
			pool.shutdown();
		}
		System.out.println("Sorting : "+(System.currentTimeMillis() - time));
//		Utilities.show(a);
		assert Utilities.isSorted(a);
	}
	
	private class SortTask extends  RecursiveAction{
		private int[] a;
		private int lo;
		private int hi;

		public SortTask(int[] a, int lo, int hi){
			this.a = a;
			this.lo = lo;
			this.hi = hi;
		}
		
		@Override
		public void compute() {
//			System.out.println("ConcurrentQuickSort.SortTask.compute()");
			sort3Way(a,lo,hi);
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
			invokeAll(new SortTask(a, lo, lt-1),new SortTask(a, gt+1, hi));
		}
		
	}
	
	public static void main(String[] args) {
		int count = 100;
		while(count < Integer.MAX_VALUE){
			int[] a = new int[count];
			for(int i=0; i<count; i++){
				a[i] = count - i;
			}
			ConcurrentQuickSort sort = new ConcurrentQuickSort();
			sort.sort(a);
			System.gc();
			System.gc();
			System.gc();
			System.gc();
			System.gc();
			count = count*10;
		}
	}

}
