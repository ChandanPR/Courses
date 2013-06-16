package buildingblocks;

import java.util.concurrent.Phaser;

/**
 * 
 * Thanks to Ashwin for nice blog on this.
 * Saved a lot of time.
 * http://javaforu.blogspot.in/2011/08/java-7s-jucphaser-short-tutorial.html
 * @author chandanpr
 */

public class PhaserDemo {
	
	public static void main(String[] args) {
		int parties = 2;
		final Phaser phaser = new Phaser(parties);
		phaser.register();
		for(int i=0; i<parties; i++){
			final int j = i;
			new Thread(){
				public void run() {
					int k = j*10;
					while(k < j*10+10){
						phaser.arriveAndAwaitAdvance();
						System.out.println(getName()+" : "+k++);
					}
				};
			}.start();
		}
		phaser.arriveAndDeregister();
	}

}
