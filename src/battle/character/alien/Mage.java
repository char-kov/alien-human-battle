package battle.character.alien;

import battle.character.type.Healer;

public class Mage extends Healer implements Alien {
	public String homeWorld;

	public Mage(int healingPower, String homeWorld) {
		super(healingPower);
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
