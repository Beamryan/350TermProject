package main.game;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class Battle {
	int playerStrength, playerSpeed, playerStamina, playerFocus, playerHealth;
	int enemyStrength, enemySpeed, enemyStamina, enemyFocus, enemyHealth;
	int enemyID;
	String enemyName;
	double playerScale;
	
	public Battle(Warrior warrior, int enemyID) throws IOException {
		this.playerStrength = warrior.strength;
		this.playerSpeed = warrior.speed;
		this.playerStamina = warrior.stamina;
		this.playerFocus = warrior.focus;
		this.playerHealth = warrior.health;
		this.playerScale = warrior.scaling;
		
		this.enemyID = enemyID;
		
		Scanner in = new Scanner(new File("EnemyInfo.txt"));
		
		// This will read from enemy file, 
		// and put attributes into needed data types
		
		try{
			while(in.nextInt() != enemyID){ // cycle thru lines til enemy id is seen
				in.nextLine();
			}
			this.enemyName = in.next();
			this.enemyStrength = in.nextInt();
			this.enemySpeed = in.nextInt();
			this.enemyStamina = in.nextInt();
			this.enemyFocus = in.nextInt();
			this.enemyHealth = in.nextInt();
		}
		finally{
			in.close();
		}
		
	}
	
	
	
	int startBattle(){
		// TODO need stuff for weapons and scaling
		
		int xp = this.enemyID;
		boolean playerWins = true;
		char choice = 0;
		double playerDamage, enemyDamage;
		int playerStrengthFloor = playerStrength/2;
		int enemyStrengthFloor = enemyStrength/2;
		
		// test weapon scaling...
		double enemyScale = 1.5;
		
		Scanner choiceSC = new Scanner(System.in);
		Random rand = new Random();
		int runFlag = 0;
		
		System.out.println("\n\n" + enemyName + " encountered!\n");
		
//		Each turn is as follows:
//			both players make a shield or attack choice
//			if players are attacked, they use the dodge seed to dodge
//			dodge seeds are also used for run option
//			if attacked, scaling*strength is turned into health damage
//			if shield, damage taken is halved
//			strength goes down by a tenth each attack
//			TODO focus for regen?			
		while(playerHealth>0 && enemyHealth>0 && runFlag == 0){
			System.out.println("\nPlayer health: " + playerHealth);
			System.out.println(enemyName + " health: " + enemyHealth);
			System.out.println("What would you like to do?");
			System.out.println("A to attack, S to shield, D to rest, or F to flee\n");
			
			choice = choiceSC.next().charAt(0);			
			//int enemyChoice = (int)Math.round( Math.random() );
			int enemyChoice = (rand.nextInt(3));
			enemyDamage = 0;
			playerDamage = 0;
			
			int playerDodgeSeed = rand.nextInt(playerSpeed) + 0;
			int enemyDodgeSeed = rand.nextInt(enemySpeed) + 0;
						
			
			//player chooses to run
			if(choice == 'f'){
				if(playerDodgeSeed>enemyDodgeSeed)
					runFlag = 1;
			}
			
			//TODO strength degeneration after attack
			//player chooses to attack
			if(choice == 'a'){
				playerDamage = (double)playerStrength*playerScale;
				double degradation = (double)playerStrength *.15;
				
				if(playerStrength > playerStrengthFloor) playerStrength -= (int)degradation;
				System.out.println("Player chose attack");
			}
			
			//enemy chooses to attack
			if(enemyChoice == 0){
				enemyDamage = (double)enemyStrength*enemyScale;
				double degradation = (double)enemyStrength *.15;
				
				if(enemyStrength > enemyStrengthFloor) enemyStrength -= (int)degradation;
				System.out.println(enemyName + " chose attack");
			}
			
			//player chooses to shield, decreases incoming damage
			if(choice == 's'){
				enemyDamage *= .3;
				playerSpeed *= 1.15;
				System.out.println("Player chose shield");
			}
						
			//enemy chooses to shield, decreases incoming damage
			if(enemyChoice == 1){
				playerDamage *= .3;
				enemySpeed *= 1.15;
				System.out.println(enemyName + " chose shield");
			}
			
			if(choice == 'd'){
				enemyDamage *= .6;
				playerStrength *= 1.2;
				playerHealth *= 1.02;
				System.out.println("Player chose rest");
			}
			
			//enemy chooses to rest, decreases incoming damage
			if(enemyChoice == 2){
				playerDamage *= .6;
				enemyStrength *= 1.2;
				enemyHealth *= 1.02;
				System.out.println(enemyName + " chose rest");
			}
			
			if(playerDodgeSeed>=enemyDodgeSeed){
				enemyHealth -= (int)playerDamage;
			}
			else if(choice == 'a') System.out.println("Enemy dodged!");
			
			
			if(playerDodgeSeed<=enemyDodgeSeed){
				playerHealth -= (int)enemyDamage;
			}
			else if(enemyChoice == 0) System.out.println("Player dodged!");


			
			if(enemyHealth<1){
				playerWins = true;
			}
			
			// if both die at the same turn, player loses still
			if(playerHealth<1){
				playerWins = false;
			}
				
		}
		
		if(runFlag == 1){
			return -1;
		}
			
		if(playerWins){
			return xp*5;
		}
		else{
			return 0;
		}
	}
	
	public static void main(String args[]) throws IOException {
		Warrior warrior = new Warrior();
		Battle testBattle = new Battle(warrior, 1);
		
	}

	
}
