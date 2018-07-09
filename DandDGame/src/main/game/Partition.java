package main.game;
import java.io.*;
import java.util.Scanner;

public class Partition {
  int height;
  int width;
  Cell[][] tiles = new Cell[height][width];
  
  
//  partition num is left->right top->bottom. Starts at 0
//  height/width are dim of one partition
//  xDim/ydim is how many partitions long each dimension of the map is
  public Partition(int partitionNum, int height, int width, int xDim, int yDim) throws IOException {
    this.height = height;
    this.width = width;
    tiles = new Cell[height][width];
    Scanner in = new Scanner(new File("output.txt"));
    
    try{
      int tileCount = 0; // how many tiles have been read into partition
      int x = 0;	//x coordinate in entire map
      int y = 0;	//y coordinate in entire map
      int xTile = 0;	// x coordinate in current partition
      int yTile = 0;	// y coordinate in current partition
      int xStart = width*(partitionNum%xDim);	// starting positions for valid x values
      int yStart = height*(partitionNum/yDim); 	// starting positions for valid y values
      int type;
      int itemID;
      int monsterID;

      while(tileCount < width*(height)){
    	  // cell values from input file get read in
        type = in.nextInt();
        SpaceType spaceType = SpaceType.EmptySpace;
        switch(type)
        {
        case 1:
        	spaceType = SpaceType.EmptySpace;
        	break;
        case 2:
        	spaceType = SpaceType.Tree;
        	break;
        case 4:
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
//        if a row of the map has been read, restart x and increment y
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
    for(i=0 ; i<25 ; i++){
      for(j=0 ; j<25 ; j++){
    	if(i == playerY && j == playerX) System.out.print("P");
    	else{
	    	if(tiles[i][j].type == SpaceType.EmptySpace) System.out.print(" ");
	    	else if(tiles[i][j].type == SpaceType.Tree) System.out.print("T");
	    	else if(tiles[i][j].type == SpaceType.Rock) System.out.print("r");
	    	else if(tiles[i][j].type == SpaceType.Water) System.out.print("~");
	    	else System.out.print(tiles[i][j].type);
    	}
      }
      System.out.println();
    }
  }
  
  public boolean isValidTile(Cell tile){
	  boolean isValid = true;
	  
	  if(tile.type == SpaceType.Tree) isValid = false;
	  if(tile.type == SpaceType.Rock) isValid = false;
	  if(tile.type == SpaceType.Water) isValid = false; 
	  //TODO item for water traversal
	  
	  return isValid;	  
  }
  
  public static void main(String args[]) throws IOException {
	 Partition foo = new Partition(0,25,25,2,2);
	 int playerX = 5;
	 int playerY = 1;
	 
	 // test basic movement in ascii
	 Scanner dir = new Scanner(System.in);
	 char choice = 0;
	 foo.printPartition(playerX, playerY);
	 while(choice != -1){
		 choice = dir.next().charAt(0);
		 
		 
		 if(choice == 'w'){
			 if(foo.isValidTile(foo.tiles[playerY-1][playerX])){
				 playerY -= 1;
			 }		 
		 }
		 if(choice == 'a'){
			 if(foo.isValidTile(foo.tiles[playerY][playerX-1])){
				 playerX -= 1;
			 }		 
		 }
		 if(choice == 's'){
			 if(foo.isValidTile(foo.tiles[playerY+1][playerX])){
				 playerY += 1;
			 }		 
		 }
		 if(choice == 'd'){
			 if(foo.isValidTile(foo.tiles[playerY][playerX+1])){
				 playerX += 1;
			 }		 
		 }
		 
		 Runtime.getRuntime().exec("clear");
		 foo.printPartition(playerX, playerY);
	 }
	 dir.close();
  }
}
