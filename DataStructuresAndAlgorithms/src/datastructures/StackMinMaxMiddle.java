package datastructures;

/**
 * The purpose of this is to find out the min in stack with O(1) and space also
 * O(1) The Push and Pop operation should be also O(1)
 * 
 * 
 * The solution is in each maintain the min and max element at the time of
 * insertion. This can be done by checking the value of new element with the
 * min/max. If element is less/greater than min/max, update min/max and update
 * new node with min/max. Else update new node with min/max.
 * 
 * @author chandanpr
 * 
 * @param <E>
 */
public class StackMinMaxMiddle<E extends Comparable<E>> {
	private int size;
	private Node top;
	private E minData;
	private E maxData;
	private E middleData;

	public void push(E element) {
		size++;
		Node temp = new Node();
		temp.data = element;
		temp.nextNode = top;
		minData = minData == null || element.compareTo(minData) < 0 ? element
				: minData;
		maxData = maxData == null || element.compareTo(maxData) > 0 ? element
				: maxData;
		temp.minData = minData;
		temp.maxData = maxData;
		top = temp;
		
		//IS IT POSSIBLE TO UPDATE MIDDLE DATA IN O(1) DURING PUSH?????
		if(middleData == null){
			middleData = element;
		}else{
			
		}
		temp.middleData = middleData;
	}

	public E getElement() {
		return top.data;
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

	public E getMinimum() {
		return top.minData;
	}

	public E getMaximum() {
		return top.maxData;
	}

	public E getMiddleData() {
		return top.middleData;
	}

	public E getData() {
		return top.data;
	}

	private class Node {
		E data;
		E minData;
		E maxData;
		E middleData;
		Node nextNode;
	}

	public static void main(String[] args) {
		int count = 5;
		StackMinMaxMiddle<Integer> ints = new StackMinMaxMiddle<>();
		for (int i = 1; i <= count; i++) {
			int element = (int) (i * 100 * Math.random());
			ints.push(element);
			System.out.println("[Elelment : " + ints.getData() + ":"
					+ ints.getMinimum() + "-" + ints.getMiddleData() + "-"
					+ ints.getMaximum() + "]");
		}
		System.out
				.println("____________________________________________________");
		for (int i = 1; i <= count; i++) {
			System.out.println("[Elelment : " + ints.getData() + ":"
					+ ints.getMinimum() + "-" + ints.getMiddleData() + "-"
					+ ints.getMaximum() + "]");
			ints.pop();
		}
	}

}
