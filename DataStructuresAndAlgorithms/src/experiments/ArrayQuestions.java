package experiments;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static utils.Utilities.*;

public class ArrayQuestions {

	private static void showIntersectionAndUnion(int[] a, int[] b) {
		usingHashSet(a, b);
		usingBitSet(a, b);

	}

	private static void usingBitSet(int[] a, int[] b) {
		List<Integer> union = new ArrayList<>();
		List<Integer> intersection = new ArrayList<>();
		BitSet bitSet = new BitSet(a.length + b.length);
		for (int i : a) {
			union.add(i);
			bitSet.set(i);
		}

		for (int i : b) {
			if (bitSet.get(i)) {
				intersection.add(i);
			} else {
				union.add(i);
				bitSet.set(i);
			}
		}
		System.out.println(union + "---" + intersection);
	}

	private static void usingHashSet(int[] a, int[] b) {
		// THE SAME CAN ALSO BE IMPLEMENTED USING BST or BITSET
		Set<Integer> union = new HashSet<>();
		Set<Integer> intersection = new HashSet<>();
		for (int i : a) {
			union.add(i);
		}

		for (int i : b) {
			if (union.contains(i)) {
				intersection.add(i);
			} else {
				union.add(i);
			}
		}

		System.out.println(union + "---" + intersection);
	}

	/**
	 * The property of the array is each element is either +1 or -1 of previous
	 * one
	 * 
	 * @param a
	 * @param expected
	 * @return
	 */
	private static int getElementIndexWithoutLinearSearch(int[] a, int expected) {
		int length = a.length;
		int i = 0;
		int difference = Math.abs(expected - a[i]);
		while (difference != 0 && i < length) {
			difference = Math.abs(expected - a[i]);
			i += difference;
		}

		return difference == 0 ? i : -1;
	}

	// UNSORTED ARRAY
	private static int checkOccurenceUsingBinarySearch(int[] a, int i, int lo,
			int hi) {
		/**
		 * AS AN OPTIMIZATION CHECK if the lo index IS THE ELEMENT SEARCH THE
		 * LEFT HALF FIRST IF NOT FOUND SEARCH RIGHT HALF. SINCE THE ARRAY IS
		 * NOT SORTED U MIGHT END UP LOOKING BOTH THE HALFS
		 */
		return -1;
	}

	private static int[] sortArrayWith2ElementsOnly(int[] a) {
		int j = 0;
		int v = a[0];
		for (int i = 1; i < a.length; i++) {
			int cmp = v - a[i];
			if (cmp > 0) {
				a[j] = a[i];
				a[i] = v;
				j++;
			} else if (cmp < 0) {
				j = i;
				v = a[j];
			}
		}
		return a;
	}
	
	private static int[] sortArrayWith2SortedSubArrays(int[] a){
		int left = 0;
		int right = 0;
		int N = a.length;
		for(int j=1; j<N; j++){
			if(a[j]< a[j-1]){
				right = j;
			}
		}
		while(left < right && right < N){
			if(a[right] <= a[left]){
				shift(a, left, right);
				right++;
			}else{
				left++;
			}
		}
		
		return a;
	}
	
	private static void shift(int[] a, int left, int right){
		int v = a[right];
		while(right > left){
			a[right] = a[--right];
		}
		a[left] = v;
	}
	
	private static int[] sortMiddleSequence(int[] a){
		int N = a.length;
		int middleStart = 0;
		int middleEnd = 0;
		//FIND THE MIDDLE SEQUENCE
		for(int i=1; i<N; i++){
			if(less(a[i], a[i-1])){
				middleStart = i;
				break;
			}
		}
		for(int i=middleStart+1; i<N; i++){
			if(less(a[i], a[i-1])){
				middleEnd = i-1;
			}
		}
		//EXPAND OR UPDATE THE MIDDLE SEQUENCE
		for(int i=middleStart; i>0; i--){
			if(less(a[i-1], a[middleStart])){
				middleStart = i;
				break;
			}
		}
		for(int i=middleEnd+1; i<N; i++){
			if(less(a[middleEnd], a[i])){
				middleEnd = i-1;
				break;
			}
		}
		quickSortThreeWay(a,middleStart,middleEnd);
		return a;
	}
	
	private static void quickSortThreeWay(int[] a, int lo, int hi) {
		if (lo >= hi)
			return;
		int lt = lo;
		int gt = hi;
		int i = lo;
		int v = a[lo];
		while (i <= gt) {
			int cmp = a[i] - v;
			if (cmp < 0)
				exchange(a, lt++, i++);
			else if (cmp > 0)
				exchange(a, i, gt--);
			else
				i++;
		}
		quickSortThreeWay(a, lo, lt - 1);
		quickSortThreeWay(a, gt + 1, hi);
	}
	
	private static int findPairsMatchingSum(int[] a, int sum){
		int count = 0;
		quickSortThreeWay(a, 0, a.length-1);
		assert isSorted(a);
		int start = 0;
		int end = a.length - 1;
		while(start < end){
			int cmp = a[start]+a[end] -sum;
			if(cmp < 0) start++;
			else if(cmp > 0) end--;
			else {
				count++;
				start++;
				end--;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		testUnionAndIntersection();
		testFIndingElementWithoutLinearSearch();
		testSortingArrayWith2TypesofElements();
		testSortingWhenSubSequenceAreSorted();
		testSortingSubSequence();
		testPairsMatchingSum();
	}

	private static void testPairsMatchingSum() {
		assert findPairsMatchingSum(new int[]{1,1,1,1}, 2) == 2;
		assert findPairsMatchingSum(new int[]{1,-1,1,-1}, 0) == 2;
	}

	private static void testSortingSubSequence() {
		assert isSorted(sortMiddleSequence(new int[]{1,2,4,7,10,11,7,12,6,7,16,18,19}));
		assert isSorted(sortMiddleSequence(new int[]{1,2,4,7,10,11,7,12,6,7,8,9,10,16,18,19}));
	}

	private static void testSortingWhenSubSequenceAreSorted() {
		assert isSorted(sortArrayWith2SortedSubArrays(new int[]{2,4,1,2}));
		assert isSorted(sortArrayWith2SortedSubArrays(new int[]{1,2,3,4,5,6,1,2,3,4,5,6}));
	}

	private static void testSortingArrayWith2TypesofElements() {
		assert isSorted(sortArrayWith2ElementsOnly(new int[] { 0, 1,
				1, 0, 1, 1, 1, 0 }));
		assert isSorted(sortArrayWith2ElementsOnly(new int[] { 1, 0,
				1, 0, 1, 0, 1, 0 }));
		assert isSorted(sortArrayWith2ElementsOnly(new int[] { 1, 1,
				1, 1, 1, 0, 0, 0, 0, 0 }));
		assert isSorted(sortArrayWith2ElementsOnly(new int[] { 0, 0,
				0, 0, 1, 1, 1, 1 }));
	}

	private static void testFIndingElementWithoutLinearSearch() {
		assert getElementIndexWithoutLinearSearch(new int[] { 1, 2, 3, 2, 1 },
				4) == -1;
		assert getElementIndexWithoutLinearSearch(new int[] { 1, 2, 3, 2, 1, 0,
				-1, -2, -3 }, -3) == 8;
		assert getElementIndexWithoutLinearSearch(new int[] { -1, -2, -3, -4,
				-3 }, -1) == 0;
	}

	private static void testUnionAndIntersection() {
		showIntersectionAndUnion(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 });
		showIntersectionAndUnion(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3, 4,
				5, 6 });
		// THIS IS A CRAZY INPUT where the array1/array2 itself having
		// duplicates.
		// ASK FOR CLARIFICATIONS
		showIntersectionAndUnion(new int[] { 1, 2, 3, 1, 2, 3 }, new int[] { 1,
				2, 3 });
		showIntersectionAndUnion(new int[] { 1, 2, 3, }, new int[] { 4, 5, 6 });
	}

}
