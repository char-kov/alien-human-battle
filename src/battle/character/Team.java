package battle.character;

import battle.character.type.Fighter;
import battle.character.type.Healer;
import battle.character.type.SpecialFighter;

public abstract class Team {
	public static int TEAM_SIZE = 4;
	
	private final String teamName;
	private final Fighter fighter1;
	private final Fighter fighter2;
	private final SpecialFighter specialFighter;
	private final Healer healer;
	
	public Team(String teamName, Fighter fighter1, Fighter fighter2, SpecialFighter specialFighter, Healer healer) {
		if(teamName == null || teamName.isEmpty()) {
			throw new IllegalArgumentException("team name cannot be empty or null");
		} else if(fighter1 == null || fighter2 == null || specialFighter == null || healer == null) {
			throw new IllegalArgumentException("combatants cannot be null");
		}
		this.teamName = teamName;
		this.fighter1 = fighter1;
		this.fighter2 = fighter2;
		this.specialFighter = specialFighter;
		this.healer = healer;
	}
	
	public boolean hasLost() {
		return !fighter1.isAlive() && !fighter2.isAlive() && !specialFighter.isAlive();
	}
	
	public String getTeamName() {
		return this.teamName;
	}
	
	public Combatant getCombatant(int i) {
		if(i == 0) {
			return getFighter1();
		} else if(i == 1) {
			return getFighter2();
		} else if(i == 2) {
			return getSpecialFighter();
		} else if(i == 3) {
			return getHealer();
		} else {
			return null;
		}
	}
	
	public Fighter getFighter1() {
		return this.fighter1;
	}
	
	public Fighter getFighter2() {
		return this.fighter2;
	}
	
	public SpecialFighter getSpecialFighter() {
		return this.specialFighter;
	}
	
	public Healer getHealer() {
		return this.healer;
	}
	
	@Override
	public String toString() {
		return getTeamName();
	}
}
