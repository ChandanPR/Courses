package buildingblocks;

import java.util.ArrayList;
import java.util.List;

public class HiddenIterators {
	private final int THRESHOLD = 1000000;
	
	public static void main(String[] args) {
		HiddenIterators hiddenIterators = new HiddenIterators();
		hiddenIterators.testContains();
		hiddenIterators.testToString();
	}
	
	private void testContains(){
		final List<Integer> intList = getIntList();
		Thread t1 = new Thread(){
			public void run() {
				System.out.println("testContains t1:"+intList.contains(THRESHOLD));
			}
		};
		Thread t2 = new Thread(){
			public void run() {
				System.out.println("testContains t2: "+intList.remove(10));
			}
		};
		
		t1.start();
		t2.start();
	}
	
	private void testToString(){
		final List<Integer> intList = getIntList();
		Thread t1 = new Thread(){
			public void run() {
				String string = "testToString : "+intList;
				System.out.println("testToString : String Obtained");
			}
		};
		Thread t2 = new Thread(){
			public void run() {
				HiddenIterators.this.sleep(100);
				System.out.println("testToString : "+intList.remove(10));
			}
		};
		
		t1.start();
		t2.start();
	}
	
	private List<Integer> getIntList(){
		List<Integer> intList = new ArrayList<>();
		for(int i=0; i<THRESHOLD; i++){
			intList.add(i);
		}
		return intList;
	}
	
	private void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

}
