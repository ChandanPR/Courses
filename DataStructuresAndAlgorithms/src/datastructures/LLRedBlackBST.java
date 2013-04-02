package datastructures;

import datastructures.BinarySearchTree.Node;

@SuppressWarnings("all")
public class LLRedBlackBST<Key extends Comparable<Key>, Value> extends
		BinarySearchTree<Key, Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private class RBNode extends BinarySearchTree.Node {
		boolean color = RED;
		public RBNode(Comparable key, Object value) {
			super(key, value);
		}

	}
	
	private boolean isRed(RBNode x){
		return x != null && x.color == RED;
	}

	@Override
	protected Node createNode(Comparable key, Object value) {
		return new RBNode(key, value);
	}
	
	@Override
	public void put(Key key, Value value) {
		super.put(key, value);
		((RBNode)root).color = BLACK;
	}
	
	@Override
	protected Node put(Node x, Key key, Value value){
		RBNode h = (RBNode)super.put(x, key, value);
		
		if(isRed((RBNode)h.right) && !isRed((RBNode)h.left)) {
			h = rotateLeft(h);
		}
		
		if(isRed((RBNode)h.left) && !isRed((RBNode)h.left.left)) {
			h = rotateRight(h);
		}
		
		if(isRed((RBNode)h.left) && !isRed((RBNode)h.right)) {
			flipColors(h);
		}
		
		return h;
	}
	
	
	private RBNode rotateLeft(RBNode h){
		assert isRed((RBNode)h.right);
		RBNode x = (RBNode)h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	private RBNode rotateRight(RBNode h){
		assert isRed((RBNode)h.left);
		RBNode x = (RBNode)h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	private void flipColors(RBNode h){
		assert !isRed((RBNode)h);
		
		RBNode right = (RBNode)h.right;
		RBNode left = (RBNode)h.left;
		assert isRed(right);
		assert isRed(left);
		
		h.color = RED;
		right.color = BLACK;
		left.color = BLACK;
	}
	
}
