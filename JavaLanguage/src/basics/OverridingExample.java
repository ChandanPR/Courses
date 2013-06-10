package basics;

public class OverridingExample {

	public static void main(String[] args) {
		BaseClass bc = new ChildClass();
		bc.test((Integer) 9);
		
		System.out.println(new ChildClass().doIt(4, 5));
		System.out.println(new ChildClass().doIt(4));
		System.out.println(new ChildClass().doIt(4, 5,6));
	}
}

class BaseClass {

	public String m1() {
		return toString();
	}

	public int m2() {
		return hashCode();
	}

	public Number m3() {
		return null;
	}

	public void test(Number num) {
		System.out.println("BaseClass.test()");
	}

	protected int blipvert(int x) {
		return 0;
	}

	public BaseClass hello() {
		return this;
	}
}

class ChildClass extends BaseClass {

	// public Object m1(){
	// return null;
	// }

	// public long m2(){
	// return 1L;
	// }

	public Integer m3() {
		return null;
	}

	public void test(Integer integer) {
		System.out.println("ChildClass.test()");
	}

	protected int blipvert(long x) {
		return 0;
	}

	@Override
	public ChildClass hello() {
		// TODO Auto-generated method stub
		return this;
	}
	
	public String doIt(int i, int j){
		return "A";
	}
	
	public String doIt(int ... i){
		return "B";
	}
}
