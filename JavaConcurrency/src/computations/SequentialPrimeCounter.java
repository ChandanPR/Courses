package computations;

public class SequentialPrimeCounter extends AbstractPrimeCounter {

	public SequentialPrimeCounter() {
		super("Sequential Prime Counter");
	}

	@Override
	public int countPrimes(int high) {
		return countPrimesInRange(1, high);
	}

}
