package datastructures;

public class QueueUsingSingleStack<E> extends Stack<E>{
	
	public void enqueue(E element){
		push(element);
	}
	
	public E dequeue(){
		return popFromBottom();
	}
	
	
	public static void main(String[] args) {
		int count = 10;
		QueueUsingSingleStack<Integer> ints = new QueueUsingSingleStack<>();
		for (int i = 1; i <= count; i++) {
			ints.enqueue(i);
		}
		for (int i = 1; i <= count; i++) {
			System.out.print(ints.dequeue() + ",");
		}
	}
}
