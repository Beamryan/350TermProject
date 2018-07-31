package main.game;
import java.io.*;
import java.util.Scanner;

import javax.swing.JTextArea;

/**
 * Class used to hold the portion of the map visible to the player.
 */
public class Partition {
	
	/** Player pos x. */
	int playerX = 5;
	
	/** Player pos y. */
	int playerY = 1;
	
	/** Starting partition. */
	int currentPartition = 0;
	
	/** The height of the partition. */
	int height;
	
	/** The width of the partition. */
	int width;
	
	/** The width of the map in partition. */
	int xDim;
	
	/** The height of the map in partition. */
	int yDim;
	
	/** The cells holding map tiles. */
	Cell[][] tiles = new Cell[height][width];
	
	/** The players character. */
	Warrior player = new Warrior();
	
	BattleMoveSelect battleMove = null;
  

  /**
 * @param height the height of the partition
 * @param width the width of the partition
 * @param partitionNum the partition sector
 * @param xDim scale of partition to full map in x
 * @param yDim scale of partition to full map in y
 * @param player the players character
 * @throws IOException exception caught for reading the output file
 */
public Partition(final int height, final int width, final int currentPartition, final int xDim, final int yDim, final Warrior player, int playerX, int playerY) throws IOException {
    this.height = height;
    this.width = width;
    this.player = player;
    this.playerX = playerX;
    this.playerY = playerY;
    this.xDim = xDim;
    this.yDim = yDim;
    this.currentPartition = currentPartition;
    tiles = new Cell[height][width];
    
}    
    

void updatePartition() throws IOException{
	
	Scanner in = new Scanner(new File("output.txt"));
	try {
	      int tileCount = 0; // how many tiles have been read into partition
	      int x = 0;	//x coordinate in entire map
	      int y = 0;	//y coordinate in entire map
	      int type;
	      int itemID;
	      int monsterID;
	      int xTile = 0;	// x coordinate in current partition
	      int yTile = 0;	// y coordinate in current partition
	      int xStart = width * (currentPartition % xDim);	// starting positions for valid x values
	      int yStart = height * (currentPartition / yDim); // starting positions for valid y values

	      while (tileCount < (width * height)) {
	        type = in.nextInt();
	        SpaceType spaceType = SpaceType.EmptySpace;
	        switch (type) {
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
	        
//	        the valid ranges on the map for a tile is the start,
//	        and the start + the dimension of the partition
	        if (x >= xStart && x < xStart + width) {
	            if (y >= yStart && y < yStart + height) {
	              tiles[yTile][xTile] = tile;
	              tileCount++;
	              xTile++;
	              // if a full row has been placed, increment row and restart x pos
	              if (xTile == width) {
	              	xTile = 0;
	              	yTile++;
	              }
	            }  		  
	          }
//	          if a row of the map has been read, restart x and increment 
	          x++;
	          if (x == xDim * width) {
	            y++;
	            x = 0;
	          }
	        }
	  } 
	  finally {
	    	in.close();
	  } 
}

  
  /**
 * @param playerX the current position x of player
 * @param playerY the current position y of player
 */
public void printPartition() {
    int i, j;
    for (i = 0; i < height; i++) {
      for (j = 0; j < width; j++) {
    	if (i == playerY && j == playerX) {
    		System.out.print("👶🏼");
    	} else {
    		if (tiles[i][j].type == SpaceType.Cleared) {
    			System.out.print("🏻");
    		} else if (tiles[i][j].type == SpaceType.EmptySpace) {
    			System.out.print("🏻‍");
    		} else if (tiles[i][j].type == SpaceType.Tree) {
    			System.out.print("🌲");
    		} else if (tiles[i][j].type == SpaceType.Rock) {
    			System.out.print("⛰️");
    		} else if (tiles[i][j].type == SpaceType.Water) {
    			System.out.print("🌊");
    		} else {
    			System.out.print(tiles[i][j].type);
    		}
    		
    	}
      }
      System.out.println();
    }
  }

public String returnPrintPartition() {
	String arr = "";
    int i, j;
    for (i = 0; i < height; i++) {
      for (j = 0; j < width; j++) {   	
    	if (i == playerY && j == playerX) {
    		arr += ("👶🏼");
    	} else {
    		if (tiles[i][j].type == SpaceType.Cleared) {
    			arr += ("🏻");
    		} else if (tiles[i][j].type == SpaceType.EmptySpace) {
    			arr += ("🏻‍");
    		} else if (tiles[i][j].type == SpaceType.Tree) {
    			arr += ("🌲");
    		} else if (tiles[i][j].type == SpaceType.Rock) {
    			arr += ("⛰️");
    		} else if (tiles[i][j].type == SpaceType.Water) {
    			arr += ("🌊");
    		} else {
    			System.out.print(tiles[i][j].type);
    		}
    	
    	}
      }
      arr += "\n";
      System.out.println();
    }
    return arr;
  }
  
  /**
   * Checks if the SpaceType is a moveable type.
   * @param tile current tile.
   * @return true if the space is able to be traveled to, false if the SpaceType is Tree, Rock, or Water
   */
  public boolean isValidTile(final Cell tile) {
	 
	  boolean isValid = true;
	  
	  if (tile.type == SpaceType.Tree) {
		  isValid = false;
	  }
	  if (tile.type == SpaceType.Rock) {
		  isValid = false;
	  }
	  if (tile.type == SpaceType.Water) {
		  isValid = false; 
	  }

	  if (player.inventory.inventory[19] == 1) {
		  if (tile.type == SpaceType.Water) {
			  isValid = true; 
		  }
	  }
	  
	  return isValid;	  
  }
  
  /**
   * Check if the itemID of the current cell has a value. 
   * @param playerLocationY Current player location in the Y direction
   * @param playerLocationX Current player location in the X direction
   * @return Returns true if the cells itemID is nonzero, false if the itemID is zero. 
   */
  public void doesTileHaveItem() {	  
	  if (tiles[playerY][playerX].itemID != 0) {
			 int itemID = getItemID(playerY, playerX);
			 player.inventory.addItemToInventory(itemID);
			 System.out.println("Got item: " + player.inventory.getItemName(itemID));
			 System.out.println("Press e to equip");
			 // remove item from tile
			 tiles[playerY][playerX].itemID = 0; 
	  }
  }
  
  /**
   * Check if the monsterID of the current cell has a value. 
   * @param playerLocationY Current player location in the Y direction
   * @param playerLocationX Current player location in the X direction
   * @return Returns true if the cells monsterID is nonzero, false if the monsterID is zero. 
   */
  public void doesTileHaveMonster() throws IOException{	  
	  if (tiles[playerY][playerX].monsterID != 0) {
		  battleMove = new BattleMoveSelect();
		  int monsterID = getMonsterID();
		  Battle battle = new Battle(player, monsterID);
		  int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
		  endBattle(xpGain);
	  }
  }
  
  /**
   * Gets the itemID of the current cell.
   * @param playerLocationY Current player location in the Y direction
   * @param playerLocationX Current player location in the X direction
   * @return Returns the itemID of the current cell.
   */
  public int getItemID(final int playerLocationY, final int playerLocationX) {
	  return tiles[playerLocationY][playerLocationX].itemID;
  }
  
  /**
   * Gets the monsterID of the current cell.
   * @param playerLocationY Current player location in the Y direction
   * @param playerLocationX Current player location in the X direction
   * @return Returns the monsterID of the current cell.
   */
  public int getMonsterID() {
	  return tiles[playerY][playerX].monsterID;
  }
  
  /**
   * Method to move the player north on the map.
   * @param playerLocationY Current location Y
   * @param playerLocationX Current location X
   * @return new playerLocationY if valid, old playerLocationY if not valid.
   */
  public void moveNorth() throws IOException{ 
	  if (playerY == 0) {
		  currentPartition -= xDim;
		  playerY = height - 1;
		  updatePartition();
		 }
	  else if (isValidTile(tiles[playerY - 1][playerX])) { 
		  tiles[playerY - 1][playerX].type = SpaceType.Cleared;
		  playerY -= 1;
	  }
  }
  
  /**
   * Method to move the player east on the map.
   *
   * @param playerLocationY Current location Y
   * @param playerLocationX Current location X
   * @return new playerLocationX if valid, old playerLocationX if not valid.
   */
  public void moveEast() throws IOException{ 
	  if (playerX == width - 1) {
		  currentPartition++;
		  playerX = 0;
		  updatePartition();
	  }
	  else if (isValidTile(tiles[playerY][playerX + 1])) { 
		  tiles[playerY][playerX + 1].type = SpaceType.Cleared;
		  playerX += 1;
	  }
  }
  
  /**
   * Method to move the player south on the map.
   * @param playerLocationY Current location Y
   * @param playerLocationX Current location X
   * @return new playerLocationY if valid, old playerLocationY if not valid.
   */
  public void moveSouth() throws IOException{ 
	if (playerY == height - 1) {
		currentPartition += xDim;
		playerY = 0;
		updatePartition();
	}
	else if (isValidTile(tiles[playerY + 1][playerX])) { 
		tiles[playerY + 1][playerX].type = SpaceType.Cleared;
		playerY += 1;
	}
  }
  
  /**
   * Method to move the player west on the map.
   * @param playerLocationY Current location Y
   * @param playerLocationX Current location X
   * @return new playerLocationX if valid, old playerLocationX if not valid.
   */
  public void moveWest() throws IOException{ 
	  if (playerX == 0) {
			 currentPartition--;
			 playerX = width - 1;
			 updatePartition();
	  }
	  else if (isValidTile(tiles[playerY][playerX - 1])) { 		  	 
			 tiles[playerY][playerX - 1].type = SpaceType.Cleared;	 	
			 playerX-=1;
	  }
  }
  
  public void endBattle(int xpGain){
		 if (xpGain > 0) {
			 System.out.println("Player wins! Gain " + xpGain + " xp\n\n");
			 player.xp += xpGain; // award xp
			 
			 if (player.xp >= player.xpToNextLevel) {
				 player.levelUp();
			 }			 
			 // Clear monster tile in partition
			 tiles[playerY][playerX].monsterID = 0;
		 } else if (xpGain == -1) {
			 System.out.println("Player ran");
			 tiles[playerY][playerX].monsterID = 0;
		 } else {
			 System.out.println("Player loses! Back to start\n\n");
			 currentPartition = 0;
			 playerX = 5;
			 playerY = 1;	
		 }
  }
  
  /**
 * The welcome message for starting the game.
 */
public void welcomeMessage() {
	  System.out.println("Welcome to the strange world of being an engineering student!");
	  System.out.println("You will struggle to find the proper tools to defend yourself from the ");
	  System.out.println("monster hiding, waiting for you to fall into their traps! Collect hidden items by ");
	  System.out.println("exploring the building. Items will help you in your quest to defet the monsters.");
	  System.out.println("Press e for item management, and r for battle rules");
	  System.out.println("Start exploring!\n\n");
  }  

public void ManageInventory(JTextArea textArea, Warrior player) {
	player.inventory.showInventory(textArea);
	textArea.append("If you would like to select an item to hold, enter the slot number \nor else hit any button to close inventory.");
//	textArea.setText();
}
public void SwapHoldingItem(int itemPosition) {
	if (itemPosition >= 0 && itemPosition <= player.inventory.getInventorySize()) {
		player.inventory.setItemToCurrent(itemPosition);
		try {
			player.scaling = player.inventory.getScaling();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
  
  /**
 * @param args none
 * @throws IOException throws exception for running partition files
 */
public static void main(final String[] args) throws IOException {
	 int currentPartition = 0;
	 int playerX = 5;
	 int playerY = 1;
	 int xDim = 5;
	 int yDim = 5;
	 int width = 10;
	 int length = 10;
	 Warrior player = new Warrior();
	 
	 Partition foo = new Partition(width, length, currentPartition, xDim, yDim, player, playerX, playerY);
	 foo.updatePartition();
	 
	 
	 // test basic movement in ascii
	 Scanner dir = new Scanner(System.in);
	 char choice = 0;
	 foo.welcomeMessage();
	 foo.printPartition();
	 while (choice != -1) {
		 dir = new Scanner(System.in);
		 choice = dir.next().charAt(0);
		 
		 if (choice == 'w') {
			 foo.moveNorth();	 
		 }
		 if (choice == 'a') {
			 foo.moveWest();	 		 
		 }
		 if (choice == 's') {
			 foo.moveSouth();	 
		 }
		 if (choice == 'd') {
			 foo.moveEast();	 
		 }
		 
		 // item directions and management
		 if (choice == 'e') {
			 foo.player.inventory.showInventory();
			 System.out.println("If you would like to select an item to hold, enter the slot number \nor else hit any button to close inventory.");
			 choice = dir.next().charAt(0);
			 if (choice != 'e') {
				 choice -= 48; // correct slot number for ascii values
				 if (choice >= 0 && choice <= foo.player.inventory.getInventorySize()) {
					 foo.player.inventory.setItemToCurrent(choice);
					 foo.player.scaling = foo.player.inventory.getScaling();
					 }
			 }
		 }
		 
		 // fighting directions
		 if (choice == 'r') {
			 System.out.println("There are four choices in combat: attack, shield, rest and flee.");
			 System.out.println("Attack will scale with your strength stat and your weapon. Every attack ");
			 System.out.println("will degrade your strength for the battle by a small amount - but watch ");
			 System.out.println("out - it adds up quickly!\n");
			 System.out.println("Shield will be used to regain/create speed. Speed is used to determine ");
			 System.out.println("evasiveness and flee capability. Don't overlook this stat - the ability to");
			 System.out.println("flee provides much needed relief.");
			 System.out.println("Rest will regain strength and health slightly, and will also reduce ");
			 System.out.println("incoming damage.");
		 }
		 	 
		 int itemID, monsterID;
		 
		 //If tile has item...
		 foo.doesTileHaveItem();
		 
		 foo.doesTileHaveMonster();
		 
		 foo.printPartition();
		 System.out.println("Level " + foo.player.level);
		 System.out.println("Xp " + foo.player.xp);
	 }
	 dir.close();
  }
}
