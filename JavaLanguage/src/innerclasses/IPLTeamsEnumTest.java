package innerclasses;

import java.util.EnumSet;

public class IPLTeamsEnumTest {
	
	public static void main(String[] args) {
		EnumSet<IPLTeams> iplTeams = EnumSet.allOf(IPLTeams.class);
		for(IPLTeams iplTeam : iplTeams){
			System.out.println(iplTeam);
		}
	}

}
