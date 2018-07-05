package main.game;
import java.io.*;
import java.util.Scanner;

public class Partition {
  int height;
  int width;
  Cell[][] tiles = new Cell[height][width];
  
  
  public Partition(int partitionNum, int height, int width) throws IOException {
    this.height = height;
    this.width = width;
    tiles = new Cell[height][width];
    Scanner in;
    
    try{
      in = new Scanner(new File("output.txt"));
      int tileCount = 0;
      int x = 0;
      int y = 0;
     // for a 2x2 map
      int xStart = width*(partitionNum%2);
      int yStart = height*(partitionNum/2);
      int type;
      int itemID;
      int monsterID;
      int xTile = 0;
      int yTile = 0;
      
      while(tileCount < width*(height)){
        type = in.nextInt();
        itemID = in.nextInt();
        monsterID = in.nextInt();
        
        Cell tile = new Cell(type, itemID, monsterID);
        
        if(x >= xStart && x < xStart + width){
          if(y >= yStart && y < yStart + height){
            tiles[yTile][xTile] = tile;
            tileCount++;
            xTile++;
            if(xTile == width){
            	xTile = 0;
            	yTile++;
            }
          }  		  
        }
        x++;
        if(x == 2*width){
          y++;
          x = 0;
        }
      }
    }
    finally {
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
	 Partition foo = new Partition(3,25,25);
	 foo.printPartition();
  }
}
