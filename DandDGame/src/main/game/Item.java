package main.game;

/**
 * Item class to hold different items.
 */
public class Item {
	
	/** the item ID. */
	int itemID;
	
	/** the item name. */
	String name;
	
	/** weapon scaling value. */
	int weaponScaling;	
	
	/** obtained weapon value. */
	int obtained = 0;
	
	/**
	 * @param itemID
	 * @param name
	 * @param weaponScaling
	 */
	public Item(final int itemID, final String name, final int weaponScaling) {
		this.itemID = itemID;
		this.name = name;
		this.weaponScaling = weaponScaling;
		this.obtained = 0;
	}
	
	/**
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return integer weaponScaling
	 */
	public int getScaling() {
		return this.weaponScaling;
	}
	
	/**
	 * @return integer obtained
	 */
	public int getObtained() {
		return this.obtained;
	}
	
	/**
	 * @return integer itemID
	 */
	public int getItemID() {
		return this.itemID;
	}
}
