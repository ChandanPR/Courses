package datastructures;

public class LinkedList<E> {
	
	Node head;
	public LinkedList(){
		
	}
	
	public void add(E element){
		if(head == null){
			head = getNode(element);
		}else{
			getLastNode().nextNode = getNode(element);
		}
	}
	
	private Node getLastNode(){
		Node curNode = head;
		while(curNode.nextNode != null){
			curNode = curNode.nextNode;
		}
		return curNode;
	}
	
	private Node getNode(E data){
		Node node = new Node();
		node.data = data;
		return node;
	}
	
	private class Node{
		E data;
		Node nextNode;
		
		@Override
		public String toString() {
			return data.toString()+ ((nextNode != null) ? nextNode.toString() : "");
		}
	}

}
