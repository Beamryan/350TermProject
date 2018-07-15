package main.game;
import java.io.*;
import java.util.Scanner;

public class Partition {
  int height;
  int width;
  Cell[][] tiles = new Cell[height][width];
  Warrior player = new Warrior();
  
  public Partition(int height, int width, int partitionNum, int xDim, int yDim) throws IOException {
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
      int xTile = 0;	// x coordinate in current partition
      int yTile = 0;	// y coordinate in current partition
      int xStart = width*(partitionNum%xDim);	// starting positions for valid x values
      int yStart = height*(partitionNum/yDim); // starting positions for valid y values

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
        if(x >= xStart && x < xStart + width){
            if(y >= yStart && y < yStart + height){
              tiles[yTile][xTile] = tile;
              tileCount++;
              xTile++;
              // if a full row has been placed, increment row and restart x pos
              if(xTile == width){
              	xTile = 0;
              	yTile++;
              }
            }  		  
          }
//          if a row of the map has been read, restart x and increment y
          x++;
          if(x == xDim*width){
            y++;
            x = 0;
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
    		if(tiles[i][j].type == SpaceType.Cleared) System.out.print(" ");
    		else if(tiles[i][j].type == SpaceType.EmptySpace) System.out.print(" ");
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
	  if(tiles[playerLocationY][playerLocationX].monsterID != 0){
		  return true;
	  }
	  else{
		  return false;
	  }
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
   * Method to move the player east on the map.s
   *
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
	 int currentPartition = 0;
	 int playerX = 2;
	 int playerY = 1;
	 int xDim = 5;
	 int yDim = 5;
	 int width = 10;
	 int length = 10;
	 
	 Partition foo = new Partition(width,length,currentPartition,xDim,yDim);
	 
	 
	 // test basic movement in ascii
	 Scanner dir = new Scanner(System.in);
	 char choice = 0;
	 foo.printPartition(playerX, playerY);
	 while(choice != -1){
		 choice = dir.next().charAt(0);
		 
		 
		 if(choice == 'w'){
			 if(playerY == 0){
				 currentPartition -= xDim;
				 foo = new Partition(width,length,currentPartition,xDim,yDim);
				 playerY = length -1;
			 }
			 playerY = foo.moveNorth(playerY, playerX);	 
		 }
		 if(choice == 'a'){
			 if(playerX == 0){
				 currentPartition--;
				 foo = new Partition(width,length,currentPartition,xDim,yDim);
				 playerX = width -1;
			 }
			 else playerX = foo.moveWest(playerY, playerX);	 
		 }
		 if(choice == 's'){
			 if(playerY == length-1){
				 currentPartition += xDim;
				 foo = new Partition(width,length,currentPartition,xDim,yDim);
				 playerY = 0;
			 }
			 else playerY = foo.moveSouth(playerY, playerX);	 
		 }
		 if(choice == 'd'){
			 if(playerX == width-1){
				 currentPartition++;
				 foo = new Partition(width,length,currentPartition,xDim,yDim);
				 playerX = 0;
			 }
			 else playerX = foo.moveEast(playerY, playerX);	 
		 }
		 
		 System.out.println("\n\n");
		 
		 int itemID, monsterID;
		 
		 if(foo.tileHasItem(playerY, playerX))
		 {
			 itemID = foo.getItemID(playerY, playerX);
			 foo.player.inventory[itemID] = 1;
			 System.out.println("Got item " + itemID);
		 }
		 if(foo.tileHasMonster(playerY, playerX))
		 {	
			 monsterID = foo.getMonsterID(playerY, playerX);
			 Battle battle = new Battle(foo.player,monsterID);
			 int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win
			 if(xpGain > 0){
				 System.out.println("Player wins! Gain " + xpGain + " xp\n\n");
				 foo.player.xp += xpGain;
				 
				 if(foo.player.xp >= foo.player.xpToNextLevel)
					 foo.player.levelUp();
				 
				 // Clear monster tile in partition
				 foo.tiles[playerY][playerX].monsterID = 0;
			 }
			 else if(xpGain == -1){
				 System.out.println("Player ran");
				 foo.tiles[playerY][playerX].monsterID = 0;
			 }
			 else{
				 System.out.println("Player loses! Back to start\n\n");
				 playerX = 2;
				 playerY = 1;				 
			 }
		 }
		 
		 Runtime.getRuntime().exec("clear");
		 foo.printPartition(playerX, playerY);
		 System.out.println("Level " + foo.player.level);
		 System.out.println("Xp " + foo.player.xp);
	 }
	 dir.close();
  }
}
