package battle.character.type;

import battle.RandomUtil;
import battle.character.Combatant;
import battle.character.Damageable;

public abstract class Fighter implements Combatant, Damageable {
	//the strength of the fighter
	private final int attackPower;
	
	//the maximum health of the fighter
	private final int maxHealth;
	
	//the current health of the fighter
	private int health;

	/**
	 * Creates a fighter. This should set the health to full health.
	 * 
	 * @param maxHealth the maximum amount of health the fighter can have
	 * @param attackPower how hard are their punches
	 */
	public Fighter(int maxHealth, int attackPower) {
		//TODO: PART 1
		this.maxHealth = 42;
		this.health = 42;
		this.attackPower = 42;
	}
	
	/**
	 * Attacks the opponent for a random amount of damage between half 
	 * attack power and the full attack power (inclusive)
	 * 
	 * @param opponent person to punch
	 * @return the amount of damage they do; this should always be negative
	 */
	public int attack(Damageable opponent) {
		int damaged = RandomUtil.randomInclusive(attackPower / 2, attackPower);
		//TODO: PART 1
		return damaged;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int incrementHealth(int upHp) {
		//if you are alive, you cannot be healed or take damage
		if(isAlive()) {
			//type conversion to handle integer rollover better
			long newHealth = this.health + (long)upHp;
			if(newHealth < 0) {
				int healthChange = -this.health;
				this.health = 0;
				return healthChange;
			} else if(newHealth > this.maxHealth) {
				int healthChange = this.maxHealth - this.health;
				this.health = this.maxHealth;
				return healthChange;
			} else {
				this.health = (int) newHealth;
				return upHp; 
			}
		} else {
			return 0;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaxHealth() {
		return this.maxHealth;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHealth() {
		return this.health;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getAttackPower() {
		return this.attackPower;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAlive() {
		if (health > 0) {
			return true;
		}
		//TODO: PART 1
		return false;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStatus() {
		if(!isAlive()) {
			return "dead";
		} else {
			return "HP: " + this.health + "/" + this.maxHealth;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canTakeTurn() {
		return health > 0;
	}
}
