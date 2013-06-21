package basics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ForEachOutSideModificationTest {
	
	public static void main(String[] args) {
		final List<Integer> list = getList();
		new Thread("ITERATOR"){
			public void run() {
				System.out.println(getName());
				for(int i : list){
					System.out.println("Value : "+i);
					try {
						TimeUnit.MILLISECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
		
		new Thread("DESTROYER"){
			public void run() {
				System.out.println(getName());
				while(list.size() > 0){
					list.remove(list.size() - 1);
				}
			};
		}.start();
		
		new Thread("BUILDER"){
			public void run() {
				System.out.println(getName());
				while(list.size() < 20){
					list.add(20);
				}
			};
		}.start();
		
	}
	
	private static List<Integer> getList(){
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<10; i++){
			list.add(i);
		}
		return list;
	}

}
