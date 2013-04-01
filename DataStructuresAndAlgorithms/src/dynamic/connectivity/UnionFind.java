package dynamic.connectivity;

/**
 * Defines the interface for the UnionFind algorithm
 * @author chandanpr
 */
public interface UnionFind {
	
	/**
	 * Returns true if p and q are connected.
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isConnected(int p, int q);
	
	/**
	 * Makes the union of p and q.
	 * @param p
	 * @param q
	 */
	public void union(int p, int q);

}
