package tests;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import main.game.*;

public class InventoryTest {

	private Inventory inventory;
	
	private void Initialize(){
		inventory = new Inventory();
	}
	
	@Test
	public void TestAddItem(){
		Initialize();
		inventory.addItemToInventory(1);
		assertTrue(inventory.inventory[0] != 0);
		assertTrue(inventory.inventory[1] == 0);
	}
	
	@Test
	public void TestGetItemName(){
		Initialize();
		inventory.addItemToInventory(1);
		assertTrue(inventory.getItemName(inventory.inventory[0]) == "Potato");
	}
	
	@Test
	public void TestGetScaling()
	{
		Initialize();
		inventory.addItemToInventory(1);
		try {
			assertTrue(inventory.getScaling() == 1.00);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]){
		InventoryTest inventoryTest = new InventoryTest();
	}
}
