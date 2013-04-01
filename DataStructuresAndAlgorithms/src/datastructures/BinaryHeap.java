package datastructures;

import static utils.Utilities.*;

/**
 * For any given K:
 * it's parent will be at k/2
 * and children will be at 2k and 2k+1
 * @author chandanpr
 *
 * @param <Key>
 */
public class BinaryHeap<Key extends Comparable<Key>> {

	private Key[] data;
	private int index;
	
	@SuppressWarnings("unchecked")
	public BinaryHeap(int size){
		this.data = (Key[])new Comparable[size];
	}
	
	private void swim(int k){
		//Find if parent is less than child
		while(k > 1 && less(data[k/2], data[k])){
			exchange(data, k, k/2);
			k = k/2;
		}
	}
	
	private void sink(int k){
		while(2*k <= index){
			int j = 2*k;
			//Find which child is bigger 2k or 2k+1
			if(j < index && less(data[j], data[j+1])){
				j++;
			}
			
			//If item at k is less than item at j sink by exchange
			if(!less(data[k], data[j])){
				break;
			}
			
			exchange(data, k, j);
			k = j;
			
		}
	}
	
	public boolean isEmpty(){
		return index == 0;
	}
	
	public void insert(Key key){
		data[++index] = key;
		swim(index);
		showAll();
	}
	
	/**
	 * Atmost 2logN compares
	 * @return
	 */
	public Key deleteMax(){
		Key max = data[1];
		exchange(data, 1, index--);
		sink(1);
		data[index+1] = null;
		showAll();
		return max;
	}
	
	public void showAll(){
		for(int i=0; i<data.length; i++){
			System.out.print(data[i]+" , ");
		}
		System.out.println();
	}
	
}
