package experiments;

public class BST<E extends Comparable<E>> {
	private static final int LEFT = -1;
	private static final int RIGHT = 1;
	private static final int START = 0;

	private Node<E> root;

	private static class Node<E extends Comparable<E>> {
		E element;
		Node<E> leftNode;
		Node<E> rightNode;

		Node(E element) {
			this.element = element;
		}
		
		@Override
		public String toString() {
			return element.toString();
		}
	}

	public boolean isBST() {
		return isBST(root);
	}

	public boolean isBST(Node<E> n) {
		return isBST(n, n, n, n, START);
	}

	private boolean isBST(Node<E> parent, Node<E> current, Node<E> max,
			Node<E> min, int side) {
		if (current == null || (current.leftNode == null && current.rightNode == null)) {
			return true;
		}

		boolean isLeftLess = less(current.leftNode, current);
		boolean isRightGreater = greater(current.rightNode, current);
		if (isLeftLess && isRightGreater) {
			switch (side) {
			case LEFT:
				boolean isRightNodeLessThanMax = less(current.rightNode, max);
				boolean isCurrentNodeGreaterThanMin = greater(current,min);
				boolean isLeftNodeLessThanMin = less(current.leftNode, min);
				
				System.out.println("___________________________________________________");
				System.out.println("current : "+current);
				System.out.println("current left : "+current.leftNode);
				System.out.println("current right : "+current.rightNode);
				System.out.println("min : "+min);
				System.out.println("max : "+max);
				System.out.println("isRightNodeLessThanMax : "+isRightNodeLessThanMax);
				System.out.println("isCurrentNodeGreaterThanMin : "+isCurrentNodeGreaterThanMin);
				System.out.println("isLeftNodeLessThanMin : "+isLeftNodeLessThanMin);
				System.out.println();System.out.println();System.out.println();System.out.println();
				
				if(isCurrentNodeGreaterThanMin && isLeftNodeLessThanMin){
					return false;
				}
				if (isRightNodeLessThanMax && isLeftNodeLessThanMin) {
					min = less(current, min) ? current : min;
					return isBST(parent, current.leftNode, parent, min, LEFT);
				}
				break;
			case RIGHT:
				
				boolean isLeftNodeGreaterThanMin = greater(current.leftNode,
						min);
				boolean currentNodeLessThanMax = less(current, max);
				boolean isRightNodeGreaterThanMax = less(max,
						current.rightNode);
				System.out.println("___________________________________________________");
				System.out.println("current : "+current);
				System.out.println("current left : "+current.leftNode);
				System.out.println("current right : "+current.rightNode);
				System.out.println("min : "+min);
				System.out.println("max : "+max);
				System.out.println("isLeftNodeGreaterThanMin : "+isLeftNodeGreaterThanMin);
				System.out.println("currentNodeLessThanMax : "+currentNodeLessThanMax);
				System.out.println("isRightNodeGreaterThanMax : "+isRightNodeGreaterThanMax);
				System.out.println();System.out.println();System.out.println();System.out.println();
				
				if (currentNodeLessThanMax && isRightNodeGreaterThanMax) {
					return false;
				}
				if (isLeftNodeGreaterThanMin && isRightNodeGreaterThanMax) {
					max = greater(current, max) ? current : max;
					return isBST(parent, current.rightNode, max, parent, RIGHT);
				}
				break;
			default:
				return isBST(parent, current.leftNode, parent,
						current.leftNode, LEFT)
						&& isBST(parent, current.rightNode, current.rightNode,
								parent, RIGHT);
			}
		}
		return false;
	}

	private boolean less(Node<E> a, Node<E> b) {
		if (a == null) {
			return true;
		}
		return a.element.compareTo(b.element) < 0;
	}

	private boolean greater(Node<E> a, Node<E> b) {
		if (a == null) {
			return true;
		}
		return a.element.compareTo(b.element) > 0;
	}

	private Node<E> createNode(E element) {
		return new Node<>(element);
	}

	private Node<E> addNode(Node<E> node, E element, boolean right) {
		Node<E> tempNode = new Node<>(element);
		if (right) {
			node.rightNode = tempNode;
		} else {
			node.leftNode = tempNode;
		}
		return tempNode;
	}

	public static void main(String[] args) {
//		BST<String> bst = new BST<>();
//		bst.root = bst.createNode("J");
//		bst.addNode(bst.root, "F", false);
//		Node<String> t = bst.addNode(bst.root, "T", true);
//		 Node<String> k = bst.addNode(t, "K", false);
//		 Node<String> l = bst.addNode(k, "L", true);
//		 bst.addNode(l, "W", true);
//
//		System.out.println(bst.isBST());
		
		BST<Integer> bst = new BST<>();
		bst.root = bst.createNode(100);
		Node<Integer> node50 = bst.addNode(bst.root, 50, false);
//		Node<Integer> node20 = bst.addNode(node50, 20, false);
//		Node<Integer> node70 = bst.addNode(node50,70, true);
//		Node<Integer> node10 = bst.addNode(node70,10, false);
		
		
		Node<Integer> node150 = bst.addNode(bst.root, 150, true);
		Node<Integer> node125 = bst.addNode(node150, 125, false);
		
		
		System.out.println(bst.isBST());
	}

}