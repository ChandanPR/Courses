package language;

import java.util.Iterator;

public class MyIterableArray<E> implements Iterable<E>{
	
	Object[] elements;
	int counter = 0;
	
	public MyIterableArray(int size){
		elements = new Object[size];
	}
	
	public boolean add(E e){
		if(counter < elements.length){
			elements[counter++] = e;
			return true;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			int i = 0;
			@Override
			public boolean hasNext() {
				return i < elements.length;
			}

			@Override
			public E next() {
				return (E)elements[i++];
			}

			@Override
			public void remove() {
			}
		};
	}
	
	public static void main(String[] args) {
		int size = 10;
		MyIterableArray<Integer> ints = new MyIterableArray<>(size);
		for(int i=0; i<size; i++){
			ints.add(i);
		}
		
		for(int i : ints){
			System.out.println(i);
		}
	}

}
