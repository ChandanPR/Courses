package rmi.callback;

import java.io.Serializable;

public class Score implements Serializable{

	private static final long serialVersionUID = -3490487490777458747L;
	
	public final int runs;
	public final int overs;
	public final int wickets;
	
	public Score(int runs, int overs, int wickets){
		this.runs = runs;
		this.overs = overs;
		this.wickets = wickets;
	}

	@Override
	public String toString() {
		return "Score [runs=" + runs + ", overs=" + overs + ", wickets="
				+ wickets + "]";
	}
	

}
