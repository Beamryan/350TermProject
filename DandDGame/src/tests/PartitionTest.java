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
		partition = new Partition(10, 10, 0, 5, 5, player, 5, 1);
		playerX = 6;
		playerY = 5;
	}
	
	/**
	 * Test method for moving north.
	 */
	@Test
	public void testMoveNorth() {
		try {
			initialize();
			int initialPlayerY = playerY;
//			playerY = partition.moveNorth(playerY, playerX);
			assertTrue(playerY + 1 == initialPlayerY);

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
			int initialPlayerX = playerX;
//			playerX = partition.moveEast(playerY, playerX);
			assertTrue(playerX - 1 == initialPlayerX);

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
			int initialPlayerY = playerY;
//			playerY = partition.moveSouth(playerY, playerX);
			assertTrue(playerY - 1 == initialPlayerY);

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
			int initialPlayerX = playerX;
//			playerX = partition.moveWest(playerY, playerX);
			assertTrue(playerX + 1 == initialPlayerX);

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
			int monsterID = partition.getItemID(playerY, playerX);
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
