package rmi.callback;

import static rmi.RMIConstants.RMI_HOST;
import static rmi.RMIConstants.RMI_PORT;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SimpleScoreBoard extends UnicastRemoteObject implements ScoreBoard{

	private static final long serialVersionUID = 7418822010385550164L;

	protected SimpleScoreBoard() throws RemoteException {
		super();
	}

	@Override
	public void updateScore(Score currentScore) throws RemoteException{
		System.out.println(currentScore);
	}
	
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry(RMI_HOST, RMI_PORT);
			for(String boundName : registry.list()){
				System.out.println(boundName);
			}
			System.out.println("Registry Obtained");
			ScoreUpdater scoreUpdater = (ScoreUpdater)registry.lookup("rmi://"+RMI_HOST+":"+RMI_PORT+"/SimpleScoreUpdater");
			System.out.println("ScoreUpdater Obtained");
			scoreUpdater.addScoreBoard(new SimpleScoreBoard());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void turnOff() throws RemoteException {
		System.exit(0);
	}

}
