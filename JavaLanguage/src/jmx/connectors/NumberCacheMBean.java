package jmx.connectors;

public interface NumberCacheMBean {
	
	public int getCacheHitCount() ;
	
	public int getCacheUpdateCount() ;
	
	public void setCacheHitCount(int cacheHitCount);
	
	public void setCacheUpdateCount(int cacheUpdateCount) ;
	
	public void reset();

}
