package datastructures;

public class SortedLinkedList<E extends Comparable<E>> extends LinkedList<E> {

	@Override
	public void add(E element) {
		if (head != null && element.compareTo(head.data) < 0) {
			Node temp = add(null, element);
			temp.nextNode = head;
			head = temp;
		} else {
			head = add(head, element);
		}
	}

	private Node add(Node node, E element) {
		if (node == null) {
			node = new Node();
			node.data = element;
		} else {
			if (node.nextNode != null) {
				boolean priorLess = node.data.compareTo(element) <= 0;
				boolean laterGreater = node.nextNode.data.compareTo(element) >= 0;
				if (priorLess && laterGreater) {
					Node temp = add(null, element);
					Node nextNode = node.nextNode;
					node.nextNode = temp;
					temp.nextNode = nextNode;
					return node;
				}
			}
			node.nextNode = add(node.nextNode, element);

		}
		return node;
	}

	public static void main(String[] args) {
		int count = 5;
		SortedLinkedList<Integer> ints = new SortedLinkedList<>();
		for (int i = 0; i < count; i++) {
			int value = (int) (i * 100 * Math.random());
			System.out.print(value + ",");
			ints.add(value);
		}
		System.out.println();
		LinkedList.testLinkedListIteration(ints);
	}

}
