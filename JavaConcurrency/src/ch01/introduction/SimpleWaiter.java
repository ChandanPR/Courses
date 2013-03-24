package ch01.introduction;

public class SimpleWaiter {
	private boolean customerArrived;
	private int noOfPeople;
	public void waitForCustomer() {
		new Thread() {
			public void run() {
				while (!customerArrived) {
				}
				System.out.println("No of people arrived : " + noOfPeople);
			}
		}.start();
	}
	public void customerArrived(int noOfPeople) {
		this.noOfPeople = noOfPeople;
		customerArrived = true;
	}
	public static void main(String[] args) throws InterruptedException {
		SimpleWaiter simpleWaiter = new SimpleWaiter();
		simpleWaiter.waitForCustomer();
		Thread.sleep(10);
		simpleWaiter.customerArrived(10);
	}
}
