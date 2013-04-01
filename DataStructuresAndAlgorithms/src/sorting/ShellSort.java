package sorting;

import static utils.Utilities.exchange;
import static utils.Utilities.less;

public class ShellSort implements Sorter {

	@Override
	public void sort(int[] a) {
		int length = a.length;
		int h = 1;
		while (h < length / 3) {
			h = 3 * h + 1;
		}
		while (h >= 1) {
			for (int i = h; i < length; i++) {
				for (int j = i; j >= h; j -= h) {
					if (less(a[j], a[j - h])) {
						exchange(a, j, j - h);
					}
				}
			}
			h = h/3;
		}

	}

}
