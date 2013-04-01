package sorting;

import static utils.Utilities.exchange;
import static utils.Utilities.less;

public class HeapSort implements Sorter{

	@Override
	public void sort(int[] a) {
		int n = a.length-1;
		//Build a heap order using bottom-up method
		for(int k = n/2; k >= 0; k--){
			sink(a, k, n);
		}
		
		while(n > 0){
			exchange(a, 0, n--);
			sink(a, 0, n);
		}
		
	}
	
	private void sink(int[] a, int k, int n){
		while(2*k <= n){
			int j = 2*k;
			//Find which child is bigger 2k or 2k+1
			if(j < n && less(a[j], a[j+1])){
				j++;
			}
			
			//If item at k is less than item at j sink by exchange
			if(!less(a[k], a[j])){
				break;
			}
			
			exchange(a, k, j);
			k = j;
			
		}
	}

}
