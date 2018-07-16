package main.game;

/**
 * Class to hold individual map tile.
 */
public class Cell {
	
	/** type of tile on the map. */
	SpaceType type;
	
	/** id of item on tile. */
	int itemID;
	
	/** id of monster on tile. */
	int monsterID;
	

	/**
	 * @param type the type of cell 
	 * @param itemID the id of item on tile
	 * @param monsterID the id of monster on tile
	 */
	Cell(final SpaceType type, final int itemID, final int monsterID) {
		this.type = type;
		this.itemID = itemID;
		this.monsterID = monsterID;
	}
	
}
