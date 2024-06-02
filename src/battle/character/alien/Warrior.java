package battle.character.alien;

import battle.character.type.Fighter;

public class Warrior extends Fighter implements Alien {
	public String homeWorld;

	public Warrior(int maxHealth, int attackPower, String homeWorld) {
		super(maxHealth, attackPower);
		if(homeWorld == null || homeWorld.isEmpty()) {
			throw new IllegalArgumentException("homeWorld cannot be null or empty");
		}
		this.homeWorld = homeWorld;
	}
	
	@Override
	public String getHomeWorld() {
		return homeWorld;
	}
}
