package computations;

public abstract class AbstractPrimeCounter {

	private final String name;
	private static final boolean DEBUG = false;
	
	public AbstractPrimeCounter(String name){
		this.name = name;
	}
	public int computeAllPrimes(int maxNumber){
		long time = System.nanoTime();
		int count = countPrimes(maxNumber);
		if (DEBUG) {
			System.out.println("##########################" + name
					+ "##################");
			System.out.println("Total Primes found : " + count);
			System.out.println("Time :" + ((System.nanoTime() - time) / 1.0e9));
			System.out
					.println("###################################################");
		}
		return count;
	}
	
	public int countPrimesInRange(int low,int high){
		int count = 0;
		for(int i=low; i<=high; i++){
			if(PrimeFinder.isPrime(i)){
				count++;
			}
		}
		return count;
	}
	
	public abstract int countPrimes(int high);
}
