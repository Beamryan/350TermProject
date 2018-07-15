package main.game;
import java.util.ArrayList;
import java.util.Scanner;

public class Warrior {

	public int strength = 20;
	public int speed = 10;
	public int stamina = 15;
	public int focus = 20;
	public int health = 150;
	public double scaling = 0;
	
	public int xp = 10;
	public int level = 1;
	public int xpToNextLevel = level*15;
		
	public Inventory inventory = new Inventory();	

	

	
	public void levelUp(){
		Scanner choiceSC = new Scanner(System.in);
		char choice;
		double increment;
		
		System.out.println("\nNew level achieved! What stat would you like to level?");
		System.out.println("1 for strength, 2 for speed, 3 for health");
		System.out.println("Current stats: " + strength + " " + speed + " " + health);
		
		choice = choiceSC.next().charAt(0);
		
		if(choice == '1'){
			increment = (double)level*1.25 + 2;
			strength += increment;
			xp -= xpToNextLevel; // these should be in here in case user enters invalid option
			level++;
		}
		
		if(choice == '2'){
			increment = (double)level*1.05 + 2;
			speed += increment;
			xp -= xpToNextLevel;
			level++;
		}
		
		if(choice == '3'){
			increment = (double)level*2.0 + 10;
			health += increment;
			xp -= xpToNextLevel;
			level++;
		}
		
		System.out.println("New stats: " + strength + " " + speed + " " + health);
		System.out.println("Enter any input to continue");
		
		choice = choiceSC.next().charAt(0);
		
		
			
	}
	

	
	
}
