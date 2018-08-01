package main.game;

public interface Character {
	
	/** the character strength. */
	public int strength = 15;
	
	/** the character speed. */
	public int speed = 15;
	
	/** the character stamina. */
	public int stamina = 15;
	
	/** the character focus. */
	public int focus = 15;
	
	/** the character health. */
	public int health = 150;
	
	/** the character scaling. */
	public double scaling = 0;
	
	/** the current character experience. */
	public int xp = 14;
	
	/** the current character level. */
	public int level = 1;
	
	/** the current experience to next character level. */
	public int xpToNextLevel = 15;
		
	/** the player character inventory. */
	public Inventory inventory = new Inventory();	
	
	/**
	 * Method used when character has leveled up.
	 */
	public void levelUp();
}
