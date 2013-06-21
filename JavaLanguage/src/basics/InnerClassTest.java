package basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InnerClassTest {

	public static void main(String[] args) {
		new InnerClassTest().test(new Random().nextBoolean());
	}

	void test(boolean testStatic) {
		System.out.println("testStatic : "+testStatic);
		if (testStatic) {
			List<A.C> list = new ArrayList<>();
			while (true) {
				for (int i = 0; i < 10; i++){
					list.add(new A().getC());
				}
				System.out.println(list.size());
			}
		} else {
			List<A.B> list = new ArrayList<>();
			while (true) {
				for (int i = 0; i < 10; i++)
					list.add(new A().getB());
				System.out.println(list.size());
			}
		}
	}

	static class A {
		int[] ints = new int[1000000];

		class B {

		}

		static class C {

		}
		
		B getB(){
			return new B();
		}
		
		static C getC(){
			return new C();
		}

	}

}
