package gc;

import java.util.ArrayList;
import java.util.List;

public class HeapDumpAnalysisDemo {
	
	public static void main(String[] args) throws InterruptedException {
		List<A.B> stringList = new ArrayList<>();
		for(;;){
			stringList.add(new A().new B());
		}
	}
	
	
	
	static class A{
		class B{
			
		}
	}

}
