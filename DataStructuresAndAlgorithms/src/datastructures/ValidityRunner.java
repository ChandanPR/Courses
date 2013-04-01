package datastructures;

import java.util.Random;

public class ValidityRunner {
	
	public static void main(String[] args) {
		checkUnorderedPriorityQueue();
		checkOrderedPriorityQueue();
		checkBinaryHeap();
		checkBinarySearchTree();
		checkBSTDelete();
	}

	private static void checkBSTDelete() {
		BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
		bst.put(9, 9);
		bst.put(5, 9);
		bst.put(11, 9);
		bst.put(4, 9);
		bst.put(8, 9);
		bst.put(10, 9);
		bst.put(16, 9);
		bst.put(12, 9);
		bst.put(20, 9);
		bst.put(19, 9);
		bst.put(18, 9);
		System.out.println(bst);
		bst.delete(16);
		System.out.println(bst);
	}

	private static void checkBinarySearchTree() {
		BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
		Random random = new Random((int)(System.nanoTime()*1e-08));
		for(int i=0; i<5; i++){
			int nextInt = random.nextInt(100);
			System.out.println(nextInt);
			bst.put(nextInt, i);
		}
		System.out.println(bst);
//		System.out.println(bst.maximum());
//		System.out.println(bst.minimum());
//		bst.deleteMin();
//		bst.deleteMin();
//		System.out.println(bst);
		bst.deleteMax();
		System.out.println(bst);
		
//		for(Object o: bst.keys()){
//			System.out.print(o+" ");
//		}
	}

	private static void checkBinaryHeap() {
		BinaryHeap<Integer> heap = new BinaryHeap<>(10);
		heap.insert(5);
		heap.insert(10);
		heap.insert(0);
		heap.insert(12);
		heap.deleteMax();
		heap.deleteMax();
	}

	private static void checkOrderedPriorityQueue() {
		OrderedPriorityQueue<Integer> intPQ = new OrderedPriorityQueue<>(10);
		for(int i=10; i>0; i--){
			intPQ.insert(i);
		}
		
		System.out.println(intPQ.deleteMax());
		System.out.println(intPQ.deleteMin());
		System.out.println(intPQ.deleteMin());
		System.out.println(intPQ.deleteMin());
		System.out.println(intPQ.deleteMax());
		System.out.println(intPQ.deleteMax());
		System.out.println(intPQ.deleteMax());
		System.out.println(intPQ.deleteMax());
		intPQ.showAll();
	}

	private static void checkUnorderedPriorityQueue() {
		UnorderedPriorityQueue<Integer> intPQ = new UnorderedPriorityQueue<>(10);
		for(int i=0; i<10; i++){
			intPQ.insert(i);
		}
		
		System.out.println(intPQ.deleteMax());
		intPQ.show();
		intPQ.showAll();
		
		System.out.println(intPQ.deleteMin());
		intPQ.show();
		intPQ.showAll();
	}

}
