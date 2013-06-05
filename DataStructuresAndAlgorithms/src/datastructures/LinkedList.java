package datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class LinkedList<E extends Comparable<E>> implements Iterable<E> {

	protected Node head;
	private ArrayList<Node> randomElements = new ArrayList<>();

	public void add(E element) {
		head = add(head, element);
	}

	public boolean remove(E element) {
		head = remove(head, element);
		return true;
	}

	public boolean removeInLoop(E element) {
		if (head != null && element.compareTo(head.data) == 0) {
			head = head.nextNode;
			return true;
		}
		Node current = head;
		while (current.nextNode != null) {
			if (element.compareTo(current.nextNode.data) == 0) {
				current.nextNode = current.nextNode.nextNode;
				return true;
			}
			current = current.nextNode;
		}
		return false;
	}
	
	private Node remove(Node node, E element){
		if(node == null){
			return null;
		}
		if(element.compareTo(node.data) == 0){
			Node temp = node.nextNode;
			node.nextNode = null;
			node = temp;
		}else{
			node.nextNode = remove(node.nextNode,element);
		}
		return node;
	}

	private Node add(Node node, E element) {
		if (node == null) {
			node = new Node();
			node.data = element;
		} else {
			node.nextNode = add(node.nextNode, element);
		}
		return node;
	}

	protected class Node {
		E data;
		Node nextNode;

		@Override
		public String toString() {
			return data.toString()
					+ ((nextNode != null) ? nextNode.toString() : "");
		}
	}

	public Iterator<E> iterator() {
		return new Iterator<E>() {
			Node currentNode = head;

			@Override
			public boolean hasNext() {
				return currentNode != null;
			}

			@Override
			public E next() {
				E data = currentNode.data;
				currentNode = currentNode.nextNode;
				return data;
			}

			@Override
			public void remove() {

			}

		};
	}

	/**
	 * Uses Floyd's Cycle Detection algorithm. Also known as Tortoise and Hair
	 * algorithm http://en.wikipedia.org/wiki/Cycle_detection
	 * 
	 * @return
	 */
	public boolean isCircular() {
		Node oneStep = head;
		Node twoStep = head;
		do {
			oneStep = oneStep.nextNode;
			twoStep = twoStep.nextNode;
			twoStep = (twoStep == null) ? twoStep : twoStep.nextNode;
			if (oneStep == null || twoStep == null) {
				return false;
			}
		} while (oneStep != twoStep);
		return true;
	}

	public E findElement(int k) {
		Node near = findKthNodeFromLast(k);
		return near == null ? null : near.data;
	}

	private Node findKthNodeFromLast(int k) {
		Node far = head;
		while (k > 0 && far != null) {
			far = far.nextNode;
			k--;
		}
		//Return null if far is null as size of list < k
		Node near = far == null ? null : head;
		while (far != null) {
			far = far.nextNode;
			near = near.nextNode;
		}
		return near;
	}

	public void reverse() {
		head = reverse(head);
	}

	private Node reverse(Node node) {
		if (node.nextNode == null) {
			return node;
		}
		Node current = node;
		Node next = node.nextNode;
		current.nextNode = null;
		Node lastNode = reverse(next);
		next.nextNode = current;
		return lastNode;
	}

	public void reverseInLoop() {
		Node current, next, loop = null;
		current = head;
		next = head.nextNode;

		while (next != null) {
			current.nextNode = loop;
			loop = current;
			current = next;
			next = next.nextNode;
		}

		head = current;
		current.nextNode = loop;
	}
	
	/**
	 * Returns the tail of this list 
	 * @return
	 */
	private Node getTail() {
		Node current = head;
		while (current.nextNode != null) {
			current = current.nextNode;
		}
		return current;
	}

	/**
	 * Attaches the head to the last node and returns the last node 
	 * @return
	 */
	private Node attachHead() {
		Node tail = getTail();
		tail.nextNode = head;
		return tail;
	}

	/**
	 * Using Reservoir Sampling algorithm to find the random.
	 * http://en.wikipedia.org/wiki/Reservoir_sampling Need to check if this
	 * complies with Reservoir_Sampling What if element is removed?
	 * 
	 * @return E
	 */
	public E findRandomElement() {
		if (head == null) {
			return null;
		}

		int size = randomElements.size();
		if (size == 0) {
			randomElements.add(head);
			return head.data;
		} else {
			Node currenNode = head;
			while (size > 0 && currenNode.nextNode != null) {
				size--;
				currenNode = currenNode.nextNode;
			}
			if (size == 0)
				randomElements.add(currenNode);
		}
		int random = ThreadLocalRandom.current().nextInt(0,
				randomElements.size());
		return randomElements.get(random).data;
	}

	private void appendLastNodesToBegining(int k) {
		Node near = head;
		Node far = head;
		while (k > 0 && far != null) {
			far = far.nextNode;
			k--;
		}

		while (far.nextNode != null) {
			far = far.nextNode;
			near = near.nextNode;
		}
		Node nextNode = near.nextNode;
		near.nextNode = null;
		far.nextNode = head;
		head = nextNode;
	}

	private void combineWithOtherListForAlternateElements(
			LinkedList<E> otherList) {
		if (head == null) {
			head = otherList.head;
		} else if (otherList.head == null) {
			return;
		}

		Node currentNode = head;
		Node otherCurrentNode = otherList.head;
		while (currentNode != null && otherCurrentNode != null) {
			Node currentNextNode = currentNode.nextNode;
			Node otherNextNode = otherCurrentNode.nextNode;
			currentNode.nextNode = otherCurrentNode;
			otherCurrentNode.nextNode = currentNextNode != null ? currentNextNode
					: otherNextNode;
			currentNode = currentNextNode;
			otherCurrentNode = otherNextNode;
		}
	}
	
	public void rotate(){
		Node headNode = head;
		Node tailNode = getTail();
		head = head.nextNode;
		tailNode.nextNode = headNode;
		headNode.nextNode = null;
	}
	
	public void rotate(int n){
		Node headNode = head;
		Node tailNode = getTail();
		while(n > 0){
			head = head.nextNode;
			tailNode.nextNode = headNode;
			headNode.nextNode = null;
			tailNode = headNode;
			headNode = head;
			n--;
		}
	}
	
	public Node getIntersectingNode(LinkedList<E> otherList){
		//PROBLEM YET TO BE ADDRESSED
//		Node lastNode = attachHead();
		return null;
	}

	// ############################# TESTING METHODS
	// ######################################

	public static void main(String[] args) {
		testRotate();
		testIntersection();
		testCombine();
		testRemove(getList());
		testFindRandomElement(getList());
		testFindingKthElement(getList(), 4);
		testAppendingLastNodes(getList());
		testReverse(getList());
		testLinkedListLoop(getList());
	}
	
	private static void testRotate(){
		LinkedList<Integer> ints = getList(5);
		testLinkedListIteration(ints);
		ints.rotate();
		testLinkedListIteration(ints);
		ints.rotate();
		testLinkedListIteration(ints);
		ints.rotate();
		testLinkedListIteration(ints);
		
		LinkedList<Integer> otherInts = getList(5);
		testLinkedListIteration(otherInts);
		otherInts.rotate(3);
		testLinkedListIteration(otherInts);
	}
	
	private static void testIntersection(){
		printBegin("testIntersection");
		//PROBLEM YET TO BE ADDRESSED
//		LinkedList<Integer> ints = getList();
//		LinkedList<Integer> otherInts = getList(5);
//		testLinkedListIteration(ints);
//		testLinkedListIteration(otherInts);
//		System.out.println("Tail : "+otherInts.getTail());
//		System.out.println("3rd Node from last  : "+ints.findKthNodeFromLast(3));
//		otherInts.getTail().nextNode = ints.findKthNodeFromLast(3);
//		System.out.println(ints.getIntersectingNode(otherInts).data);
		printEnd();
		
	}

	private static void testCombine() {
		printBegin("testCombine");
		// OTHER LIST IS EMPTY
		LinkedList<Integer> ints = getList();
		ints.combineWithOtherListForAlternateElements(getList(0));
		testLinkedListIteration(ints);

		// CURRENT LIST IS EMPTY
		ints = getList(0);
		ints.combineWithOtherListForAlternateElements(getList(10));
		testLinkedListIteration(ints);

		// CURRENT LIST BIGGER
		ints = getList();
		ints.combineWithOtherListForAlternateElements(getList(3));
		testLinkedListIteration(ints);

		// OTHER LIST BIGGER
		ints = getList();
		ints.combineWithOtherListForAlternateElements(getList(15));
		testLinkedListIteration(ints);

		// BOTH OF SAME SIZE
		ints = getList();
		ints.combineWithOtherListForAlternateElements(getList(10));
		testLinkedListIteration(ints);

		printEnd();
	}

	private static void testRemove(LinkedList<Integer> ints) {
		printBegin("testRemove");
		testLinkedListIteration(ints);
		ints.remove(100);// REMOVE BEGIN
		testLinkedListIteration(ints);
		ints.remove(105);// REMOVE MIDDLE
		testLinkedListIteration(ints);
		ints.remove(109);// REMOVE END
		testLinkedListIteration(ints);
		ints.remove(1000);// REMOVE NON-EXISTING
		testLinkedListIteration(ints);
		printEnd();
	}

	private static void testFindRandomElement(LinkedList<Integer> ints) {
		printBegin("testFindRandomElement");
		testLinkedListIteration(ints);
		int count = 30;
		while (count > 0) {
			System.out.print(ints.findRandomElement() + ",");
			count--;
		}
		printEnd();
	}

	private static void testAppendingLastNodes(LinkedList<Integer> ints) {
		printBegin("APPEND LAST NODES");
		testLinkedListIteration(ints);
		ints.appendLastNodesToBegining(3);
		testLinkedListIteration(ints);
		printEnd();
	}

	private static void testReverse(LinkedList<Integer> ints) {
		printBegin("REVERSE LIST RECURSION AND LOOP");
		testLinkedListIteration(ints);
		ints.reverse();
		testLinkedListIteration(ints);
		ints.reverseInLoop();
		testLinkedListIteration(ints);
		printEnd();
	}

	private static void testFindingKthElement(LinkedList<Integer> ints, int k) {
		printBegin("FINDING Kth ELEMENT");
		testLinkedListIteration(ints);
		System.out.println("K is  :" + k);
		System.out.println(ints.findElement(k));
		printEnd();
	}

	static void testLinkedListIteration(LinkedList<Integer> ints) {
		for (int i : ints) {
			System.out.print(i + ",");
		}
		System.out.println();
	}

	private static void testLinkedListLoop(LinkedList<Integer> ints) {
		printBegin("FINDING LOOP");
		testLinkedListIteration(ints);
		System.out.println("Is Circular : " + ints.isCircular());
		ints.attachHead();
		System.out.println("Is Circular : " + ints.isCircular());
		printEnd();
	}

	private static void printBegin(String beginString) {
		System.out.println("################################ " + beginString
				+ "  #############################");
	}

	private static void printEnd() {
		System.out.println();
		System.out.println();
		System.out
				.println("###################################################################################");
		System.out.println();
		System.out.println();
	}

	private static LinkedList<Integer> getList() {
		int count = 110;
		LinkedList<Integer> ints = new LinkedList<>();
		for (int i = 100; i < count; i++) {
			ints.add(i);
		}
		return ints;
	}

	private static LinkedList<Integer> getList(int count) {
		LinkedList<Integer> ints = new LinkedList<>();
		for (int i = 0; i < count; i++) {
			ints.add(i);
		}
		return ints;
	}

}
