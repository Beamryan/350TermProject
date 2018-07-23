package main.game;
import java.io.*;

/**
 * Game class used to attach gui to partition framework.
 */
public class Game {
	
	/** the default map height. */
	int defaultHeight = 20;
	
	/** the default map width. */
	int defaultWidth = 20;
	
	/** current player location in x. */
	int playerLocationX = 1;
	
	/** current player location in y. */
	int playerLocationY = 1;
	
	/** the player character. */
	Warrior player = new Warrior();
	
	/** the partition used to hold current map. */
	Partition partition;

	/**
	 * @throws IOException throws exception for creating partition from output file.
	 */
	public Game() throws IOException {
	}
	
	/**
	 * @param height the height of the partition
	 * @param width the width of the partition
	 * @throws IOException throws exception for reading from the output file
	 */
	public Game(final int height, final int width) throws IOException { 
		defaultHeight = height;
		defaultWidth = width;
	}
	
	/**
	 * Chance to move the play north from the current position if the north tile is valid.
	 * validity of the tile is checked in the Partition class
	 */
	public void moveNorth() {
		try {
			partition.moveNorth();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Chance to move the play north from the current position if the east tile is valid.
	 * validity of the tile is checked in the Partition class
	 */
	public void moveEast() {
		try {
			partition.moveEast();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Chance to move the play north from the current position if the south tile is valid.
	 * validity of the tile is checked in the Partition class
	 */
	public void moveSouth() {
		try {
			partition.moveSouth();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Chance to move the play north from the current position if the west tile is valid.
	 * validity of the tile is checked in the Partition class
	 */
	public void moveWest() {
		try {
			partition.moveWest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Check if the current tile has an item or not from the Partition class.
	 * @return true if the tile has item, false if the tile item value is 0.
	 */
	public void tileHasItem() {
		partition.doesTileHaveItem();
	}
	
	/**
	 * Check if the current tile has a monster or not from the Partition class.
	 * @return true if the tile has monster, false if the tile monster value is 0.
	 */
	public void tileHasMonster() {
		try {
			partition.doesTileHaveMonster();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	/**
//	 * Get the itemID of the current cell tile from the Partition class.
//	 * @return the ItemID
//	 */
//	public int getItemID() {
//		return partition.getItemID(playerLocationY, playerLocationX);
//	}
//	
//	/**
//	 * Get the monsterID of the current cell tile from the Partition class.
//	 * @return the monsterID
//	 */
//	public int getMonsterID() {
//		return partition.getMonsterID(playerLocationY, playerLocationX);
//	}

}
