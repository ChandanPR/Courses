package basics;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class IteratorTest {

	public static void main(String[] args) {
//		testLinkedQueue();
//		testLinkedForEach();
//		testLinkedQueueWithForEachConcurrent();
		testLinkedForEach();
		testLinkedForEachInSameThread();
	}

	private static void testLinkedQueueWithForEachConcurrent() {
		final Queue<String> queue = new ConcurrentLinkedQueue<>();
		new Thread("BEFORE HEAD :") {
			public void run() {
				for (String str : queue) {
					System.out.println(getName()+str);
				}
				System.err.println(getName());
			};
		}.start();

		new Thread("AFTER HEAD : ") {
			public void run() {
				sleep1(5);
				for (String str : queue) {
					sleep1(2);
					System.out.println(getName()+str);
				}
				System.err.println(getName());
			};
		}.start();

		new Thread("ADDER : ") {
			public void run() {
				for(int i=0; i<10; i++)
					add(queue);
			}

		}.start();

	}

	private static void sleep1(int time) {
		try {
			TimeUnit.MICROSECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

	private static void testLinkedQueue() {
		Queue<String> queue = new ConcurrentLinkedQueue<>();
		Iterator<String> itreratorBeforeHeadAdded = queue.iterator();
		queue.add("A");
		Iterator<String> itreratorAfterHeadAdded = queue.iterator();
		queue.add("A");
		queue.add("A");
		queue.add("A");
		while (itreratorBeforeHeadAdded.hasNext()) {
			System.out.println("Before : " + itreratorBeforeHeadAdded.next());
		}

		while (itreratorAfterHeadAdded.hasNext()) {
			System.out.println("after : " + itreratorAfterHeadAdded.next());
		}
	}
	
	private static void testLinkedForEach() {
		final Queue<String> queue = new ConcurrentLinkedQueue<>();
		for(String str: queue){
			System.out.println("testLinkedForEach Before : " + str);
		}
		queue.add("A");
		new Thread(){
			public void run() {
				sleep1(1);
				for(String str: queue){
					System.out.println("testLinkedForEach After : " + str+" : "+queue);
					sleep1(1);
				}
			};
		}.start();
		System.out.println("IteratorTest.testLinkedForEach()");
		add(queue);
		add(queue);
		add(queue);
		add(queue);
		System.out.println("IteratorTest.testLinkedForEach() end");
	}
	
	private static void testLinkedForEachInSameThread() {
		final Queue<String> queue = new ConcurrentLinkedQueue<>();
		for(String str: queue){
			System.out.println("testLinkedForEachInSameThread Before : " + str);
		}
		queue.add("A");
//		queue.add("A"); //Goes for infinite loop if this is added
		for(String str: queue){
			System.out.println("testLinkedForEachInSameThread Before : " + str+" : "+queue);
			queue.add("A");
		}
		
		System.out.println("testLinkedForEachInSameThread : " + queue);
	}

	private static void add(final Queue<String> queue) {
		sleep1(1);
		queue.add("A");
		System.out.println("Addition : "+queue);
	}
	

}
