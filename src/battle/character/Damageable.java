package battle.character;

public interface Damageable {
	/**
	 * @return the maximum amount of health of the person
	 */
	public int getMaxHealth();
	
	/**
	 * @return the current of health of the person
	 */
	public int getHealth();

	/**
	 * @return the attack power of the person
	 */
	public int getAttackPower();
	
	/**
	 * @return true if the person is alive; otherwise false
	 */
	public boolean isAlive();
	
	/**
	 * Changes the health of the person by the specified amount. 
	 * If the specified amount is negative, the person takes damage.
	 * If the specified amount is positive, the person is healed.
	 * If the specified amount is zero, nothing happens.
	 * 
	 * The health of the person must stay between 0 and maxHealth (inclusive).
	 * In other words, if you give this person Integer.MIN_VALUE, it'll make 
	 * them have 0 health. If you give this person Integer.MAX_VALUE, it'll 
	 * make them have full health.
	 * 
	 * @param upHp how much to change the user's health by.
	 * @return the amount of health that occurred. It may be different than 
	 * the parameter if the person's life would have gone below 0 or above 
	 * maximum health
	 */
	public int incrementHealth(int upHp);
}
