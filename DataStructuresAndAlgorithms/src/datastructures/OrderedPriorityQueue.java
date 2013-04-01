package datastructures;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class OrderedPriorityQueue<Key extends Comparable<Key>>{

	private final Key[] data;
	private int endIndex;
	private int startIndex;
	private boolean resetNeeded;
	
	public OrderedPriorityQueue(int size){
		this.data = (Key[])new Comparable[size];
	}
	
	private void reset(){
		if(!resetNeeded) return;
		int length = data.length;
		if(endIndex - startIndex <= length/2){
			System.out.println("Reset");
			showAll();
			Key[] temp = (Key[])new Comparable[length];
			System.arraycopy(data, 0, temp, 0, length);
			Arrays.fill(data, null);
			System.arraycopy(temp, startIndex, data, 0, endIndex-startIndex);
			showAll();
			endIndex -= startIndex;
			startIndex = 0;
			System.out.println("Reset");
			resetNeeded = false;
		}
	}

	public void insert(Key key) {
		data[endIndex++] = key;
		if(endIndex > data.length/2){
			resetNeeded = true;
		}
		Arrays.sort(data,startIndex,endIndex);
	}
	
	public boolean isEmpty(){
		return endIndex <= startIndex;
	}
	
	public Key deleteMin(){
		Key key = data[startIndex];
		data[startIndex++] = null;
		reset();
		return key;
	}
	
	public Key deleteMax(){
		Key key = data[--endIndex];
		data[endIndex] = null;
		reset();
		return key;
	}
	
	public void show(){
		for(int i=startIndex; i<endIndex; i++){
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
