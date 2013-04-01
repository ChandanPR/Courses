package dynamic.connectivity;

public class QuickUnion implements UnionFind {

	private int[] a;
	
	public QuickUnion(int n){
		a = new int[n];
		for(int i=0; i<n; i++){
			a[i] = i;
		}
	}
	private int root(int p){
		while(p != a[p]){
			p = a[p];
		}
		return p;
	}
	
	@Override
	public boolean isConnected(int p, int q) {
		return root(p) == root(q);
	}

	@Override
	public void union(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);
		a[pRoot] = a[qRoot];

	}

}
