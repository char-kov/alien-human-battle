package battle.character.human;

import battle.character.Team;

public class HumanTeam extends Team {
	public HumanTeam(String teamName, Soldier s1, Soldier s2, Spy spy, Medic m) {
		super(teamName, s1, s2, spy, m);
	}
}
