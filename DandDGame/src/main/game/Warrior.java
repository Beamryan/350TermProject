package main.game;
import java.util.Scanner;

/**
 * Class to handle the warrior character.
 */
public class Warrior {

	/** character strength. */
	public int strength = 15;

	/** character speed. */
	public int speed = 10;

	/** character health. */
	public int health = 150;

	/** character scaling. */
	public double scaling = 0;

	/** character experience. */
	public int xp = 10;

	/** character level. */
	public int level = 1;

	/** experience to next character level. */
	public int xpToNextLevel = 15;

	/** character inventory. */
	public Inventory inventory = new Inventory();	

	/**
	 * Method to handle character leveling up.
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
		choiceSC.close();
	}

	/**
	 * @return Inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * @return double
	 */
	public double getScaling() {
		return scaling;
	}		
}
