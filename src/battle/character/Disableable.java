package battle.character;

public interface Disableable {
	/**
	 * If the user is disabled, this deprecates the disabled counter by one.
	 * Otherwise, nothing happens
	 */
	public void skipTurn();
	
	/**
	 * Tells whether or not the person is disabled
	 * 
	 * @return true if the person is disabled; otherwise false
	 */
	public boolean isDisabled();
}
