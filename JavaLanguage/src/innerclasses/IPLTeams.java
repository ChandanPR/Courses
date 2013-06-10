package innerclasses;

public enum IPLTeams {
	BANGALORE("RCB"),
	
	CHENNAI("CSK"){
		@Override
		public String toString() {
			return this.teamName;
		}
	};
	
	String teamName;
	IPLTeams(String teamName){
		this.teamName = teamName;
	}
	

}
