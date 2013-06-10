package innerclasses;

class Shape {
	static int[] num;
	
	static{
		num[0] = 10;
	}
 public class Color{
	 public Color(){
		 System.out.println("Shape.Color.Color()");
	 }
 }
 
 
 enum ColorEnum{
	 RED, GREEN;
 }
 public static void main(String[] args) {
	ColorEnum myColorEnum = ColorEnum.GREEN;
	ColorEnum c3 = ColorEnum.GREEN;
}
}
