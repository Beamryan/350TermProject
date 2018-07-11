package main.game;
import java.io.*;

public class game {
	
	int defaultHeight = 50;
	int defaultWidth = 50;
	int playerLocationX = 2;
	int playerLocationY = 1;
	
	Partition partition;

	public game() throws IOException {
		partition = new Partition(defaultHeight, defaultWidth);
	}
	
	public game(int height, int width) throws IOException{ 
		defaultHeight = height;
		defaultWidth = width;
		partition = new Partition(defaultHeight, defaultWidth);
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
