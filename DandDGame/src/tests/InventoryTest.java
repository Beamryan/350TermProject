package tests;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import main.game.*;

/**
 * Test class for inventory management.
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
	 * Tests the swaping of items in inventory. Item slot 0 is the users current item. 
	 */
	@Test 
	public void testSwapItems() {
		initialize();
		inventory.addItemToInventory(1); //potato
		inventory.addItemToInventory(2); //stone shovel
		assertTrue(inventory.inventory[0] == 1); //slot 0 is the current item
		inventory.setItemToCurrent(1); //sets stone shovel to current. 
		assertTrue(inventory.inventory[0] == 2); //slot 0 is the current item
	}
	
	/**
	 * Main test method.
	 * @param args nothing here
	 */
	public static void main(final String[] args) {
	}
}
