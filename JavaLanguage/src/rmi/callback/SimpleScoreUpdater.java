package rmi.callback;

import static rmi.RMIConstants.RMI_HOST;
import static rmi.RMIConstants.RMI_PORT;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimpleScoreUpdater extends UnicastRemoteObject implements
		ScoreUpdater {

	private static final long serialVersionUID = -4838227840186416390L;

	List<ScoreBoard> scoreBoards = new CopyOnWriteArrayList<>();

	protected SimpleScoreUpdater() throws RemoteException {
		super();
	}

	@Override
	public void addScoreBoard(ScoreBoard board) throws RemoteException {
		scoreBoards.add(board);
		System.out.println(scoreBoards);
	}

	private void updateScoreBoard() throws IOException {
		while (true) {
			System.out.println("Updating Score Board");
			Random random = new Random(System.currentTimeMillis());
			for (ScoreBoard scoreBoard : scoreBoards) {
				try {
					Score currentScore = new Score(random.nextInt(),
							random.nextInt(), random.nextInt());
					System.out.println("Score to be updated : " + currentScore);
					scoreBoard.updateScore(currentScore);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			System.setProperty("java.rmi.server.hostname", RMI_HOST);
			SimpleScoreUpdater server = new SimpleScoreUpdater();
			Registry registry = LocateRegistry.getRegistry(RMI_PORT);
			String url = "rmi://" + RMI_HOST + ":" + RMI_PORT
					+ "/SimpleScoreUpdater";
			System.out.println("URL to bind : " + url);
			registry.rebind(url, server);
			server.updateScoreBoard();
			System.err.println("SimpleScoreUpdater is running");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
