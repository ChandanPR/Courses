package sorting;

import utils.Utilities;

/**
 * In a normal binary heap the index starts from 1.
 * For Heap Sort to accomodate, array start index is assumed to be 1.
 * Hence length will be N.
 * The exchanges and less methods implemented locally will take care of this
 * offset.
 * @author chandanpr
 */
public class HeapSort implements Sorter{

	@Override
	public void sort(int[] a) {
		int N = a.length;
		//BUILDING THE MAX HEAP
		for(int k=N/2; k >=1 ; k--){
			sink(a, k, N);
		}
		
		while(N > 1){
			exchange(a, 1, N--);
			sink(a, 1, N);
		}
	}
	
	private void sink(int[] a, int k, int N){
		while(2*k <= N){
			int j = 2*k;
			if(j < N && less(a, j, j+1)) j++;
			if(less(a, j, k)) break;
			exchange(a, j, k);
			k = j;
		}
		
	}
	
	
	/**
	 * 
	 * @param data
	 * @param p
	 * @param q
	 * @return
	 */
	private boolean less(int[] data,int p, int q){
		return data[p-1] - data[q-1] < 0;
	}
	
	/**
	 * Exchanges the elements at index i and j.
	 * @param a
	 * @param i
	 * @param j
	 */
	private void exchange(int[] a, int i, int j){
		int temp = a[i-1];
		a[i-1] = a[j-1];
		a[j-1] = temp;
	}
	
	public static void main(String[] args) {
		int[] intArray = Utilities.getIntArray();
		new HeapSort().sort(intArray);
		Utilities.show(intArray);
		assert Utilities.isSorted(intArray);
	}
	

}
