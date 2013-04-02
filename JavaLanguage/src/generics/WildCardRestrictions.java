package generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WildCardRestrictions {
	
	public static void main(String[] args) {
		ArrayList<? extends Number> list = new ArrayList<>();
//		ArrayList<? extends Number> list = new ArrayList<? extends Number>(); Doesn't work
		
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		
		List<?> list1 = createList();
		List<?> list2 = WildCardRestrictions.<Object>createList();
		List<?> list3 = WildCardRestrictions.<List<?>>createList();
//		List<?> list4 = WildCardRestrictions.<?>createList(); Does't work
	}
	
	
	public static <T> List<T> createList(){
		return new ArrayList<>();
	}
	
	//Doesn;t work
//	class MyList extends ArrayList<?>{
//		
//	}
	
	//Doesn't work
//	class MyList implements List<?>{
//		
//	}
	
	class DoubleList extends ArrayList<List<?>>{
		
	}

}
