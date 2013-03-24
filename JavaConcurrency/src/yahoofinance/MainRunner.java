package yahoofinance;

public class MainRunner {
	
	public static void main(String[] args) throws InterruptedException {
//		new SequentialNav().computeValues();
//		new ConcurrentNav().computeValues();
//		new ConcurrentNoWaitNav().computeValues();
		measurePoolSizeVariationPerformance();
	}
	
	static void measurePoolSizeVariationPerformance(){
		System.out.println("Value\t\t\tPool Size\tTime");
		for(int i=40; i>0; i--){
			long time = System.nanoTime();
			double value = new ConcurrentNav(i).computeValues();
			System.out.println(value+"\t"+i+"\t\t"+ ((System.nanoTime() - time) / 1.0e9));
			
		}
	}

}
