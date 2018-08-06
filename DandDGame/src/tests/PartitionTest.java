package tests;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import main.game.*;

/**
 * Partition test class.
 */
public class PartitionTest {


	
	/** Players x location. */
	int playerX;
	
	/** Players y location. */
	int playerY;
	
	/** Test partition */
	Partition partition;
	
	/**
	 * Initializes the test variables.
	 * @throws IOException throws test exception
	 */
	private void initialize() throws IOException {
		Warrior player = new Warrior();
		playerX = 5;
		playerY = 4;
		partition = new Partition(10, 10, 0, 5, 5, player, playerX, playerY);

		partition.updatePartition();
	}
	
	/**
	 * Test method for moving north.
	 */
	@Test
	public void testMoveNorth() {
		try {
			initialize();
			int initialPlayerY = partition.getPlayerY();
			partition.moveNorth();
			assertTrue((partition.getPlayerY() + 1) == initialPlayerY);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test method for moving east.
	 */
	@Test
	public void testMoveEast() {
		try {
			initialize();
			int initialPlayerX = partition.getPlayerX();
			partition.moveEast();
			assertTrue(partition.getPlayerX() - 1 == initialPlayerX);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test method for moving south.
	 */
	@Test
	public void testMoveSouth() {
		try {
			initialize();
			int initialPlayerY = partition.getPlayerY();
			partition.moveSouth();
			assertTrue(partition.getPlayerY() - 1 == initialPlayerY);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test method for moving west.
	 */
	@Test
	public void testMoveWest() {
		try {
			initialize();
			int initialPlayerX = partition.getPlayerX();
			partition.moveWest();
			assertTrue(partition.getPlayerX() + 1 == initialPlayerX);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test method for getting item Id.
	 */
	@Test
	public void testGetItemID() {
		try {
			initialize();
			int itemID = partition.getItemID(playerY, playerX);
			assertTrue(itemID == 0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Test method for getting monster Id.
	 */
	@Test
	public void testGetMonsterID() {
		try {
			initialize();
			int monsterID = partition.getMonsterID();
			assertTrue(monsterID == 0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main method to run partition test.
	 * @param args none
	 */
	public static void main(final String[] args) {
	}
}
