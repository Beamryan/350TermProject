package main.game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Inventory {

	public int invenorySize = 20;
	public int[] inventory;
	double currentScaling;
	
	
	public Inventory()
	{
		inventory = new int[invenorySize];
	}
	
	public int getCurrentItem()
	{
		return inventory[0];
	}
	
	public void showInventory()
	{
		int inventorySize = getNextEmptyInvntorySlot();
		//Added -2 so that quest item isnt shown
		for(int i = 0; i < inventorySize; i++)
		{
			System.out.println("Inventory Slot " + i +": " + getItemName(inventory[i]));
		}
	}
	
	public void setItemToCurrent(int itemIndex)
	{
		int oldCurrentItem = inventory[0];
		inventory[0] = inventory[itemIndex];
		inventory[itemIndex] = 0;
		
		addItemToInventory(oldCurrentItem);
	}
	
	public boolean addItemToInventory(int itemID)
	{
		// special item for quest to travel on water
		if(itemID == 19){
			inventory[19] = 1;
			System.out.println("Got quest item: Pool Floaty");
			System.out.println("You can now travel on water!");
		}
		else if(!isInventoryFull())
		{
			inventory[getNextEmptyInvntorySlot()] = itemID;
			System.out.println("Got item: " + getItemName(itemID));
			System.out.println("Press e to equip");
			return true;
		}
		return false;
	}
	
	public double getScaling() throws IOException {
		Scanner itemSC = new Scanner(new File("ItemInfo.txt"));
		double scaling = 0;
		String name;
		
		try{
			//Cycle through item info until the right itemID is found
			while(itemSC.nextInt() != inventory[0]){ // cycle thru lines til enemy id is seen
				itemSC.nextLine();
			}
			name = itemSC.next();
			scaling = itemSC.nextDouble();

		}
		finally{
			itemSC.close();			
		}
		System.out.println("Weapon scaling is " + scaling);
		return scaling;
	}
	
	private int getNextEmptyInvntorySlot()
	{
		for(int i = 0; i < invenorySize-2; i++)
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
	
	private String getItemName(int itemID)
	{
		String itemName = null;
		switch(itemID)
		{
			case 0:
				itemName = "Empty";
				break;
			case 1:
				itemName = "Potato";
				break;
			case 2:
				itemName = "Stone-Shovel";
				break;
			case 3:
				itemName = "Wooden-Stick";
				break;
			case 4:
				itemName = "Wooden-Stick-With-Nail";
				break;
			case 5:
				itemName = "Iron-Shovel";
				break;
			case 6:
				itemName = "Stone-Sword";
				break;
			case 7:
				itemName = "";
				break;
			case 8:
				itemName = "";
				break;
			case 9:
				itemName = "";
				break;
			case 10:
				itemName = "";
				break;
			case 11:
				itemName = "";
				break;
			case 12:
				itemName = "";
				break;
			case 13:
				itemName = "";
				break;
			case 14:
				itemName = "";
				break;
			case 15:
				itemName = "";
				break;
			case 16:
				itemName = "";
				break;
		}
		return itemName;
	}
}
