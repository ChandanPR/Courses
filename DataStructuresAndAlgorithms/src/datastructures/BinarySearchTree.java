package datastructures;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
	
	private Node root;
	
	public Value get(Key key){
		Node x = root;
		while(x != null){
			int compare = key.compareTo(x.key);
			if(compare < 0) x = x.left;
			else if (compare > 0) x = x.right;
			else return x.value;
		}
		return null;
	}
	
	public void put(Key key, Value value){
		root = put(root,key,value);
	}
	
	private Node put(Node x, Key key, Value value){
		if(x == null) return new Node(key,value);
		
		int compare = key.compareTo(x.key);
		
		if(compare < 0) x.left = put(x.left,key,value);
		else if(compare > 0) x.right = put(x.right,key,value);
		else x.value = value;
		x.count = 1+size(x.left)+size(x.right);
		return x;
	}
	
	private class Node{
		private Key key;
		private Value value;
		private Node left, right;
		private int count;
		
		public Node(Key key, Value value){
			this.key = key;
			this.value = value;
		}
		
		@Override
		public String toString() {
			return "["+getKey(left)+"-"+key+"-"+getKey(right)+"]";
		}
		
		private String getKey(Node n){
			if(n == null) return "#";
			if(n.left == null && n.right == null){
				return n.key.toString();
			}
			return n.toString();
		}
	}
	
	//Other Useful Methods
	
	public Key minimum(){
		return minimum(root);
	}
	
	private Key minimum(Node x){
		if(x == null) return null;
		Key min = minimum(x.left);
		return min == null ? x.key : min;
	}
	
	public Key maximum(){
		return maximum(root);
	}
	
	private Key maximum(Node x){
		if(x == null) return null;
		Key max = maximum(x.right);
		return max == null ? x.key : max;
	}
	
	public Key floor(Key key){
		Node x =  floor(key, root);
		if(x != null){
			return x.key;
		}
		return null;
	}
	
	private Node floor(Key key, Node x){
		if(x == null) return null;
		int compare = key.compareTo(x.key);
		
		if(compare == 0) return x;
		
		if(compare < 0) return floor(key, x.left);
		Node tempNode = floor(key, x.right);
		return tempNode == null ? x : tempNode;
	}
	
	@Override
	public String toString() {
		return root.toString();
	}
	
	public int size(){
		return size(root);
	}
	
	private int size(Node x){
		return x == null ? 0 : x.count;
	}
	
	public int rank(Key k){
		return rank(k,root);
	}
	
	private int rank(Key k, Node x){
		if(x == null) return 0;
		int compare = k.compareTo(x.key);
		
		if(compare < 0) return rank(k,x.left);
		else if(compare > 0) return 1 + size(x.left) + rank(k, x.right);
		else return size(x.left);
	}
	
	public Iterable<Key> keys(){
		List<Key> queue = new ArrayList<>();
		inorder(root, queue);
		return queue;
	}
	
	private void inorder(Node x, List<Key> q){
		if(x == null) return;
		inorder(x.left, q);
		q.add(x.key);
		inorder(x.right, q);
	}
	
	public void deleteMin(){
		deleteMin(root);
	}
	
	private Node deleteMin(Node x){
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.count = 1+ size(x.left)+ size(x.right);
		return x;
	}
	
	public void deleteMax(){
		deleteMax(root);
	}
	
	private Node deleteMax(Node x){
		if(x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x.count = 1+ size(x.left)+ size(x.right);
		return x;
	}
	
	private Node min(Node x){
		if(x.left == null) return x;
		return min(x.left);
	}
	
	public void delete(Key k){
		delete(k,root);
	}
	
	private Node delete(Key k, Node x){
		if(x == null) return null;
		int compare = k.compareTo(x.key);
		
		if(compare < 0) {
			x.left = delete(k, x.left);
		}else if(compare > 0) {
			x.right = delete(k, x.right);
		}else{
			if(x.right == null) return x.left;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.count = 1+ size(x.left)+ size(x.right);
		return x;
	}
}
