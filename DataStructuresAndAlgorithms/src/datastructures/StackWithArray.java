package datastructures;

public class StackWithArray<E> {
	
	public static final int DEFAULT = 10;
	
	private Object[] data;
	
	private int index;
	
	public StackWithArray(){
		data = new Object[DEFAULT];
	}
	
	public void push(E element){
		increaseSize();
		data[index++] = element;
	}
	
	private void increaseSize(){
		if(index >= data.length*0.75){
			System.out.println("StackWithArray.increaseSize()");
			Object[] temp = new Object[data.length*2];
			System.arraycopy(data, 0, temp, 0, index);
			data = temp;
		}
	}
	
	private void decreaseSize(){
		if(index <= data.length*.25 && data.length/2 > DEFAULT){
			System.out.println("StackWithArray.decreaseSize()");
			Object[] temp = new Object[data.length/2];
			System.arraycopy(data, 0, temp, 0, index);
			data = temp;
		}
	}
	
	public E pop(){
		E element = (E)data[--index];
		data[index] = null;
		decreaseSize();
		return element;
	}
	
	public boolean isEmpty(){
		return index == 0;
	}
	
	public static void main(String[] args) {
		int count = 20;
		StackWithArray<Integer> ints = new StackWithArray<>();
		for (int i = 1; i <= count; i++) {
			ints.push(i);
		}
		for (int i = 1; i <= count; i++) {
			System.out.print(ints.pop() + ",");
		}
	}

}
