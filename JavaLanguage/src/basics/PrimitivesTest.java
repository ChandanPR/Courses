package basics;


public class PrimitivesTest {
	
	public static void main(String[] args) {
//		boolean[] b = new boolean[4];
//		System.out.println(b[0]);
//		
//		try{
//			Integer i = Integer.valueOf("two");
//		}catch(IllegalArgumentException e){
//			
//		}catch(Exception e){
//			
//		}
//		
//		int x = 24;
//		do{
//			x--;
//			System.out.println("X: "+x);
//		}while(x<20);
//		System.out.println(x);
//		
//		for (int i = 0; i < 10; i++) {
//			if(i == 5){
//				System.out.println("Out of loop");
//				break;
//			}
//			
//			if(i==12){
//				System.out.println("Stil in loop");
//				break;
//			}
//		}
//		
//		
//		
//		
//		int temp =0, count;
//		
//		
//		while(temp < 3){
//			temp++;
//			for(count = 1; count<3; count++){
//				System.out.println("temp : "+temp+",count"+count);
//			}
//		}
//		int num = 1;
//		for (num = 0; num < 3; ++num) {
//			num *= 2;
//			System.out.println("Inside  "+num);
//		}
//		System.out.println(num);
//		System.out.println((++num));
		
		int i=0,j=0;
		boolean b = ((i++ == ++j && i-- == --j));
		System.out.println(b);
		System.out.println(i);
	}

}
