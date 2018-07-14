package main.game;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class Battle {
	int playerStrength, playerSpeed, playerStamina, playerFocus, playerHealth;
	int enemyStrength, enemySpeed, enemyStamina, enemyFocus, enemyHealth;
	int enemyID;
	String enemyName;
	
	public Battle(Warrior warrior, int enemyID) throws IOException {
		this.playerStrength = warrior.strength;
		this.playerSpeed = warrior.speed;
		this.playerStamina = warrior.stamina;
		this.playerFocus = warrior.focus;
		this.playerHealth = warrior.health;
		
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
		double playerScale = 1.5;
		double enemyScale = 0.7;
		
		Scanner choiceSC = new Scanner(System.in);
		Random rand = new Random();
		int runFlag = 0;
		
		
//		Each turn is as follows:
//			both players make a shield or attack choice
//			if players are attacked, they use the dodge seed to dodge
//			dodge seeds are also used for run option
//			if attacked, scaling*strength is turned into health damage
//			if shield, damage taken is halved
//			strength goes down by a tenth each attack
//			TODO focus for regen?			
		while(playerHealth>0 && enemyHealth>0 && runFlag == 0){
			System.out.println("Player health: " + playerHealth);
			System.out.println("Enemy health: " + enemyHealth);
			System.out.println("What would you like to do?");
			System.out.println("A to attack, S to shield, R to run");
			
			choice = choiceSC.next().charAt(0);			
			int enemyChoice = (int)Math.round( Math.random() );
			enemyDamage = 0;
			playerDamage = 0;
			
			int playerDodgeSeed = rand.nextInt(playerStamina) + 0;
			int enemyDodgeSeed = rand.nextInt(enemyStamina) + 0;
			
		
			System.out.println("Player chose " + choice);
			
			//info for user
			if(enemyChoice == 0){
				System.out.println(enemyName + " chose attack");
			}
			else{
				System.out.println(enemyName + " chose shield");
			}
			
			//player chooses to run
			if(choice == 'r'){
				if(playerDodgeSeed>enemyDodgeSeed)
					runFlag = 1;
			}
			
			//TODO strength degeneration after attack
			//player chooses to attack
			if(choice == 'a'){
				playerDamage = (double)playerStrength*playerScale;
				double degradation = (double)playerStrength *.15;
				
				if(playerStrength > playerStrengthFloor) playerStrength -= (int)degradation;			
			}
			
			//enemy chooses to attack
			if(enemyChoice == 0){
				enemyDamage = (double)enemyStrength*enemyScale;
				double degradation = (double)enemyStrength *.15;
				
				if(enemyStrength > enemyStrengthFloor) enemyStrength -= (int)degradation;	
			}
			
			//player chooses to shield, halve incoming damage
			if(choice == 's') enemyDamage *= .5;
			
			//enemy chooses to shield, halve incoming damage
			if(enemyChoice == 1) playerDamage *= .5;
			
			enemyHealth -= (int)playerDamage;
			playerHealth -= (int)enemyDamage;
			
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
		System.out.println("Player strength: " + testBattle.playerStrength);
		System.out.println("Player speed: " + testBattle.playerSpeed);
		System.out.println("Player stamina: " + testBattle.playerStamina);
		System.out.println("Player focus: " + testBattle.playerFocus);
		System.out.println("\nEnemy name: " + testBattle.enemyName);
		System.out.println("Enemy strength: " + testBattle.enemyStrength);
		System.out.println("Enemy speed: " + testBattle.enemySpeed);
		System.out.println("Enemy stamina: " + testBattle.enemyStamina);
		System.out.println("Enemy focus: " + testBattle.enemyFocus);
	}

	
	
	
	
	
	

}
