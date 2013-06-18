package experiments;

public class Sequence {

	public int findMaxSubSequenceSum(int[] sequence) {
		if (sequence == null || sequence.length == 0) {
			return 0;
		}
		int tempSum = sequence[0];
		int finalSum = tempSum;
		for (int i = 1; i < sequence.length; i++) {
			int nextSum = tempSum + sequence[i];
			if (nextSum < tempSum) {
				finalSum = tempSum;
			}
			//THIS IS NEEDED TO HANDLE THE CASE OF
			//ONLY NEGATIVE NUMBERS
			//RESET tempSum zero only when the current tempSum
			//is +ve and nextSum is -ve.
			if (tempSum >= 0){
				tempSum = Math.max(nextSum, 0);
			}
		}
		finalSum = Math.max(finalSum, tempSum);
		System.out.println(finalSum);
		return finalSum;
	}

	public static void main(String[] args) {
		Sequence sequence = new Sequence();
		assert sequence.findMaxSubSequenceSum(new int[] { 10, 4, -20, 18, 3,
				19, -100, 3 }) == 40;
		assert sequence.findMaxSubSequenceSum(new int[] { 3, 5, 10, -8, 20, 10,
				30, -100, 1, 10 }) == 70;
		// ONLY NEGATIVES
		assert sequence
				.findMaxSubSequenceSum(new int[] { -10, -1, -4, -5, -15 }) == -10;
	}

}
