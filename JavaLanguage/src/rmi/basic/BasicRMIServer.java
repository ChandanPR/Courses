package rmi.basic;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import static rmi.RMIConstants.*;

public class BasicRMIServer implements BasicRMIInterface{

	@Override
	public void welcome() throws RemoteException {
		System.out.println("Welcome to the Basic RMI Server");
	}
	
	
	public static void main(String[] args) {
		try {
			BasicRMIServer server = new BasicRMIServer();
			System.setProperty("java.rmi.server.hostname", RMI_HOST);
			BasicRMIInterface stub = (BasicRMIInterface) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = LocateRegistry.getRegistry(RMI_PORT);
			String url = "rmi://"+RMI_HOST+":"+RMI_PORT+"/BasicRMIInterface";
			System.out.println("RMI URL to bind : "+url);
			registry.rebind(url, stub);
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
