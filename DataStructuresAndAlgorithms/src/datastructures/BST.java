package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Refer http://algs4.cs.princeton.edu/32bst/BST.java.html
 *
 * @param <K>
 * @param <V>
 */
public class BST<K extends Comparable<K>, V> {

	private Node root;

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		return x == null ? 0 : x.count;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public V get(K key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.leftNode;
			else if (cmp > 0)
				x = x.rightNode;
			else
				return x.value;
		}
		return null;
	}

	public void delete(K key) {
		root = delete(root, key);
		assert check();
	}

	private Node delete(Node x, K key) {
		if (x == null)
			return null;

		// SEARCH FOR KEY
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.leftNode = delete(x.leftNode, key);
		else if (cmp > 0)
			x.rightNode = delete(x.rightNode, key);
		else {
			if (x.rightNode == null)
				return x.leftNode;

			Node t = x;
			x = min(t.rightNode);
			x.rightNode = deleteMin(t.rightNode);
			x.leftNode = t.leftNode;
		}
		x.count = 1 + size(x.leftNode) + size(x.rightNode);
		return x;
	}

	public void put(K key, V value) {
		if (value == null) {
			delete(key);
			return;
		}
		root = put(root, key, value);
		assert check();
	}

	private Node put(Node x, K key, V value) {
		if (x == null)
			return new Node(key, value);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.leftNode = put(x.leftNode, key, value);
		else if (cmp > 0)
			x.rightNode = put(x.rightNode, key, value);
		else
			x.value = value;
		x.count = 1 + size(x.leftNode) + size(x.rightNode);
		return x;
	}

	public Iterable<K> traverse() {
		List<K> list = new ArrayList<>();
		traverse(root, list);
		return list;
	}

	private void traverse(Node x, List<K> list) {
		if (x == null)
			return;
		traverse(x.leftNode, list);
		list.add(x.key);
		traverse(x.rightNode, list);
	}

	public int rank(K key) {
		return rank(root, key);
	}

	private int rank(Node x, K key) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(x.leftNode, key);
		else if (cmp > 0)
			return 1 + size(x.leftNode) + rank(x.rightNode, key);
		else
			return size(x);
	}

	/**
	 * Returns the key for the given rank
	 * 
	 * @param k
	 * @return
	 */
	public K select(int k) {
		if (k < 0 || k > size())
			return null;
		Node x = select(root, k);
		return x == null ? null : x.key;
	}

	private Node select(Node x, int k) {
		if (x == null)
			return null;
		int t = size(x.leftNode);
		if (t > k)
			return select(x.leftNode, k);
		if (t < k)
			return select(x.rightNode, t - k - 1);
		return x;
	}

	public K ceil(K key) {
		Node ceil = ceil(root, key);
		return ceil == null ? null : ceil.key;
	}

	private Node ceil(Node x, K key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp > 0)
			return ceil(x.rightNode, key);
		Node temp = ceil(x.leftNode, key);
		return temp != null ? temp : x;
	}

	public K floor(K key) {
		Node floor = floor(root, key);
		return floor == null ? null : floor.key;
	}

	private Node floor(Node x, K key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp < 0)
			floor(x.leftNode, key);
		Node t = floor(x.rightNode, key);
		return t != null ? t : x;
	}

	public K min() {
		Node min = min(root);
		return min == null ? null : min.key;
	}

	private Node min(Node x) {
		if (x == null)
			return null;
		if (x.leftNode == null)
			return x;
		return min(x.leftNode);
	}

	public K max() {
		Node max = max(root);
		return max == null ? null : max.key;
	}

	private Node max(Node x) {
		if (x == null)
			return null;
		if (x.rightNode == null)
			return x;
		return max(x.rightNode);
	}

	public boolean isBST() {
		return isBST(root, null, null);
	}

	private boolean isBST(Node x, K min, K max) {
		if (x == null)
			return true;
		if (min != null && x.key.compareTo(min) <= 0)
			return false;
		if (max != null && x.key.compareTo(max) >= 0)
			return false;
		return isBST(x.leftNode, min, x.key) && isBST(x.rightNode, x.key, max);
	}

	public boolean isSizeConsistent() {
		return isSizeConsistent(root);
	}

	private boolean isSizeConsistent(Node x) {
		if (x == null)
			return true;
		if (x.count != 1 + size(x.leftNode) + size(x.rightNode))
			return false;
		return isSizeConsistent(x.leftNode) && isSizeConsistent(x.rightNode);
	}

	public boolean isRankConsistent() {
		for (int i = 0; i < size(); i++) {
			if (i != rank(select(i)))
				return false;
		}
		for (K key : keys()) {
			if (key.compareTo(select(rank(key))) != 0)
				return false;
		}
		return true;
	}

	public Iterable<K> keys() {
		return keys(min(), max());
	}

	private Iterable<K> keys(K lo, K hi) {
		List<K> keysList = new ArrayList<>();
		keys(root, lo, hi, keysList);
		return keysList;
	}

	private void keys(Node x, K lo, K hi, List<K> list) {
		if (x == null)
			return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0)
			keys(x.leftNode, lo, hi, list);
		else if (cmplo <= 0 && cmphi >= 0)
			list.add(x.key);
		else if (cmphi > 0)
			keys(x.rightNode, lo, hi, list);

	}

	private boolean check() {
		if (!isBST())
			System.out.println("Not in symmetric order");
		if (!isSizeConsistent())
			System.out.println("Subtree counts not consistent");
		if (!isRankConsistent())
			System.out.println("Ranks not consistent");
		return isBST() && isSizeConsistent() && isRankConsistent();
	}

	public boolean contains(K key) {
		return get(key) != null;
	}

	public void deleteMin() {
		if (isEmpty())
			throw new NoSuchElementException();
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.leftNode == null)
			return x.rightNode;
		x.leftNode = deleteMin(x.leftNode);
		x.count = 1 + size(x.leftNode) + size(x.rightNode);
		return x;
	}

	public void deleteMax() {
		if (isEmpty())
			throw new NoSuchElementException();
		root = deleteMax(root);
	}

	private Node deleteMax(Node x) {
		if (x.rightNode == null)
			return x.leftNode;
		x.rightNode = deleteMax(x.rightNode);
		x.count = 1 + size(x.leftNode) + size(x.rightNode);
		return x;
	}

	private class Node {
		private K key;
		private V value;
		private Node leftNode;
		private Node rightNode;
		private int count;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + ", leftNode="
					+ leftNode + ", rightNode=" + rightNode + ", count="
					+ count + "]";
		}
		
		
	}
	
	public static void main(String[] args) {
		BST<Integer, Integer> ints = new BST<>();
		for(int i=0; i<20; i++){
//			ints.put((int)(i*Math.random()), i);
			ints.put(i, i);
		}
		
//		System.out.println(ints.traverse());
//		ints.deleteMax();
//		System.out.println(ints.traverse());
//		ints.deleteMin();
//		System.out.println(ints.traverse());
		System.out.println(ints.isBST());
		System.out.println(ints.check());
		
//		for(int i=0; i<ints.size(); i++){
//			System.out.println(i+":"+ints.select(i));
//		}
	}

}
