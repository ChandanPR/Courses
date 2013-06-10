package basics;

public class MethodResoultionTest {

	public void test(String string) {
		System.out.println("MethodResoultionTest.test(String)");
	}

	public void test(Double dou) {
		System.out.println("MethodResoultionTest.test(Double)");
	}
	
	public void test(Float flo) {
		System.out.println("MethodResoultionTest.test(Float)");
	}

	public void test(Integer integer) {
		System.out.println("MethodResoultionTest.test(Integer)");
	}

	public void test(int i) {
		System.out.println("MethodResoultionTest.test(int)");
	}

	public void test(short shr) {
		System.out.println("MethodResoultionTest.test(short)");
	}
	
	public void test(int i, long l){
		System.out.println("MethodResoultionTest.test(int,long)");
	}
	
	public void test(long l,int i){
		System.out.println("MethodResoultionTest.test(long,int)");
	}
	
	
	public static void main(String[] args) {
		MethodResoultionTest mrt = new MethodResoultionTest();
		mrt.test("String");
		mrt.test(9.0);
		byte b = 1;
		mrt.test(b);
		mrt.test(9);
		
		//Not Applicable. No exact match. Up cast not possible.
//		mrt.test(new Object());
		
		//Ambiguous
//		mrt.test(null);
//		mrt.test(1, 1);
	}

}
