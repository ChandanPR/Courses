package datastructures;

public class Stack<E> {
	private int size;
	private Node top;

	public Node push(E element) {
		Node temp = new Node();
		temp.data = element;
		temp.nextNode = top;
		top = temp;
		size++;
		return top;
	}

	public E pop() {
		Node temp = top;
		top = top.nextNode;
		size--;
		return temp.data;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public int getSize() {
		return size;
	}
	
	public void reverse(){
		if(isEmpty()){
			return;
		}
		E e = popFromBottom();
		reverse();
		push(e);
	}

	public E popFromBottom(){
		E element = pop();
		if(isEmpty()){
			return element;
		}
		
		E otherElement = popFromBottom();
		push(element);
		return otherElement;
	}

	private class Node {
		E data;
		Node nextNode;
	}

	public static void main(String[] args) {
		int count = 10;
		Stack<Integer> ints = new Stack<>();
		for (int i = 1; i <= count; i++) {
			ints.push(i);
		}
		for (int i = 1; i <= count; i++) {
			System.out.print(ints.pop() + ",");
		}
	}

}
