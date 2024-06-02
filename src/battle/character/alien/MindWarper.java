package battle.character.alien;

import battle.character.type.SpecialFighter;

public class MindWarper extends SpecialFighter implements Alien {
	public String homeWorld;

	public MindWarper(int maxHealth, int attackPower, String homeWorld) {
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
