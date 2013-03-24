package ch01.introduction;

public class RaceTrack {
	public static void main(String[] args) {
		doTrial(new RaceCar());
	}
	public static void doTrial(final RaceCar car){
		Thread driver1 = new Thread() {
			public void run() {
				car.accelerate();
			};
		};
		Thread driver2 = new Thread() {
			public void run() {
				car.brake();
			};
		};
		driver1.start(); driver2.start();
	}
}
