package basics;

public class CloneTests {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		String[] str = new String[10];
		String[] str1 = str.clone();
		System.out.println(str1);
		
		B1 b = new B1();
		B1 b1 = b.clone();
		
		System.out.println(b.a == b1.a);
		
		D d = new D(b);
		//Wil get a class cast exception here.
		D d1 = d.clone();
		
		
	}
}

class A1{
	
}

class B1 extends A1 implements Cloneable{
	A1 a = new A1();
	@Override
	public B1 clone() {
		try {
			return (B1)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}


class C1 extends A1 implements Cloneable{
	
	private int id;
	private final B1 b;
	
	public C1(){
		this(-1,null);
	}
	
	public C1(B1 b){
		this(-1,b);
	}
	public C1(int id, B1 b){
		this.id = id;
		this.b = b;
	}
	
	@Override
	public C1 clone() {
		return new C1(id,(B1)b.clone());
	}
}

class D extends C1 implements Cloneable{
	
	public D(B1 b){
		super(b);
	}
	
	@Override
	public D clone() {
		return (D)super.clone();
	}
}

class UniqueIndex implements Cloneable{
	//Usually you don't serialize the primitives.
	//But there can be exceptions like this.
	//for e.g. index represents no. of object created.
	private int index = 0;
	
	UniqueIndex(){
		index++;
	}
	
	public int getIndex() {
		return index;
	}
	
	@Override
	public UniqueIndex clone() {
		UniqueIndex clone = null;
		try {
			clone = (UniqueIndex)super.clone();
			clone.index = index++;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
	
}
