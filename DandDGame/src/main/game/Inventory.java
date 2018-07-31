package main.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTextArea;

/**
 * Class to hold inventory of the player.
 */
public class Inventory {

	/** inventory size. */
	public int inventorySize = 20;
	
	/** inventory of the player. */
	public int[] inventory;
	
	/** current armor inventory of the player. 
	 *  index 0 -> helmet
	 *  index 1 -> body
	 *  index 2 -> legs
	 *  index 3 -> feets
	 */
	public int[] armorInventory;
	
	/** current item scaling. */
	double currentScaling;
	
	
	/**
	 * inventory used to hold character items.
	 */
	public Inventory() {
		inventory = new int[inventorySize];
		armorInventory = new int[4];
	}
	
	/**
	 * @return integer the current item
	 */
	public int getCurrentItem() {
		return inventory[0];
	}
	
	/**
	 * Shows the character inventory.
	 */
	public void showInventory() {
		int inventorySize = getNextEmptyInvntorySlot();
		//Added -2 so that quest item isnt shown
		for (int i = 0; i < inventorySize; i++) {
			if(inventory[i] < 19) {
				System.out.println("Inventory Slot " + i + ": Item: " + getItemName(inventory[i]));
			}
			else if(inventory[i] > 19) {
				System.out.println("Inventory Slot " + i + ": Armor: " + getItemName(inventory[i]));
			}
		}
	}
	public void showInventory(JTextArea textArea) {
		String inventoryString = "";
		int inventorySize = getNextEmptyInvntorySlot();
		//Added -2 so that quest item isnt shown
		for (int i = 0; i < inventorySize; i++) {
			if(inventory[i] < 19) {
				inventoryString = inventoryString + "Inventory Slot " + i + ": Item: " + getItemName(inventory[i]) + "\n";
//				System.out.println("Inventory Slot " + i + ": Item: " + getItemName(inventory[i]));
			}
			else if(inventory[i] > 19) {
				inventoryString = inventoryString + "Inventory Slot " + i + ": Item: " + getItemName(inventory[i]) + "\n";
//				System.out.println("Inventory Slot " + i + ": Armor: " + getItemName(inventory[i]));
			}
		}
		textArea.setText(inventoryString);
	}
	
	/**
	 * Shows the armor inventory of the character
	 */
	public void showArmorInventory() {
		for(int i = 0; i < 4; i++) {
			System.out.println("Armor Slot " + i + ": " + armorInventory[i]);
		}
	}
	
	/**
	 * 
	 */
	public void showArmorInventory(JTextArea textArea) {
		String inventoryString = "";
		for(int i = 0; i < 4; i++) {
			inventoryString = inventoryString + "Armor Slot " + i + ": " + armorInventory[i] + "\n";
//			System.out.println("Armor Slot " + i + ": " + armorInventory[i]);
		}
		textArea.setText(inventoryString);
	}
	
	/**
	 * @param itemIndex the current item index
	 */
	public void setItemToCurrent(final int itemIndex) {
		if(inventory[itemIndex] < 19) {		
			int oldCurrentItem = inventory[0];
			inventory[0] = inventory[itemIndex];
			inventory[itemIndex] = 0;
		
			addItemToInventory(oldCurrentItem);
		}
	}
	
	/**
	 * Armor items should be above index 20 and also x0 -> helmets, x1 -> body, x2 -> legs, x3 -> feets.
	 * @param armorIndex
	 */
	public void setArmorItemToCurrent(final int armorIndexID) {
		if(armorInventory[armorIndexID] > 19) {
			int itemType = getArmorItemType(armorIndexID);
			int oldCurrentItem = armorInventory[itemType];
			armorInventory[itemType] = armorInventory[armorIndexID];
			inventory[armorIndexID] = 0; //clears the inventory space to eliminate duplicate items
		
			addItemToInventory(oldCurrentItem);
		}
	}
	
	/**
	 * @param itemID the added item id
	 * @return boolean
	 */
	public boolean addItemToInventory(final int itemID) {
		// special item for quest to travel on water
		if (itemID == 19) {
			inventory[19] = 1;
			System.out.println("Got quest item: Pool Floaty");
			System.out.println("You can now travel on water!");
		} else if (!isInventoryFull() && !isItemInInventory(itemID)) {
			inventory[getNextEmptyInvntorySlot()] = itemID;
			return true;
		}
		return false;
	}
	
	/**
	 * @return double the scaling value
	 * @throws IOException throws exception for reading item index
	 */
	public double getScaling() throws IOException {
		Scanner itemSC = new Scanner(new File("ItemInfo.txt"));
		double scaling = 0;
		String name;
		
		try {
			//Cycle through item info until the right itemID is found
			while (itemSC.nextInt() != inventory[0]) { // cycle thru lines til enemy id is seen
				itemSC.nextLine();
			}
			name = itemSC.next();
			scaling = itemSC.nextDouble();

		} finally {
			itemSC.close();			
		}
		System.out.println(name + " scaling is " + scaling);
		return scaling;
	}
	
	/**
	 * Gets the armor scaling values.
	 * @return double[] array of scaling values corresponding to item type slot position..
	 * @throws IOException
	 */
	public double[] getArmorScaling() throws IOException {
		double[] armorScalingValues = new double[4];
		Scanner itemSC = new Scanner(new File("ItemInfo.txt"));
		double scaling = 0;
		String name;
		
		try {
			for(int i = 0; i < 4; i++) {
				//Cycle through item info until the right itemID is found
				while (itemSC.nextInt() != armorInventory[i]) { // cycle thru lines til enemy id is seen
					itemSC.nextLine();
				}
				name = itemSC.next();
				scaling = itemSC.nextDouble();
				
				armorScalingValues[i] = scaling;
				System.out.println(name + " scaling is " + scaling);
			}
			
		} finally {
			itemSC.close();			
		}
		return armorScalingValues;
	}
	
	/**
	 * @return integer the next open slot
	 */
	public int getNextEmptyInvntorySlot() {
		for (int i = 0; i < inventorySize; i++) {
			if (inventory[i] == 0) {
				return i;
			}
		}
		return inventorySize;
	}
	
	
	/**
	 * @return boolean is the inventory full
	 */
	private boolean isInventoryFull() {
		int inventorySpace = getNextEmptyInvntorySlot();
		if (inventorySpace == inventorySize) {
			return true;
		}
		return false;
	
	}
	
	/**
	 * Checks if an item is already in the inventory.
	 * @param itemID 
	 * @return true if the itemID is in the inventory, else false.
	 */
	private boolean isItemInInventory(final int itemID) {
		int inventorySize = getNextEmptyInvntorySlot();
		for(int i = 0; i < inventorySize; i++) {
			if(itemID == inventory[i]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return integer the inventory size
	 */
	public int getInventorySize() {
		return this.inventorySize;
	}
	
	/**
	 * 0-18 will be weapons, 19 quest item, 20+ will be armor
	 * @param itemID the item id
	 * @return String the item name
	 */
	public String getItemName(final int itemID) {
		String itemName = null;
		switch (itemID) {
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
				itemName = "Iron-Sword";
				break;
			case 8:
				itemName = "Knight-Sword";
				break;
			case 9:
				itemName = "GreatSword";
				break;
			case 10:
				itemName = "BattleAxe";
				break;
			case 11:
				itemName = "War-Hammer";
				break;
			case 12:
				itemName = "BFS";
				break;
			case 13:
				itemName = "GreatAxe";
				break;
			case 14:
				itemName = "Zweihander";
				break;
			case 15:
				itemName = "Ultra-GreatSword";
				break;
			case 16:
				itemName = "Master-GreatSword";
				break;
			case 17:
				itemName = "Wooden-Ruler";
				break;
			case 20:
				itemName = "Bucket Helmet";
				break;
			case 21:
				itemName = "Leather Vest";
				break;
			case 22:
				itemName = "Leather Pants";
				break;
			case 23:
				itemName = "Leather Boots";
				break;
			case 30:
				itemName = "Iron Helmet";
				break;
			case 31:
				itemName = "Iron Chest Piece";
				break;
			case 32:
				itemName = "Iron Pants";
				break;
			case 33:
				itemName = "Iron Boots";
				break;
				
			default:
				itemName = "";
				break;
		}
		return itemName;
	}
	
	private int getArmorItemType(final int armorIndexID) {
		int itemType = 0;
		if(armorIndexID >= 20 && armorIndexID < 30) {
			itemType = armorIndexID - 20;
		}
		if(armorIndexID >= 30 && armorIndexID < 40) {
			itemType = armorIndexID - 30;
		}
		if(armorIndexID >= 40 && armorIndexID < 50) {
			itemType = armorIndexID - 40;
		}
		if(armorIndexID >= 50 && armorIndexID < 60) {
			itemType = armorIndexID - 50;
		}
		return itemType;
	}
}
