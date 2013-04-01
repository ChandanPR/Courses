package dynamic.connectivity;

public class QuickFind implements UnionFind {

	private int[] a;
	
	public QuickFind(int n){
		a = new int[n];
		for(int i=0; i<n; i++){
			a[i] = i;
		}
	}
	
	@Override
	public boolean isConnected(int p, int q) {
		return a[p] == a[q];
	}

	@Override
	public void union(int p, int q) {
		int pid = a[p];
		int qid = a[q];
		for (int i = 0; i < a.length; i++) {
			if(a[i] == pid){
				a[i] = qid;
			}
		}

	}

}
