package dynamic.connectivity;

public class WeightedQuickUnion implements UnionFind {

	private int[] a;
	private int[] size;
	
	public WeightedQuickUnion(int n){
		a = new int[n];
		size = new int[n];
		for(int i=0; i<n; i++){
			a[i] = i;
			size[i] = 1;
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
		if(size[pRoot] < size[qRoot]){
			a[pRoot] = a[qRoot];
			size[qRoot] += size[pRoot];
		}else{
			a[qRoot] = a[pRoot];
			size[pRoot] += size[qRoot];
		}

	}

}
