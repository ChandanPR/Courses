package ch01.introduction;

public class RaceCar {
	private Integer speed = 10;
	private static final int MAX_SPEED = 500,MIN_SPEED = 5;
	public void accelerate() {
		while (speed < MAX_SPEED) 
			if(!updateSpeed(1, "Don't break")) break;
		System.out.println("Max Speed is reached :" + speed);
	}
	public void brake() {
		while (speed > MIN_SPEED) 
			if(!updateSpeed(-1, "Don't Accelerate")) break;
		System.out.println("Min Speed reached :" + speed);
	}
	private boolean updateSpeed(int offset,String message){
		synchronized (speed) {
			int currentSpeed = speed;
			speed += offset;
			if(speed != currentSpeed+offset){ 
				System.err.println(message);
				return false;
			}
			return true;
		}
	}
}