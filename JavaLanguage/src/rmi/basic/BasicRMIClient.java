package rmi.basic;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BasicRMIClient {
	private static final int RMI_PORT = 9999;
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("10.0.0.150", RMI_PORT);
			BasicRMIInterface stub = (BasicRMIInterface)registry.lookup("rmi://10.0.0.150/BasicRMIInterface");
			stub.welcome();
			System.out.println(stub.getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
