package basics;

public class Initialization {
	public static void main(String[] args) {
		C c = new C();
	}
}


class A{
	A(){
		System.out.println("A");
	}
	
	A(String s){
		System.out.println("A string");
	}
}

class B extends A{
	A a  = new A("str");
	A b = new A();
	B(){
		System.out.println("B");
	}
}


class C extends B{
	C(){
		System.out.println("C");
	}
}