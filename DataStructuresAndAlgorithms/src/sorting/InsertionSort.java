package sorting;

import static utils.Utilities.*;

public class InsertionSort implements Sorter{

	@Override
	public void sort(int[] a) {
		int length = a.length;
		for(int i=0; i< length ; i++){
			for(int j = i; j > 0; j--){
				if(less(a[j],a[j-1])){
					exchange(a, j, j-1);
				}
			}
		}
		
	}
	
	public static void sort(int[] a, int lo, int hi) {
		for(int i=lo; i<= hi ; i++){
			for(int j = i; j > lo; j--){
				if(less(a[j],a[j-1])){
					exchange(a, j, j-1);
				}
			}
		}
		
	}


}
