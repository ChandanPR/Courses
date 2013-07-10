package experiments;

import java.util.Stack;

public class Tower {
	Stack<Integer> discs = new Stack<>();
	
	public Tower(){
		
	}
	
	public Tower(int n){
		for(int i=n-1; i>=0; i--){
			discs.push(i);
		}
	}
	
	public void moveDisksTo(Tower destination){
		moveDisksTo(discs.size(), destination, new Tower());
	}
	
	public void addDisk(Integer item){
		discs.push(item);
	}
	
	private void moveDisksTo(int n,Tower destination, Tower buffer){
		if(n == 0) return;
		moveDisksTo(n-1,buffer,destination);
		destination.addDisk(discs.pop());
		buffer.moveDisksTo(n-1,destination,this);
	}
	
	public void show(){
		int size = discs.size();
		for(int i=0; i<size; i++){
			System.out.println(discs.get(i));
		}
	}
	
	public static void main(String[] args) {
		Tower t1 = new Tower(10);
		t1.show();
		Tower t2 = new Tower();
		t1.moveDisksTo(t2);
		t2.show();
		
	}

}
