package rmi.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ScoreBoard extends Remote{
	
	public void updateScore(Score currentScore) throws RemoteException;
	
	public void turnOff() throws RemoteException;

}
