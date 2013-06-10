package generics;

public class GenericsWithInheritance {

	public static void main(String[] args) {
		ChildClass<String> cc = new ChildClass<>();
		//Doesn't compile
//		cc.set("");
	}
}

class BaseClass<E>{
	E e;
	public void set(E e){
		this.e = e;
	}
}

class ChildClass<T extends CharSequence> extends BaseClass<String>{
	
	public void set(T e) {
	}
}
