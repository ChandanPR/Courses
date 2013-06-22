package experiments;

import java.util.BitSet;

import utils.Utilities;

public class DuplicatesDemo {
	
	public static int countDuplicates(int[] ints){
		BitSet bits = new BitSet(ints.length);
		int count = 0;
		for(int i=0; i<ints.length; i++){
			if(!bits.get(ints[i])){
				bits.set(ints[i]);
			}else{
				count++;
			}
		}
		return count;
	}
	
	public static int[] getUniqueInts(int[] array){
		int length = array.length;
		int i, j;

		/* new length of modified array */
		int NewLength = 1;

		for(i=1; i< length; i++){

		   for(j=0; j< NewLength ; j++)
		   {

		      if(array[i] == array[j])
		      break;
		   }

		   /* if none of the values in index[0..j] of array is not same as array[i],
		      then copy the current value to corresponding new position in array */

		  if (j==NewLength )
		      array[NewLength++] = array[i];
		}
		return array;
	}
	
	public static void main(String[] args) {
		testDuplicateCount();
		
		Utilities.show(getUniqueInts(new int[]{1,2,3,4,5,6,4,5,6}));
	}

	private static void testDuplicateCount() {
		assert countDuplicates(new int[]{1,2,3,4,5,6}) == 0;
		assert countDuplicates(new int[]{1,1,3,4,5,6}) == 1;
		assert countDuplicates(new int[]{1,1,1,4,5,6}) == 2;
		assert countDuplicates(new int[]{1,1,1,1,5,6}) == 3;
		assert countDuplicates(new int[]{1,1,1,1,1,6}) == 4;
		assert countDuplicates(new int[]{1,1,1,1,1,1}) == 5;
	}

}
