package sorting;

import static utils.Utilities.*;

public class SelectionSort implements Sorter{

	@Override
	public void sort(int[] a) {
		int length = a.length;
		for(int i=0; i<length; i++){
			int min = i;
			for(int j=i+1; j<length; j++){
				if(less(a[j], a[min])){
					min = j;
				}
			}
			exchange(a, i, min);
		}
	}

}
