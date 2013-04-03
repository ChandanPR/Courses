package jmx.connectors;


public class NumberCache implements NumberCacheMBean {
	
	private int cacheUpdateCount;
	private int cacheHitCount;
	
	public int getCacheHitCount() {
		return cacheHitCount;
	}
	
	public int getCacheUpdateCount() {
		return cacheUpdateCount;
	}
	
	public void setCacheHitCount(int cacheHitCount) {
		this.cacheHitCount = cacheHitCount;
	}
	
	public void setCacheUpdateCount(int cacheUpdateCount) {
		this.cacheUpdateCount = cacheUpdateCount;
	}
	
	
	@Override
	public void reset() {
		
	}


}
