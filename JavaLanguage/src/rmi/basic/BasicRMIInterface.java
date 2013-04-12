package rmi.basic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BasicRMIInterface extends Remote{

	public void welcome() throws RemoteException;
	
	public String getName() throws RemoteException;
}
