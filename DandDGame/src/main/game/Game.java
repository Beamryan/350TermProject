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
		GameWindow.main(null);
		Partition.main(null);
	}
	
	/**
	 * Chance to move the play north from the current position if the north tile is valid.
	 * validity of the tile is checked in the Partition class
	 */
	public void moveNorth() {
		playerLocationY = partition.moveNorth(playerLocationY, playerLocationX);
	}

	/**
	 * Chance to move the play north from the current position if the east tile is valid.
	 * validity of the tile is checked in the Partition class
	 */
	public void moveEast() {
		playerLocationX = partition.moveEast(playerLocationY, playerLocationX);
	}
	
	/**
	 * Chance to move the play north from the current position if the south tile is valid.
	 * validity of the tile is checked in the Partition class
	 */
	public void moveSouth() {
		playerLocationY = partition.moveSouth(playerLocationY, playerLocationX);
	}
	
	/**
	 * Chance to move the play north from the current position if the west tile is valid.
	 * validity of the tile is checked in the Partition class
	 */
	public void moveWest() {
		playerLocationX = partition.moveWest(playerLocationY, playerLocationX);
	}
	
	/**
	 * Check if the current tile has an item or not from the Partition class.
	 * @return true if the tile has item, false if the tile item value is 0.
	 */
	public boolean tileHasItem() {
		return partition.tileHasItem(playerLocationY, playerLocationX);
	}
	
	/**
	 * Check if the current tile has a monster or not from the Partition class.
	 * @return true if the tile has monster, false if the tile monster value is 0.
	 */
	public boolean tileHasMonster() {
		return partition.tileHasMonster(playerLocationY, playerLocationX);
	}
	
	/**
	 * Get the itemID of the current cell tile from the Partition class.
	 * @return the ItemID
	 */
	public int getItemID() {
		return partition.getItemID(playerLocationY, playerLocationX);
	}
	
	/**
	 * Get the monsterID of the current cell tile from the Partition class.
	 * @return the monsterID
	 */
	public int getMonsterID() {
		return partition.getMonsterID(playerLocationY, playerLocationX);
	}

}
