package main.game;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

/**
 * Class to handle battles between player and monsters.
 */
public class Battle {
	
	/** player stats. */
	private int playerStrength = 10, playerSpeed = 10, playerHealth = 100;
	
	/** enemy monster stats. */
	private int enemyStrength = 10, enemySpeed = 10, enemyHealth = 100;
	
	/** enemy monster id. */
	int enemyID = 0;
	
	/** enemy monster name. */
	String enemyName = "";
	
	/** player scaling value. */
	double playerScale = 0.0;
	
//	BattleChoices battleChoice;
//	boolean choiceHasChanged = false;
	
	/** xp from enemy value. */
	double xp = (double) enemyID;
	
	/** player wins value. */
	boolean playerWins;
	
	/** player and enemy damage values. */
	double playerDamage, enemyDamage;
	
	/** Player strength floor. */
	int playerStrengthFloor = playerStrength / 2;
	
	/** Enemy strength floor. */
	int enemyStrengthFloor = enemyStrength / 2;
	
	/** Enemy scaling. */
	double enemyScale = 1;

	
	/**
	 * @param warrior the player character
	 * @param enemyID the enemy monster id
	 * @throws IOException exception thrown for reading enemyinfo file
	 */	
	public Battle(final Warrior warrior, final int enemyID) throws IOException {
		this.playerStrength = warrior.strength;
		this.playerSpeed = warrior.speed;
		this.playerHealth = warrior.health;
		this.playerScale = warrior.scaling;
		
		this.enemyID = enemyID;
		
		Scanner in = new Scanner(new File("EnemyInfo.txt"));
		
		// This will read from enemy file, 
		// and put attributes into needed data types		
		try {
			while (in.nextInt() != enemyID) { // cycle thru lines til enemy id is seen
				in.nextLine();
			}
			this.enemyName = in.next();
			this.enemyStrength = in.nextInt();
			this.enemySpeed = in.nextInt();
			this.enemyHealth = in.nextInt();
		} finally {
			in.close();
		}
		
	}
	
	
	/**
	 * @return integer
	 */
	int startBattle() {		

		
		Scanner choiceSC = new Scanner(System.in);
		Random rand = new Random();
		int runFlag = 0;
		char choice = 0;
		
		System.out.println("\n\n" + enemyName + " encountered!\n");
		
//		Each turn is as follows:
//			both players make a shield or attack choice
//			if players are attacked, they use the dodge seed to dodge
//			dodge seeds are also used for run option
//			if attacked, scaling*strength is turned into health damage
//			if shield, damage taken is halved
//			strength goes down by a tenth each attack
//			TODO focus for regen?			
		while (playerHealth > 0 && enemyHealth > 0 && runFlag == 0) {

			System.out.println(turnHeader());
			
			choice = choiceSC.next().charAt(0);			
			//int enemyChoice = (int)Math.round( Math.random() );
			int enemyChoice = (rand.nextInt(3));
			enemyDamage = 0;
			playerDamage = 0;
			
			int playerDodgeSeed = rand.nextInt(playerSpeed) + 0;
			int enemyDodgeSeed = rand.nextInt(enemySpeed) + 0;
						
			
			//player chooses to run
			if (choice == 'f') {
				if (playerDodgeSeed > enemyDodgeSeed) {
					runFlag = 1;
				}
			}
			
			if (choice == 'a') {			
				System.out.println(playerAttack());
			}
			
			//enemy chooses to attack
			if (enemyChoice == 0) {			
				System.out.println(enemyName + " " + enemyAttack());
			}
			
			//player chooses to shield, decreases incoming damage
			if (choice == 's') {				
				System.out.println(playerShield());
			}
						
			//enemy chooses to shield, decreases incoming damage
			if (enemyChoice == 1) {
				System.out.println(enemyName + " " + enemyShield());
			}
			
			if (choice == 'd') {			
				System.out.println(playerRest());
			}
			
			//enemy chooses to rest, decreases incoming damage
			if (enemyChoice == 2) {
				System.out.println(enemyName + " " + enemyRest());
			}
			
			if (playerDodgeSeed >= enemyDodgeSeed) {
				enemyHealth -= (int) playerDamage;
			} else if (choice == 'a') {
				System.out.println("Enemy dodged!");
			}			
			
			if (playerDodgeSeed <= enemyDodgeSeed) {
				playerHealth -= (int) enemyDamage;
			} else if (enemyChoice == 0) {
				System.out.println("Player dodged!");
			}
			
			if (enemyHealth < 1) {
				playerWins = true;
			}
			
			// if both die at the same turn, player loses still
			if (playerHealth < 1) {
				playerWins = false;
			}
				
		}
		
		if (runFlag == 1) {
			return -1;
		}
			
		if (playerWins) {
			double xpGain =  Math.pow(xp + 1, .7);
			return (int) (xpGain * 3);
		} else {
			return 0;
		}		
	}
	
	/**
	 * Player chose attack.
	 * @return string for player choice
	 */
	public String playerAttack() {
		playerDamage = (double) playerStrength * playerScale;
		double degradation = (double) playerStrength * .15;
		
		if (playerStrength > playerStrengthFloor) {
			playerStrength -= (int) degradation;
		}
		
		return "Player chose attack";
	}
	
	/**
	 * Player chose shield.
	 * @return string for player choice
	 */
	public String playerShield() {
		enemyDamage *= .3;
		playerSpeed *= 1.15;
		
		return "Player chose shield";
	}
	
	/**
	 * Player chose rest.
	 * @return string for player choice
	 */
	public String playerRest() {
		enemyDamage *= .6;
		playerStrength *= 1.2;
		playerHealth *= 1.02;
		
		return "Player chose rest";
	}
	
	
	/**
	 * Enemy chose attack.
	 * @return string for enemy choice
	 */
	public String enemyAttack() {
		enemyDamage = (double) enemyStrength * enemyScale;
		double degradation = (double) enemyStrength * .15;
		
		if (enemyStrength > enemyStrengthFloor) {
			enemyStrength -= (int) degradation;
		}
		
		return "chose attack";
	}
	
	/**
	 * Enemy chose shield.
	 * @return string for enemy choice
	 */
	public String enemyShield() {
		playerDamage *= .3;
		enemySpeed *= 1.15;
		
		return "chose shield";
	}
	
	/**
	 * Enemy chose rest.
	 * @return string for enemy choice
	 */
	public String enemyRest() {
		playerDamage *= .6;
		enemyStrength *= 1.2;
		enemyHealth *= 1.02;
		
		return "chose rest";
	}
	
	/**
	 * Reprints the battle turn information.
	 * @return information for battle turn
	 */
	public String turnHeader() {
		String finalString = "";
		finalString += "\nPlayer health: " + playerHealth + "\n";
		finalString += enemyName + " health: " + enemyHealth + "\n";
		finalString += "What would you like to do?" + "\n";
		finalString += "A to attack, S to shield, D to rest, or F to flee\n";
		
		return finalString;
	}
	
	/**
	 * @param args none
	 * @throws IOException exception thrown during battle environment
	 */
	public static void main(final String[] args) throws IOException {
	}
}
