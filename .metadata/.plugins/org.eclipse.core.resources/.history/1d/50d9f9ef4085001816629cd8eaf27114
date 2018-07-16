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
	
	public void moveNorth() {
		playerLocationY = partition.moveNorth(playerLocationY, playerLocationX);
	}
	
	public void moveEast() {
		playerLocationX = partition.moveEast(playerLocationY, playerLocationX);
	}
	
	public void moveSouth() {
		playerLocationY = partition.moveSouth(playerLocationY, playerLocationX);
	}
	
	public void moveWest() {
		playerLocationX = partition.moveWest(playerLocationY, playerLocationX);
	}
	
	public boolean tileHasItem() {
		return partition.tileHasItem(playerLocationY, playerLocationX);
	}
	
	public boolean tileHasMonster() {
		return partition.tileHasMonster(playerLocationY, playerLocationX);
	}
	
	public int getItemID() {
		return partition.getItemID(playerLocationY, playerLocationX);
	}
	
	public int getMonsterID() {
		return partition.getMonsterID(playerLocationY, playerLocationX);
	}

}
