package rmi.basic;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import static rmi.RMIConstants.*;

public class BasicRMIClient {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry(RMI_HOST, RMI_PORT);
			for(String boundName : registry.list()){
				System.out.println(boundName);
			}
			System.out.println("Registry Obtained");
			BasicRMIInterface stub = (BasicRMIInterface)registry.lookup("rmi://"+RMI_HOST+":"+RMI_PORT+"/BasicRMIInterface");
			System.out.println("Stub Obtained");
			stub.welcome();
			System.out.println(stub.getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
