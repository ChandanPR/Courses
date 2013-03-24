package ch01.introduction;

public class ThreadContention {
	
	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(5000);
		for(int i=0; i<100; i++){
			new Thread(){
				public void run() {
					int maxValue = Integer.MAX_VALUE;
					for(int j=0; j<maxValue; j++){
							for(int k=0; k<maxValue/2; k++){
								k++;
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
					}
				};
			}.start();
		}
	}

}
