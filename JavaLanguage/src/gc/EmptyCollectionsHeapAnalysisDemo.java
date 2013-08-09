package gc;

import java.util.ArrayList;
import java.util.List;

public class EmptyCollectionsHeapAnalysisDemo {
	
	public static void main(String[] args) {
		List<List<String>> listOfLists = new ArrayList<>();
		for(;;){
			listOfLists.add(new ArrayList<String>());
		}
	}

}
