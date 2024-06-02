package battle.character.human;

import battle.character.type.SpecialFighter;

public class Spy extends SpecialFighter implements Human {
	public String hometown;
	
	public Spy(int maxHealth, int attackPower, String hometown) {
		super(maxHealth, attackPower);
		if(hometown == null || hometown.isEmpty()) {
			throw new IllegalArgumentException("hometown cannot be null or empty");
		}
		this.hometown = hometown;
	}
	
	@Override
	public String getHometown() {
		return hometown;
	}
}
