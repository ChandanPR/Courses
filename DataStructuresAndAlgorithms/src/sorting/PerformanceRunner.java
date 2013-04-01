package sorting;
import static utils.Utilities.isSorted;

public class PerformanceRunner {

	public static void main(String[] args) {
		System.out.println("Numbers\t\t\t\tTime");
		int start = 100000;
		while(start <= 1000000000){
			checkPerformance(start);
			System.gc();
			System.gc();
			System.gc();
			System.gc();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.gc();
			System.gc();
			System.gc();
			System.gc();
			start = start*10;
		}
	}
	
	
	private static void checkPerformance(int n){
		int[] a = getArray(n);
		checkPerformance(a, new HeapSort());
	}


	private static int[] getArray(int n) {
		int[] a = new int[n];
		for(int i=0; i<n; i++){
			a[i] = n - i;
		}
		return a;
	}
	
	private static void checkPerformance(int[] a, Sorter sorter){
		long time = System.nanoTime();
		sorter.sort(a);
		System.out.println(a.length+"\t\t\t\t"+((System.nanoTime() - time)/1e09));
		assert isSorted(a);
	}

}
