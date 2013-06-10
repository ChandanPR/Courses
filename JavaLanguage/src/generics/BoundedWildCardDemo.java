package generics;

import java.util.ArrayList;
import java.util.List;

public class BoundedWildCardDemo {
	
	public static void main(String[] args) {
		List<? super Runnable> runnableList = new ArrayList<>();
		runnableList.add(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
	}

}

