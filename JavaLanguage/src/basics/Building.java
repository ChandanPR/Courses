package basics;

public class Building {

	public static void main(String[] args) {
//		Building b1 = new Building();
//		Bunglow bl1 = new Bunglow();
//		Bunglow bl2 = (Bunglow)b1;
//		Object ob1 = (Object)b1;
		
		System.out.println(new ToString() {
			@Override
			public String toString() {
				return "Test";
			}
		});
	}
	
	interface ToString{
		String toString();
	}
}

class Bunglow extends Building{
	
}
