package basics;

public class OperatorsTest {
	
	public static void main(String[] args) {
		int i=0;
		int j=0;
		if(i++ == ++j){
			System.out.println("true i: "+i+", j: "+j);
		}else{
			System.out.println("false i: "+i+", j: "+j);
		}
		
		i >>>= 2;
	}

}
