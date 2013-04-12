package rmi.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ScoreUpdater extends Remote{
	
	public void addScoreBoard(ScoreBoard board) throws RemoteException;
	
}
