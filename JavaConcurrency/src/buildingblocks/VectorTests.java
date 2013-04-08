package buildingblocks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

public class VectorTests {
	
	public static void main(String[] args) {
		VectorTests vectorTests = new VectorTests();
		vectorTests.testVectorIterations();
		vectorTests.testVectorForEach();
		vectorTests.testVectorOldStyleFor();
	}
	
	private void testVectorIterations(){
		final Vector<String> vector = new Vector<>(Arrays.asList(new String[]{"A","B","C"}));
		new Thread(){
			public void run() {
				Iterator<String> iterator = vector.iterator();
				System.out.println("testVectorIterations [ITERATIOS THREAD]: "+vector);
				VectorTests.this.sleep(100);
				System.out.println("testVectorIterations [ITERATIOS THREAD] element present ?: "+iterator.hasNext());
				while(iterator.hasNext()){
					System.out.println(iterator.next());
				}
				System.out.println("testVectorIterations [ITERATIOS THREAD] DONE");
			}
		}.start();
		
		new Thread(){
			public void run() {
				VectorTests.this.sleep(50);
				vector.clear();
				System.out.println("testVectorIterations [CLEAR THREAD]: "+vector);
			}
		}.start();
	}
	
	private void testVectorForEach(){
		final Vector<String> vector = new Vector<>(Arrays.asList(new String[]{"A","B","C"}));
		new Thread(){
			public void run() {
				for(String s: vector){
					VectorTests.this.sleep(100);
					System.out.println(s);
				}
			}
		}.start();
		
		new Thread(){
			public void run() {
				VectorTests.this.sleep(50);
				vector.clear();
			}
		}.start();
	}
	
	private void testVectorOldStyleFor(){
		final Vector<String> vector = new Vector<>(Arrays.asList(new String[]{"A","B","C"}));
		new Thread(){
			public void run() {
				for(int i=0; i<vector.size(); i++){
					VectorTests.this.sleep(100);
					System.out.println(vector.get(i));
				}
			}
		}.start();
		
		new Thread(){
			public void run() {
				VectorTests.this.sleep(50);
				vector.clear();
			}
		}.start();
	}
	
	
	
	private void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

}
