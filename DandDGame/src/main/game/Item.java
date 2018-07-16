package main.game;

/**
 * Class to hold items found on game map.
 */
public class Item {
	
	/** the item id. */
	int itemID;
	
	/** the name of the item. */
	String name;
	
	/** the weapon scaling value. */
	int weaponScaling;	
	
	/** check to see if item is obtained. */
	int obtained = 0;
	
	/**
	 * @param itemID the item id
	 * @param name the name of the item
	 * @param weaponScaling the scaling of the item
	 */
	public Item(final int itemID, final String name, final int weaponScaling) {
		this.itemID = itemID;
		this.name = name;
		this.weaponScaling = weaponScaling;
		this.obtained = 0;
	}
}
