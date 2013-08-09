package gc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HeapAnalysisDemo {
	
	public static void main(String[] args) throws InterruptedException {
		List<MyObject> objects = new ArrayList<>();
		for(int i=0; i<10; i++){
			objects.add(new MyObject());
		}
		TimeUnit.MINUTES.sleep(10);
		System.out.println(objects.size());
	}

}
