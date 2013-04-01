package datastructures;

import static utils.Utilities.*;
@SuppressWarnings("unchecked")
public class UnorderedPriorityQueue<Key extends Comparable<Key>>{

	private final Key[] data;
	private int index;
	
	public UnorderedPriorityQueue(int size){
		this.data = (Key[])new Comparable[size];
	}

	public void insert(Key key) {
		data[index++] = key;
	}
	
	public boolean isEmpty(){
		return index == 0;
	}
	
	public Key deleteMin(){
		int min = 0;
		for(int i=1; i<index; i++){
			if(less(data[i], data[min])){
				min = i;
			}
		}
		exchange(data, min, index-1);
		Key key = data[--index];
		data[index] = null;
		return key;
	}
	
	public Key deleteMax(){
		int max = 0;
		for(int i=1; i<index; i++){
			if(less(data[max], data[i])){
				max = i;
			}
		}
		exchange(data, max, index-1);
		Key key = data[--index];
		data[index] = null;
		return key;
	}
	
	public void show(){
		for(int i=0; i<index; i++){
			System.out.print(data[i]+" , ");
		}
		System.out.println();
	}
	
	public void showAll(){
		for(int i=0; i<data.length; i++){
			System.out.print(data[i]+" , ");
		}
		System.out.println();
	}
}
