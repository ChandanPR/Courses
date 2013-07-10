package experiments;

import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class NavigableCollectionDemo {
	
	public static void main(String[] args) {
		NavigableSet<String> set = new ConcurrentSkipListSet<>();
		NavigableMap<String, String> map = new ConcurrentSkipListMap<>();
		
		for(int i=0; i<10; i++){
			String string = Integer.toString(i);
			set.add(string);
			map.put(string, string);
		}
		
		System.out.println(set.first()+":"+set.last());
		System.out.println(map.firstKey()+":"+map.lastKey());
	}

}
