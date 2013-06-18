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

		// WE HAVE REACHED THE END OF THE TREE - means no failed conditions.
		// SO THE TREE IS BST
		if (current == null
				|| (current.leftNode == null && current.rightNode == null)) {
			return true;
		}

		// FIRST CHECK IF THE CURRENT NODE WITH IT'S DIRECT CHILD FORMS BST
		boolean isLeftLess = less(current.leftNode, current);
		boolean isRightGreater = greater(current.rightNode, current);

		/**
		 * System.out
		 * .println("___________________________________________________");
		 * System.out.println("side : " + side); System.out.println("current : "
		 * + current); System.out.println("current left : " + current.leftNode);
		 * System.out.println("current right : " + current.rightNode);
		 * System.out.println("min : " + min); System.out.println("max : " +
		 * max); System.out.println("isLeftLess : " + isLeftLess);
		 * System.out.println("isRightGreater : " + isRightGreater);
		 */

		if (isLeftLess && isRightGreater) {
			switch (side) {
			case LEFT:

				/**
				 * Invariants for the left side of the tree
				 * 
				 * max: always the given parent/root node min: can change over
				 * iteration and refers to current minimum
				 * 
				 * Invariants to be maintained during traversal 1. Any node
				 * should be less than max(parent/root) 2. The right child will
				 * be always greater than min. 3. The left child will be less
				 * than min. i) RIGHT-LEFT case: In this case the current node
				 * will be greater than min and hence the left child of this
				 * should be greater than min.
				 * 
				 * Invariants will be broken when
				 * 
				 * 1. Any node is greater than max. 2. The right child is less
				 * than min. 3. The left child is greater than min. i)
				 * RIGHT-LEFT case: The left child is less than min.
				 * 
				 */
				boolean isRightNodeLessThanMax = less(current.rightNode, max);
				boolean isCurrentNodeGreaterThanMin = current != min
						&& greater(current, min);
				boolean isLeftNodeLessThanMin = less(current.leftNode, min);

				/**
				 * System.out.println("isRightNodeLessThanMax : " +
				 * isRightNodeLessThanMax);
				 * System.out.println("isCurrentNodeGreaterThanMin : " +
				 * isCurrentNodeGreaterThanMin);
				 * System.out.println("isLeftNodeLessThanMin : " +
				 * isLeftNodeLessThanMin); System.out.println();
				 * System.out.println(); System.out.println();
				 * System.out.println();
				 */

				if (isRightNodeLessThanMax
						&& (isCurrentNodeGreaterThanMin != isLeftNodeLessThanMin)) {
					// All three variants satisfied for the current node.
					// Proceed further down in the tree.
					min = less(current, min) ? current : min;
					return isBST(parent, current.leftNode, parent, min, LEFT)
							&& isBST(parent, current.rightNode, parent, min,
									LEFT);
				}
				break;
			case RIGHT:

				/**
				 * Invariants for the right side of the tree
				 * 
				 * min: always the given parent/root node max: can change over
				 * iteration and refers to current maximum
				 * 
				 * Invariants to be maintained during traversal 1. Any node
				 * should be greater than min(parent/root) 2. The left child
				 * will be always lesser than max. 3. The right child will be
				 * greater than max. i) LEFT-RIGHT case: In this case the
				 * current node will be less than max and hence the right child
				 * of this should be lesser than max.
				 * 
				 * Invariants will be broken when
				 * 
				 * 1. Any node is lesser than min(parent/root). 2. The left
				 * child is greater than max. 3. The right child is greater than
				 * max. i) LEFT-RIGHT case: The right child is less than max.
				 * 
				 */

				boolean isLeftNodeGreaterThanMin = greater(current.leftNode,
						min);
				boolean currentNodeLessThanMax = current != max
						&& less(current, max);
				boolean isRightNodeGreaterThanMax = greater(current.rightNode,
						max);

				/**
				 * System.out.println("isLeftNodeGreaterThanMin : " +
				 * isLeftNodeGreaterThanMin);
				 * System.out.println("currentNodeLessThanMax : " +
				 * currentNodeLessThanMax);
				 * System.out.println("isRightNodeGreaterThanMax : " +
				 * isRightNodeGreaterThanMax); System.out.println();
				 * System.out.println(); System.out.println();
				 * System.out.println();
				 */

				if (isLeftNodeGreaterThanMin
						&& (currentNodeLessThanMax != isRightNodeGreaterThanMax)) {
					// All three variants satisfied for the current node.
					// Proceed further down in the tree.
					max = greater(current, max) ? current : max;
					return isBST(parent, current.rightNode, max, parent, RIGHT)
							&& isBST(parent, current.leftNode, max, parent,
									RIGHT);
				}
				break;
			default:
				// STARTING: ROOT with it's immediate children is a BST.
				// Proceed further to check if the child nodes satisfy the
				// invariants.
				return isBST(parent, current.leftNode, parent,
						current.leftNode, LEFT)
						&& isBST(parent, current.rightNode, current.rightNode,
								parent, RIGHT);
			}
		}
		return false;
	}

	// ----------------------- HELPER APIS------------------------------//
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
		BST<String> bst = new BST<>();
		bst.root = bst.createNode("J");
		Node<String> f = bst.addNode(bst.root, "F", false);
		Node<String> t = bst.addNode(bst.root, "T", true);
		Node<String> k = bst.addNode(t, "K", false);
		Node<String> l = bst.addNode(k, "L", true);
		Node<String> i = bst.addNode(f, "I", true);
		Node<String> g = bst.addNode(i, "G", false);
		Node<String> b = bst.addNode(g, "b", false);
		bst.addNode(l, "M", true);

		System.out.println("bst with strings : " + bst.isBST());

		// BST<Integer> bst1 = new BST<>();
		// bst1.root = bst1.createNode(100);
		// Node<Integer> node50 = bst1.addNode(bst1.root, 50, false);
		// Node<Integer> node20 = bst1.addNode(node50, 20, false);
		// Node<Integer> node70 = bst1.addNode(node50,70, true);
		// Node<Integer> node10 = bst.addNode(node70,10, false);
		// Node<Integer> node120 = bst1.addNode(node70,120, true);

		// Node<Integer> node150 = bst1.addNode(bst1.root, 150, true);
		// Node<Integer> node125 = bst1.addNode(node150, 125, false);
		// Node<Integer> node175 = bst1.addNode(node150, 175, true);
		// Node<Integer> node90 = bst.addNode(node125, 90, false);
		// Node<Integer> node200 = bst.addNode(node125, 200, true);
		// Node<Integer> node90 = bst.addNode(node175, 90, false);
		// Node<Integer> node200 = bst.addNode(node175, 200, true);

		// System.out.println("bst with ints:" +bst1.isBST());
		// System.out.println("is Node 150 BST ? :" +bst1.isBST(node150));
	}

}
