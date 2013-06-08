package utils;

import java.util.Comparator;
import static utils.Trace.*;
import static utils.Utilities.show;

/**
 * This is a utilities class for the algorithms and data structures
 *  
 * @author chandanpr
 */
@SuppressWarnings("all")
public final class Utilities {
	
	private Utilities(){
		
	}
	
	/**
	 * Returns true if p is less than q.
	 * @param p
	 * @param q
	 * @return
	 */
	public static boolean less(int p, int q){
		return p - q < 0;
	}
	
	/**
	 * 
	 * @param data
	 * @param p
	 * @param q
	 * @return
	 */
	public static boolean less(int[] data,int p, int q){
		return data[p] - data[q] < 0;
	}
	
	/**
	 * Returns true if p is less than q.
	 * @param p
	 * @param q
	 * @return
	 */
	public static boolean less(Comparable p, Comparable q){
		return p.compareTo(q) < 0;
	}
	
	/**
	 * Compares p and q using the given Comparator and returns true
	 * if p is less than q
	 * @param p
	 * @param q
	 * @param c
	 * @return
	 */
	public static boolean less(Object p, Object q, Comparator c){
		return c.compare(p, q) < 0;
	}
	
	/**
	 * Exchanges the elements at index i and j.
	 * @param a
	 * @param i
	 * @param j
	 */
	public static void exchange(Object[] a, int i, int j){
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	/**
	 * Exchanges the elements at index i and j.
	 * @param a
	 * @param i
	 * @param j
	 */
	public static void exchange(int[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	/**
	 * Returns true if the array is sorted.
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static boolean isSorted(Comparable[] a, int lo, int hi){
		for(int i=lo+1; i<=hi; i++){
			if(less(a[i], a[i-1])){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns true if the array is sorted
	 * @param a
	 * @param c
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static boolean isSorted(Object[] a, Comparator c, int lo, int hi){
		for(int i=lo+1; i<=hi; i++){
			if(less(a[i], a[i-1],c)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns true if the array is sorted
	 * @param a
	 * @return
	 */
	public static boolean isSorted(Comparable[] a){
		return isSorted(a, 0, a.length-1);
	}
	
	/**
	 * Returns true if the array is sorted
	 * @param a
	 * @param c
	 * @return
	 */
	public static boolean isSorted(Object[] a, Comparator c){
		return isSorted(a, c, 0, a.length-1);
	}
	
	/**
	 * Returns true if the array is sorted.
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static boolean isSorted(int[] a){
		return isSorted(a, 0, a.length-1);
	}
	
	/**
	 * Returns true if the array is sorted.
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static boolean isSorted(int[] a, int lo, int hi){
		for(int i=lo+1; i<=hi; i++){
			if(less(a[i], a[i-1])){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Prints the given int array
	 * @param a
	 */
	public static void show(int[] a){
		show(a,0,a.length-1);
	}
	
	/**
	 * Prints the given array from lo to high
	 * @param a
	 * @param lo
	 * @param high
	 */
	public static void show(int[] a,int lo, int high){
		if(DEBUG){
			for (int i = lo; i <= high; i++) {
				print(a[i] + " ");
			}
			println();
		}
	}
	
	/**
	 * Returns the copy of the array
	 * @param a
	 * @return
	 */
	public static int[] copy(int[] a){
		int length = a.length;
		int[] copy = new int[length];
		
		for(int i=0; i<length; i++){
			copy[i] = a[i];
		}
		return copy;
	}
	
	/**
	 * 
	 * @return int[]
	 */
	public static int[] getIntArray() {
		int count = 10;
		int[] a = new int[count];
		for (int i = count - 1; i > -1; i--) {
			a[i] = (int) (i * count * Math.random());
		}
		show(a);
		return a;
	}

}
