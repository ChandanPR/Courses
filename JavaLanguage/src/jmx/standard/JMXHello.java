package jmx.standard;

public class JMXHello implements JMXHelloMBean {

	@Override
	public void welcome() {
		System.out.println("Welcome");
	}

	@Override
	public void sendOff() {
		System.out.println("Send Off");
	}

	@Override
	public String getName() {
		return "JMXHello";
	}
	
	@Override
	public String party() {
		return getName();
	}

}
