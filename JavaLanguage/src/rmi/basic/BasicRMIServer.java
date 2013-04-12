package rmi.basic;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BasicRMIServer implements BasicRMIInterface{

	public static final int RMI_PORT = 9999;


	@Override
	public void welcome() throws RemoteException {
		System.out.println("Welcome to the Basic RMI Server");
	}
	
	
	public static void main(String[] args) {
		try {
			BasicRMIServer server = new BasicRMIServer();
			System.setProperty("java.rmi.server.hostname", "10.0.0.150");
			BasicRMIInterface stub = (BasicRMIInterface) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = LocateRegistry.getRegistry(RMI_PORT);
			registry.rebind("rmi://10.0.0.150:"+RMI_PORT+"/BasicRMIInterface", stub);
			System.err.println("BasicRMIServer is running");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}


	@Override
	public String getName() throws RemoteException {
		return BasicRMIInterface.class.getSimpleName();
	}

}
