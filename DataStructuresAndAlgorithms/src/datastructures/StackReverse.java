package datastructures;

/**
 * This is done using double recursion..
 * 
 * 
 * recursion for popping the bottom element:
 * 		1. Pop the element.
 * 		2. If stack is empty return the element.
 * 		3. Else recursively pop for the other element;
 * 		4. push the originally popped element.
 * 		5. Return the bottom element popped.
 * 
 * recursion for reverse
 * 		1. if stack is empty return;
 * 		2. Pop the element from bottom.
 * 		3. Recursively call reverse.
 * 		4. Push the element to stack
 * 
 * 
 * @author chandanpr
 * @param <E>
 */
public class StackReverse<E> {
	private int size;
	private Node top;

	public void push(E element) {
		Node temp = new Node();
		temp.data = element;
		temp.nextNode = top;
		top = temp;
		size++;
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

	private class Node {
		E data;
		Node nextNode;
	}
	
	public void reverse(){
		if(isEmpty()){
			return;
		}
		E e = popFromBottom();
		reverse();
		push(e);
	}

	private E popFromBottom(){
		E element = pop();
		if(isEmpty()){
			return element;
		}
		
		E otherElement = popFromBottom();
		push(element);
		return otherElement;
	}
	
	
	public static void main(String[] args) {
		int count = 10;
		StackReverse<Integer> ints = new StackReverse<>();
		for (int i = 1; i <= count; i++) {
			ints.push(i);
		}
		for (int i = 1; i <= count; i++) {
			System.out.print(ints.pop() + ",");
		}
		System.out.println();
		for (int i = 1; i <= count; i++) {
			ints.push(i);
		}
		ints.reverse();
		for (int i = 1; i <= count; i++) {
			System.out.print(ints.pop() + ",");
		}
	}

}
