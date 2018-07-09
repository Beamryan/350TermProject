package main.game;

public class Cell {
	SpaceType type;
//	Direction[] availableDirections; 
	//int type;
	int itemID;
	int monsterID;
	
//	Cell(int type, int itemID, int monsterID){
//		this.type = type;
//		this.itemID = itemID;
//		this.monsterID = monsterID;
//	}
	Cell(SpaceType type, int itemID, int monsterID){
		this.type = type;
		this.itemID = itemID;
		this.monsterID = monsterID;
	}
	
}
