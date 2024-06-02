package battle.character.human;

import battle.character.type.Healer;

public class Medic extends Healer implements Human {
	public String hometown;

	public Medic(int healingPower, String hometown) {
		super(healingPower);
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
