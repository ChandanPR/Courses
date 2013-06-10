package generics;

public class Pair<T1,T2> {
	T1 t1;
	T2 t2;
	
	public Pair(T1 t1,T2 t2){
		this.t1 = t1;
		this.t2 = t2;
	}
	
	public T1 getT1() {
		return t1;
	}
	
	public T2 getT2() {
		return t2;
	}
	
	public static void main(String[] args) {
		Pair<String, Integer> pair1 = new Pair<>("Test", 10);
		System.out.println(pair1.getT1()+":"+pair1.getT2());
		
		pair1 = new Pair("Test", "Test");
		System.out.println(pair1.getT1()+":"+pair1.getT2());
	}
	
}
