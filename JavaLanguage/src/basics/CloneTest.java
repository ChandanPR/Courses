package basics;


/**
 * You will face a fundamental problem while cloning final fields.
 * Clone architecture doesn't allow you to clone final fields
 * @author chandanpr
 *
 */
public class CloneTest {
	
	
	public static void main(String[] args) throws CloneNotSupportedException {
		A a = new A("Name");
		A a1 = a.clone();
		
		System.out.println(a == a1);
		//RETURNS TRUE
		System.out.println(a.b == a1.b);
		System.out.println(a.name == a1.name);
	}
	private static class A implements Cloneable{
		B b = new B();
		String name;
		
		public A(String name){
			this.name = name;
		}
		
		@Override
		public A clone() throws CloneNotSupportedException {
			A clone = (A)super.clone();//THIS IS A SHALLOW CLONE
			return clone;
		}
	}
	
	private static class B{
	}

}
