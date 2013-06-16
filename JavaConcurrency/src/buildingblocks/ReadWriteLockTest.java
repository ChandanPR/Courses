package buildingblocks;

import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

	private ArrayList<String> stringList = new ArrayList<>();
	private ReadWriteLock rwlock = new ReentrantReadWriteLock();

	private String getElement(int index,String tname) {
		System.out.println("Waiting to get readlock "+tname);
		rwlock.readLock().lock();
		System.out.println("got readlock "+tname);     
		String string = stringList.get(index);
		rwlock.readLock().unlock();
		System.out.println("released readlock "+tname);
		return string;
	}

	private void addElement(String element,String tname) {
		System.out.println("Waiting to get writelock "+tname);
		rwlock.writeLock().lock();
		System.out.println("got writelock "+tname);
		stringList.add(element);
		rwlock.writeLock().unlock();
		System.out.println("released writelock "+tname);
	}

	private void addElement(String element, int times,String tname) {
		System.out.println("Waiting to get writelock "+tname);
		rwlock.writeLock().lock();
		System.out.println("got writelock "+tname);
		for (int i = 0; i < times; i++)
			stringList.add(element);
		sleep1(10000);
		rwlock.writeLock().unlock();
		System.out.println("released writelock "+tname);
	}
	
	public static void main(String[] args) {
		final ReadWriteLockTest test = new ReadWriteLockTest();
		test.addElement("ELEM", "MAIN");
		for(int i=1; i<=10; i++){
			new Thread("T"+i){
				public void run() {
					while(true){
						test.getElement(0, getName());
						sleep1(1);
					}
				};
			}.start();
		}
		
		new Thread("WRITING"){
			public void run() {
				while(true){
					test.addElement("ELEM",100, getName());
				}
			};
		}.start();
	}
	
	private static void sleep1(int val) {
		try {
			Thread.sleep(val);
		} catch (InterruptedException e) {
		}
	}

}
