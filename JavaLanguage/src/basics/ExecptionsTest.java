package basics;

public class ExecptionsTest {
	
	public static void main(String[] args) {
		try{
			throw new Error();
		}catch(Exception e){
			System.out.println("Error Caught");
		}
	}

}
