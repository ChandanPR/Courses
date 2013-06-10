package basics;

public class FinallyTest {
	
	public static int getNumber(){
		try{
			return 3;
		}catch(Exception e){
			return 2;
		}finally{
			return 8;
		}
//		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(getNumber());
	}

}
