package tests;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import main.game.*;

public class PartitionTest {

	private Partition partition;
	int playerX;
	int playerY;
	
	private void Initialize() throws IOException
	{
		int currentPartition = 0;
		playerX = 6;
		playerY = 5;
		int xDim = 5;
		int yDim = 5;
		int width = 10;
		int length = 10;
		Warrior player = new Warrior();
		 
		partition = new Partition(width,length,currentPartition,xDim,yDim,player);
	}
	
	@Test
	public void TestMoveNorth(){
		try {
			Initialize();
			int initialPlayerY = playerY;
			playerY = partition.moveNorth(playerY, playerX);
			assertTrue(playerY + 1 == initialPlayerY);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestMoveEast(){
		try {
			Initialize();
			int initialPlayerX = playerX;
			playerX = partition.moveEast(playerY, playerX);
			assertTrue(playerX - 1 == initialPlayerX);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestMoveSouth(){
		try {
			Initialize();
			int initialPlayerY = playerY;
			playerY = partition.moveSouth(playerY, playerX);
			assertTrue(playerY - 1 == initialPlayerY);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestMoveWest(){
		try {
			Initialize();
			int initialPlayerX = playerX;
			playerX = partition.moveWest(playerY, playerX);
			assertTrue(playerX + 1 == initialPlayerX);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestGetItemID(){
		try {
			Initialize();
			int itemID = partition.getItemID(playerY, playerX);
			assertTrue(itemID == 0);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestGetMonsterID(){
		try {
			Initialize();
			int monsterID = partition.getItemID(playerY, playerX);
			assertTrue(monsterID == 0);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		PartitionTest test = new PartitionTest();
		
	}

}
