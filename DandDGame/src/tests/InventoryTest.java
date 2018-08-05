package tests;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import main.game.*;

/**
 * Test class for inventory managment.
 */
public class InventoryTest {

	/**
	 * The character inventory.
	 */
	private Inventory inventory;
	
	/**
	 * Initializes new inventory for testing.
	 */
	private void initialize() {
		inventory = new Inventory();
	}
	
	/**
	 * Tests adding item to inventory.
	 */
	@Test
	public void testAddItem() {
		initialize();
		inventory.addItemToInventory(1);
		assertTrue(inventory.inventory[0] != 0);
		assertTrue(inventory.inventory[1] == 0);
	}
	
	/**
	 * Tests adding item to inventory.
	 */
	@Test
	public void testGetItemName() {
		initialize();
		inventory.addItemToInventory(1);
		assertTrue(inventory.getItemName(inventory.inventory[0]) == "Potato");
	}
	
	/**
	 * Tests getting item scaling.
	 */
	@Test
	public void testGetScaling() {
		initialize();
		inventory.addItemToInventory(1);
		try {
			assertTrue(inventory.getScaling() == 1.00);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Main test method.
	 * @param args nothing here
	 */
	public static void main(final String[] args) {
	}
}
