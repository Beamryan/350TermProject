package main.game;

public class Item {
	int itemID;
	String name;
	int weaponScaling;	
	int obtained = 0;
	
	public Item(int itemID, String name, int weaponScaling){
		this.itemID = itemID;
		this.name = name;
		this.weaponScaling = weaponScaling;
		this.obtained = 0;
	}
}
