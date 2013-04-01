package dynamic.connectivity;

public class MainRunner {
	
	public static void main(String[] args) {
		checkValidity(new QuickFind(10),10);
		checkValidity(new QuickUnion(10),10);
		checkValidity(new WeightedQuickUnion(10),10);
		checkValidity(new WeightedQuickUnionPC(10),10);
	}
	
	private static void checkValidity(UnionFind uf,int length){
		uf.union(1, 3);
		uf.union(6, 8);
		uf.union(3, 9);
		assert uf.isConnected(9, 1) == true;
		assert uf.isConnected(1, 4) == false;
		uf.union(0, 9);
		uf.union(8, 9);
		uf.union(2, 3);
		uf.union(4, 5);
		uf.union(6, 7);
		uf.union(1, 5);
		assert uf.isConnected((int)(length*Math.random()), (int)(length*Math.random())) == true;
		assert uf.isConnected((int)(length*Math.random()), (int)(length*Math.random())) == true;
		assert uf.isConnected((int)(length*Math.random()), (int)(length*Math.random())) == true;
		assert uf.isConnected((int)(length*Math.random()), (int)(length*Math.random())) == true;
		assert uf.isConnected((int)(length*Math.random()), (int)(length*Math.random())) == true;
	}

}
