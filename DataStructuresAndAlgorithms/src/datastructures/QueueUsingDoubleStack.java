package datastructures;

public class QueueUsingDoubleStack<E> {
	
	private Stack<E> inputStack = new Stack<>();
	private Stack<E> outputStack = new Stack<>();
	
	public void enqueue(E element){
		inputStack.push(element);
	}
	
	public E dequeue(){
		if(outputStack.isEmpty()){
			while(!inputStack.isEmpty()){
				outputStack.push(inputStack.pop());
			}
		}
		return outputStack.pop();
	}
	
	public static void main(String[] args) {
		int count = 10;
		QueueUsingDoubleStack<Integer> ints = new QueueUsingDoubleStack<>();
		for (int i = 1; i <= count; i++) {
			ints.enqueue(i);
		}
		for (int i = 1; i <= count; i++) {
			System.out.print(ints.dequeue() + ",");
		}
	}

}
