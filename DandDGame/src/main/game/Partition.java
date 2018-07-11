package main.game;
import java.io.*;
import java.util.Scanner;

public class Partition {
  int height;
  int width;
  Cell[][] tiles = new Cell[height][width];
  
  public Partition(int height, int width) throws IOException {
    this.height = height;
    this.width = width;
    tiles = new Cell[height][width];
    Scanner in = new Scanner(new File("output.txt"));
    
    try{
      int tileCount = 0; // how many tiles have been read into partition
      int x = 0;	//x coordinate in entire map
      int y = 0;	//y coordinate in entire map
      int type;
      int itemID;
      int monsterID;

      while(tileCount < (width*height)){
        type = in.nextInt();
        SpaceType spaceType = SpaceType.EmptySpace;
        switch(type) {
        case 1:
    		spaceType = SpaceType.Cleared;
    		break;
        case 2:
        	spaceType = SpaceType.EmptySpace;
        	break;
        case 4:
    		spaceType = SpaceType.Tree;
    		break;
    	case 8:
    		spaceType = SpaceType.Rock;
    		break;
    	case 16:
    		spaceType = SpaceType.Water;
    		break;
    	default:
    		break;
        }
        itemID = in.nextInt();
        monsterID = in.nextInt();
        in.nextLine();
        
        
        Cell tile = new Cell(spaceType, itemID, monsterID);
        
//        the valid ranges on the map for a tile is the start,
//        and the start + the dimension of the partition
        if(x >= 0 && x < width){
          if(y >= 0 && y < height){
            tiles[y][x] = tile;
            tileCount++;
            x++;
            // if a full row has been placed, increment row and restart x pos
            if(x == width){
            	x = 0;
            	y++;
            }
          }  		  
        }
      }
    }
    finally {
    	in.close();
    } 
  }
  
  public void printPartition(int playerX, int playerY){
    int i,j;
    for(i=0 ; i<height ; i++){
      for(j=0 ; j<width ; j++){
    	if(i == playerY && j == playerX) System.out.print("P");
    	else{
    		if(tiles[i][j].type == SpaceType.Cleared) System.out.print("X");
    		else if(tiles[i][j].type == SpaceType.EmptySpace) System.out.print("O");
	    	else if(tiles[i][j].type == SpaceType.Tree) System.out.print("T");
	    	else if(tiles[i][j].type == SpaceType.Rock) System.out.print("R");
	    	else if(tiles[i][j].type == SpaceType.Water) System.out.print("W");
	    	else System.out.print(tiles[i][j].type);
    	}
      }
      System.out.println();
    }
  }
  
  /**
   * Checks if the SpaceType is a traversable type
   * @param tile current tile.
   * @return true if the space is able to be traveled to, false if the SpaceType is Tree, Rock, or Water
   */
  public boolean isValidTile(Cell tile){
	  boolean isValid = true;
	  
	  if(tile.type == SpaceType.Tree) isValid = false;
	  if(tile.type == SpaceType.Rock) isValid = false;
	  if(tile.type == SpaceType.Water) isValid = false; 
	  //TODO item for water traversal
	  
	  return isValid;	  
  }
  
  /**
   * Check if the itemID of the current cell has a value. 
   * @param playerLocationY Current player location in the Y direction
   * @param playerLocationX Current player location in the X direction
   * @return Returns true if the cells itemID is nonzero, false if the itemID is zero. 
   */
  public boolean tileHasItem(int playerLocationY, int playerLocationX){
	  
	  if(tiles[playerLocationY][playerLocationX].itemID != 0) return true;	  
	  return false;
  }
  
  /**
   * Check if the monsterID of the current cell has a value. 
   * @param playerLocationY Current player location in the Y direction
   * @param playerLocationX Current player location in the X direction
   * @return Returns true if the cells monsterID is nonzero, false if the monsterID is zero. 
   */
  public boolean tileHasMonster(int playerLocationY, int playerLocationX){
	  
	  if(tiles[playerLocationY][playerLocationX].monsterID != 0) return true;	  
	  return false;
  }
  
  /**
   * Gets the itemID of the current cell.
   * @param playerLocationY Current player location in the Y direction
   * @param playerLocationX Current player location in the X direction
   * @return Returns the itemID of the current cell.
   */
  public int getItemID(int playerLocationY, int playerLocationX){
	  return tiles[playerLocationY][playerLocationX].itemID;
  }
  
  /**
   * Gets the monsterID of the current cell.
   * @param playerLocationY Current player location in the Y direction
   * @param playerLocationX Current player location in the X direction
   * @return Returns the monsterID of the current cell.
   */
  public int getMonsterID(int playerLocationY, int playerLocationX){
	  return tiles[playerLocationY][playerLocationX].monsterID;
  }
  
  /**
   * Method to move the player north on the map.
   * @param playerLocationY Current location Y
   * @param playerLocationX Current location X
   * @return new playerLocationY if valid, old playerLocationY if not valid.
   */
  public int moveNorth(int playerLocationY, int playerLocationX) { 
	  if(isValidTile(tiles[playerLocationY - 1][playerLocationX])) { 
		  tiles[playerLocationY - 1][playerLocationX].type = SpaceType.Cleared;
		  return playerLocationY - 1;
	  }
	  return playerLocationY;
  }
  
  /**
   * Method to move the player east on the map.
   * @param playerLocationY Current location Y
   * @param playerLocationX Current location X
   * @return new playerLocationX if valid, old playerLocationX if not valid.
   */
  public int moveEast(int playerLocationY, int playerLocationX) { 
	  if(isValidTile(tiles[playerLocationY][playerLocationX + 1])) { 
		  tiles[playerLocationY][playerLocationX + 1].type = SpaceType.Cleared;
		  return playerLocationX + 1;
	  }
	  return playerLocationX;
  }
  
  /**
   * Method to move the player south on the map.
   * @param playerLocationY Current location Y
   * @param playerLocationX Current location X
   * @return new playerLocationY if valid, old playerLocationY if not valid.
   */
  public int moveSouth(int playerLocationY, int playerLocationX) { 
	  if(isValidTile(tiles[playerLocationY + 1][playerLocationX])) { 
		  tiles[playerLocationY + 1][playerLocationX].type = SpaceType.Cleared;
		  return playerLocationY + 1;
	  }
	  return playerLocationY;
  }
  
  /**
   * Method to move the player west on the map.
   * @param playerLocationY Current location Y
   * @param playerLocationX Current location X
   * @return new playerLocationX if valid, old playerLocationX if not valid.
   */
  public int moveWest(int playerLocationY, int playerLocationX) { 
	  if(isValidTile(tiles[playerLocationY][playerLocationX - 1])) { 
		  tiles[playerLocationY][playerLocationX - 1].type = SpaceType.Cleared;
		  return playerLocationX - 1;
	  }
	  return playerLocationX;
  }
  
  public static void main(String args[]) throws IOException {
	 Partition foo = new Partition(50,50);
	 int playerX = 2;
	 int playerY = 1;
	 
	 // test basic movement in ascii
	 Scanner dir = new Scanner(System.in);
	 char choice = 0;
	 foo.printPartition(playerX, playerY);
	 while(choice != -1){
		 choice = dir.next().charAt(0);
		 
		 
		 if(choice == 'w'){
			 playerY = foo.moveNorth(playerY, playerX);	 
		 }
		 if(choice == 'a'){
			 playerX = foo.moveWest(playerY, playerX);	 
		 }
		 if(choice == 's'){
			 playerY = foo.moveSouth(playerY, playerX);	 
		 }
		 if(choice == 'd'){
			 playerX = foo.moveEast(playerY, playerX);	 
		 }
		 
		 int itemID, monsterID;
		 if(foo.tileHasItem(playerY, playerX))
		 {
			 itemID = foo.getItemID(playerY, playerX);
		 }
		 if(foo.tileHasMonster(playerY, playerX))
		 {
			 monsterID = foo.getItemID(playerY, playerX);
		 }
		 
		 Runtime.getRuntime().exec("clear");
		 foo.printPartition(playerX, playerY);
	 }
	 dir.close();
  }
}
