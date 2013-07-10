package experiments;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class IteratorTest {
	
	public static void main(String[] args) throws InterruptedException {
		testWithLinkedQueue();
		testWithCopyOnWriteArrayList();
		
	}

	private static void testWithCopyOnWriteArrayList() {
		final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		for(int i=0; i<10; i++){
			list.add(Integer.toString(i));
		}
		
		new Thread("List ITERABLE"){
			public void run() {
				for(String s : list){
					System.out.println(getName()+":"+s);
					sleep1(2);
				}
			};
		}.start();
		
		new Thread("List ITERATOR"){
			public void run() {
				Iterator<String> it = list.iterator();
				while(it.hasNext()){
					System.out.println(getName()+":"+it.next());
					sleep1(2);
				}
			};
		}.start();
		
		new Thread("List ADDER"){
			public void run() {
				for(int i=10; i<100; i++){
					list.add(Integer.toString(i));
					sleep1(1);
				}
			};
		}.start();
	}

	private static void testWithLinkedQueue() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(3);
		final ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<>();
		for(int i=0; i<10; i++){
			q.add(Integer.toString(i));
		}
		
		new Thread("ITERABLE"){
			public void run() {
				for(String s : q){
					System.out.println(getName()+":"+s);
					sleep1(2);
				}
				latch.countDown();
			};
		}.start();
		
		new Thread("ITERATOR"){
			public void run() {
				Iterator<String> it = q.iterator();
				while(it.hasNext()){
					System.out.println(getName()+":"+it.next());
					sleep1(2);
				}
				latch.countDown();
			};
		}.start();
		
		new Thread("ADDER"){
			public void run() {
				for(int i=10; i<100; i++){
					q.add(Integer.toString(i));
					sleep1(1);
				}
				latch.countDown();
			};
		}.start();
		latch.await();
		System.out.println("-------------------------------------------------------------------------------------------------------------");
	}

	private static void sleep1(int val) {
		try {
			Thread.sleep(val);
		} catch (InterruptedException e) {
		}
	}
}
