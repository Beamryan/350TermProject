package main.game;

import java.util.Scanner;

/**
 * Class used to hold the warrior character.
 */
public class Rogue implements Character {

	/** the character strength. */
	public int strength = 100;
	
	/** the character speed. */
	public int speed = 25;
	
	/** the character stamina. */
	public int stamina = 20;
	
	/** the character focus. */
	public int focus = 15;
	
	/** the character health. */
	public int health = 100;
	
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
	public void levelUp() {
		Scanner choiceSC = new Scanner(System.in);
		char choice;
		double increment;
		
		System.out.println("\nNew level achieved! What stat would you like to level?");
		System.out.println("1 for strength, 2 for speed, 3 for health");
		System.out.println("Current stats: " + strength + " " + speed + " " + health);
		
		choice = choiceSC.next().charAt(0);
		
		if (choice == '1') {
			increment = (double) level * 1.05 + 1;
			strength += increment;
			xp -= xpToNextLevel; // these should be in here in case user enters invalid option
			level++;
		}
		
		if (choice == '2') {
			increment = (double) level * 1.05 + 1;
			speed += increment;
			xp -= xpToNextLevel;
			level++;
		}
		
		if (choice == '3') {
			increment = (double) level * 1.2 + 10;
			health += increment;
			xp -= xpToNextLevel;
			level++;
		}
		
		xpToNextLevel += level * 3;
		
		System.out.println("New stats: " + strength + " " + speed + " " + health);

					
	}	
}
