package generics;

import java.util.List;

public class WildCardAndTypeParameters<E> {

	//Using Type Parameters
	public void add(E e) {

	}

	public void addAll(List<?> c) {

	}

	//Wild Cards or no type parameter
	public void remove(Object o) {

	}

	public void removeAll(List<?> c) {

	}

}
