package main.game;
import java.util.ArrayList;

public class Warrior {

	public int strength = 15;
	public int speed = 5;
	public int stamina = 15;
	public int focus = 20;
	
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
