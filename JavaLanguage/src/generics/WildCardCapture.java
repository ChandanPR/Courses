package generics;

import java.util.ArrayList;
import java.util.List;

public class WildCardCapture {
	
	/**
	 * Wild Card demonstration
	 * @param src
	 */
	public static void copy(List<?> src){
		List<Object> dest = new ArrayList<Object>(src);
		for(Object e : src){
			dest.add(e);
		}
		
		for(int i=0; i<src.size(); i++){
			dest.set(i,src.get(i));
		}
	
		int counter = 0;
		for(Object e : src){
			dest.set(counter++,e);
		}
//		
//		List<?> dest = new ArrayList<>();
//		for(Object e : src){
//			Doesn't compile
//			dest.add(e);
//		}
		

	}
	
	
	/**
	 * Wild Card Capture demonstration
	 * @param src
	 */
	public static void captureCopy(List<?> src){
		captureCopyImpl(src);
	}
	
	private static <T> void captureCopyImpl(List<T> src){
		List<T> dest = new ArrayList<>(src);
		for(T e : src){
			dest.add(e);
		}
		
		for(int i=0; i<src.size(); i++){
			dest.set(i,src.get(i));
		}
	
		int counter = 0;
		for(T e : src){
			dest.set(counter++,e);
		}
		
	}

}
