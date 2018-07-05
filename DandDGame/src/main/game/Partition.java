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
        itemID = in.nextInt();
        monsterID = in.nextInt();
        
        Cell tile = new Cell(type, itemID, monsterID);
        
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
  
  public void printPartition(){
    int i,j;
    for(i=0 ; i<25 ; i++){
      for(j=0 ; j<25 ; j++){
    	if(tiles[i][j].type == 1) System.out.print(" ");
    	else if(tiles[i][j].type == 2) System.out.print("T");
    	else if(tiles[i][j].type == 4) System.out.print("r");
    	else if(tiles[i][j].type == 16) System.out.print("~");
    	else System.out.print(tiles[i][j].type);
      }
      System.out.println();
    }
  }
  
  public static void main(String args[]) throws IOException {
	 Partition foo = new Partition(3,25,25,2,2);
	 foo.printPartition();
  }
}
