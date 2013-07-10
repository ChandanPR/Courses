package innerclasses;

public class InnerClassDemo {
//	ICDemoChild child = new ICDemoChild();
	public static void main(String[] args) throws InterruptedException {
		InnerClassDemo demo = new InnerClassDemo();
		System.out.println("______________________");
		Thread.sleep(5000);
		
		InnerClassDemo.ICDemoChild child = demo.new ICDemoChild();
	}
	
	private class ICDemoChild{
		private String name;
	}

}
