package io.serialization;


public class Point implements Cloneable{
	
	
//	static final long serialVersionUID = 4555673815704008565L;
	
	private int x;
	private int y;
	
//	private int quadrant;
//	
//	enum States{ INIT, INITIALIZING, INTIALIZED,} 
//	AtomicReference<States> ref = new AtomicReference<Point.States>(States.INIT);
	
	public Point(){
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
//		ref.set(States.INTIALIZED);
	}
	
	public int getX() {
//		checkInit();
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
//		checkInit();
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
//	public int getQuadrant() {
//		return quadrant;
//	}
//	
//	private void checkInit(){
//		if(ref.get() != States.INTIALIZED){
//			throw new RuntimeException();
//		}
//	}
//	
//	public void init(int x, int y){
//		System.out.println("Point.init()");
//		ref.set(States.INITIALIZING);
//		this.x = x;
//		this.y = y;
//		ref.set(States.INTIALIZED);
//		System.out.println("Point.init()");
//	}
//	
//		protected void toStringPrivate() {
//			// TODO Auto-generated method stub
////			return super.toString();
//		}
	
	

}
