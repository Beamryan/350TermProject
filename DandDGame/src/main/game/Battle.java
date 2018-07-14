package main.game;
import java.io.*;
import java.util.Scanner;

public class Battle {
	int playerStrength, playerSpeed, playerStamina, playerFocus;
	int enemyStrength, enemySpeed, enemyStamina, enemyFocus;
	int enemyID;
	String enemyName;
	
	public Battle(Warrior warrior, int enemyID) throws IOException {
		this.playerStrength = warrior.strength;
		this.playerSpeed = warrior.speed;
		this.playerStamina = warrior.stamina;
		this.playerFocus = warrior.focus;
		
		this.enemyID = enemyID;
		
		Scanner in = new Scanner(new File("EnemyInfo.txt"));
		
		try{
			while(in.nextInt() != enemyID){
				in.nextLine();
			}
			this.enemyName = in.next();
			this.enemyStrength = in.nextInt();
			this.enemySpeed = in.nextInt();
			this.enemyStamina = in.nextInt();
			this.enemyFocus = in.nextInt();
		}
		finally{
			in.close();
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

	
	
	
	
	
	// TODO need stuff for weapons and scaling

}
