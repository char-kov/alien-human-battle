package battle.character;

public interface Combatant {
	/**
	 * Makes a string representing the state of the combatant.  
	 * For example, it returns "dead" if the combatant has 0 health
	 * 
	 * @return a string representing the state of the combatant
	 */
	public String getStatus();
	
	/**
	 * Is this combatant able to take a turn? They won't be able to if they
	 * are disabled or dead.
	 * 
	 * @return true if the combatant is able to take an action; otherwise false
	 */
	public boolean canTakeTurn();	
}
