package computations;

public class PrimeFinder {
	
	public static boolean isPrime(int number){
		for(int i=2; i<Math.sqrt(number); i++){
			if(number % i == 0){
				return false;
			}
		}
		return true;
	}

}
