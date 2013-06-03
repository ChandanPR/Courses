package datastructures;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

	private Node head;

	public void add(E element) {
		head = add(head, element);
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

	private class Node {
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
		Node near = head;
		Node far = head;
		while (k > 0 && far != null) {
			far = far.nextNode;
			k--;
		}

		while (far != null) {
			far = far.nextNode;
			near = near.nextNode;
		}
		return near.data;
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

	private void attachHead() {
		Node current = head;
		while (current.nextNode != null) {
			current = current.nextNode;
		}
		current.nextNode = head;
	}

	public static void main(String[] args) {
		int count = 10;
		LinkedList<Integer> ints = new LinkedList<>();
		for (int i = 0; i < count; i++) {
			System.out.print(i + ",");
			ints.add(i);
		}
		ints.reverse();
		testLinkedListIteration(ints);
		ints.reverseInLoop();
		testLinkedListIteration(ints);
		testFindingKthElement(ints);
		testLinkedListIteration(ints);
		testLinkedListLoop(ints);
	}

	private static void testFindingKthElement(LinkedList<Integer> ints) {
		System.out.println();
		System.out.println(ints.findElement(4));
	}

	private static void testLinkedListIteration(LinkedList<Integer> ints) {
		System.out.println();
		for (int i : ints) {
			System.out.print(i + ",");
		}
	}

	private static void testLinkedListLoop(LinkedList<Integer> ints) {
		System.out.println();
		System.out.println("Is Circular : " + ints.isCircular());
		ints.attachHead();
		System.out.println("Is Circular : " + ints.isCircular());
	}

}
