package main.game;
import java.util.ArrayList;
import java.util.Scanner;

public class Warrior {

	public int strength = 15;
	public int speed = 5;
	public int stamina = 15;
	public int focus = 20;
	public int health = 150;
	
	public int xp = 10;
	public int level = 1;
	public int xpToNextLevel = level*15;
	
	public int waterTravelFlag;
	
	public int invenorySize = 20;
	public int[] inventory = new int[invenorySize];
	
	public void showInventory()
	{
		//TODO: Implemented descriptions for itemIDs. 
	}
	
	public boolean addItemToInventory(int itemID)
	{
		if(!isInventoryFull())
		{
			inventory[getNextEmptyInvntorySlot()] = itemID;
			return true;
		}
		return false;
	}
	
	public void levelUp(){
		Scanner choiceSC = new Scanner(System.in);
		char choice;
		double increment;
		
		System.out.println("\nNew level achieved! What stat would you like to level?");
		System.out.println("1 for strength, 2 for stamina, 3 for health");
		System.out.println("Current stats: " + strength + " " + stamina + " " + health);
		
		choice = choiceSC.next().charAt(0);
		
		if(choice == '1'){
			increment = (double)level*.05 + 2;
			strength += increment;
		}
		
		if(choice == '2'){
			increment = (double)level*.05 + 2;
			stamina += increment;
		}
		
		if(choice == '3'){
			increment = (double)level*.025 + 10;
			health += increment;
		}
		
		System.out.println("New stats: " + strength + " " + stamina + " " + health);
		System.out.println("Enter any input to continue");
		
		choice = choiceSC.next().charAt(0);
		
		xp -= xpToNextLevel;
		level++;
			
	}
	
	private int getNextEmptyInvntorySlot()
	{
		for(int i = 0; i < invenorySize; i++)
		{
			if(inventory[i] == 0)
			{
				return i;
			}
		}
		return invenorySize;
	}
	
	private boolean isInventoryFull()
	{
		int inventorySpace = getNextEmptyInvntorySlot();
		if(inventorySpace == invenorySize) return true;
		return false;
	
	}
	
	
}
