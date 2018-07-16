package main.game;

/**
 * Cell to hold tile values.
 */
public class Cell {
	
	/** holds enum of type for tile. */
	SpaceType type;
	
	/** holds item id of tile. */
	int itemID;
	
	/** holds monster id of tile. */
	int monsterID;	
	
	/**
	 * @param type
	 * @param itemID
	 * @param monsterID
	 */
	Cell(final SpaceType type, final int itemID, final int monsterID) {
		this.type = type;
		this.itemID = itemID;
		this.monsterID = monsterID;
	}
	
	/**
	 * @return SpaceType
	 */
	public SpaceType getSpaceType() {
		return this.type;
	}
	
	/**
	 * @return integer
	 */
	public int getItemID() {
		return this.itemID;
	}
	
	/**
	 * @return integer
	 */
	public int getMonsterID() {
		return this.monsterID;
	}
	
}
