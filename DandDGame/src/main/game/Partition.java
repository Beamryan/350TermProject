package main.game;
import java.io.*;
import java.util.Scanner;

/**
 * Class to hold each map partition section.
 */
public class Partition {
	/** partition height. */
	int height = 50;
	
	/** partition width.  */
	int width = 50;
	
	/** partition tiles. */
	Cell[][] tiles = new Cell[height][width];
	
	/** warrior character. */
	Warrior player = new Warrior();

	/**
	 * @param height
	 * @param width
	 * @param partitionNum
	 * @param xDim
	 * @param yDim
	 * @param player
	 * @throws IOException
	 */
	public Partition(final int height, final int width, final int partitionNum, final int xDim, final int yDim, final Warrior player) throws IOException {
		this.height = height;
		this.width = width;
		this.player = player;
		tiles = new Cell[height][width];
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
			int xStart = width * (partitionNum % xDim);	// starting positions for valid x values
			int yStart = height * (partitionNum / yDim); // starting positions for valid y values

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

				//        the valid ranges on the map for a tile is the start,
				//        and the start + the dimension of the partition
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
				//          if a row of the map has been read, restart x and increment 
				x++;
				if (x == xDim * width) {
					y++;
					x = 0;
				}
			}
		} finally {
			in.close();
		} 
	}

	/**
	 * @param playerX
	 * @param playerY
	 */
	public void printPartition(final int playerX, final int playerY) {
		int i, j;
		for (i = 0; i < height; i++) {
			for (j = 0; j < width; j++) {
				if (i == playerY && j == playerX) {
					System.out.print("P");
				} else {
					if (tiles[i][j].type == SpaceType.Cleared) {
						System.out.print("X");
					} else if (tiles[i][j].type == SpaceType.EmptySpace) {
						System.out.print("O");
					} else if (tiles[i][j].type == SpaceType.Tree) {
						System.out.print("T");
					} else if (tiles[i][j].type == SpaceType.Rock) {
						System.out.print("R");
					} else if (tiles[i][j].type == SpaceType.Water) {
						System.out.print("W");
					} else {
						System.out.print(tiles[i][j].type);
					}

				}
			}
			System.out.println();
		}
	}

	/**
	 * Checks if the SpaceType is a valid to be moved onto.
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
	 * @param posY Current player location in the Y direction
	 * @param posX Current player location in the X direction
	 * @return Returns true if the cells itemID is nonzero, false if the itemID is zero. 
	 */
	public boolean tileHasItem(final int posY, final int posX) {	  
		if (tiles[posY][posX].itemID != 0) { 
			return true;
		}
		return false;
	}

	/**
	 * Check if the monsterID of the current cell has a value. 
	 * @param posY Current player location in the Y direction
	 * @param posX Current player location in the X direction
	 * @return Returns true if the cells monsterID is nonzero, false if the monsterID is zero. 
	 */
	public boolean tileHasMonster(final int posY, final int posX) {	  
		if (tiles[posY][posX].monsterID != 0) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the itemID of the current cell.
	 * @param posY Current player location in the Y direction
	 * @param posX Current player location in the X direction
	 * @return Returns the itemID of the current cell.
	 */
	public int getItemID(final int posY, final int posX) {
		return tiles[posY][posX].itemID;
	}

	/**
	 * Gets the monsterID of the current cell.
	 * @param posY Current player location in the Y direction
	 * @param posX Current player location in the X direction
	 * @return Returns the monsterID of the current cell.
	 */
	public int getMonsterID(final int posY, final int posX) {
		return tiles[posY][posX].monsterID;
	}

	/**
	 * Method to move the player north on the map.
	 * @param posY Current location Y
	 * @param posX Current location X
	 * @return new playerLocationY if valid, old playerLocationY if not valid.
	 */
	public int moveNorth(final int posY, final int posX) { 
		if (isValidTile(tiles[posY - 1][posX])) { 
			tiles[posY - 1][posX].type = SpaceType.Cleared;
			return posY - 1;
		}
		return posY;
	}

	/**
	 * Method to move the player east on the map.
	 *
	 * @param posY Current location Y
	 * @param posX Current location X
	 * @return new playerLocationX if valid, old playerLocationX if not valid.
	 */
	public int moveEast(final int posY, final int posX) { 
		if (isValidTile(tiles[posY][posX + 1])) { 
			tiles[posY][posX + 1].type = SpaceType.Cleared;
			return posX + 1;
		}
		return posX;
	}

	/**
	 * Method to move the player south on the map.
	 * @param posY Current location Y
	 * @param posX Current location X
	 * @return new playerLocationY if valid, old playerLocationY if not valid.
	 */
	public int moveSouth(final int posY, final int posX) { 
		if (isValidTile(tiles[posY + 1][posX])) { 
			tiles[posY + 1][posX].type = SpaceType.Cleared;
			return posY + 1;
		}
		return posY;
	}

	/**
	 * Method to move the player west on the map.
	 * @param posY Current location Y
	 * @param posX Current location X
	 * @return new playerLocationX if valid, old playerLocationX if not valid.
	 */
	public int moveWest(final int posY, final int posX) { 
		if (isValidTile(tiles[posY][posX - 1])) { 
			tiles[posY][posX - 1].type = SpaceType.Cleared;
			return posX - 1;
		}
		return posX;
	}

	/**
	 * Welcome to the game message.
	 */
	public void welcomeMessage() {
		System.out.println("Welcome to the strange world of being an engineering student!");
		System.out.println("You will struggle to find the proper tools to defend yourself from the ");
		System.out.println("monster hiding, waiting for you to fall into their traps! Collect hidden items by ");
		System.out.println("exploring the building. Items will help you in your quest to defet the monsters.");
		System.out.println("Press e for item management, and r for battle rules");
		System.out.println("Start exploring!\n\n");
	}

	/**
	 * @param args
	 * @throws IOException
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

		Partition foo = new Partition(width, length, currentPartition, xDim, yDim, player);


		// test basic movement in ascii
		Scanner dir = new Scanner(System.in, "utf-8");
		char choice = 0;
		foo.welcomeMessage();
		foo.printPartition(playerX, playerY);
		while (choice != -1) {
			choice = dir.next().charAt(0);

			if (choice == 'w') {
				//Following block is for if player goes to a new partition
				if (playerY == 0) {
					currentPartition -= xDim;
					foo = new Partition(width, length, currentPartition, xDim, yDim, player);
					playerY = length - 1;
				}
				playerY = foo.moveNorth(playerY, playerX);	 
			}
			if (choice == 'a') {
				if (playerX == 0) {
					currentPartition--;
					foo = new Partition(width, length, currentPartition, xDim, yDim, player);
					playerX = width - 1;
				} else {
					playerX = foo.moveWest(playerY, playerX);	 
				}
			}
			if (choice == 's') {
				if (playerY == length - 1) {
					currentPartition += xDim;
					foo = new Partition(width, length, currentPartition, xDim, yDim, player);
					playerY = 0;
				} else {
					playerY = foo.moveSouth(playerY, playerX);	 
				}
			}
			if (choice == 'd') {
				if (playerX == width - 1) {
					currentPartition++;
					foo = new Partition(width, length, currentPartition, xDim, yDim, player);
					playerX = 0;
				} else {
					playerX = foo.moveEast(playerY, playerX);
				}
			}

			// item directions and management
			if (choice == 'e') {
				foo.player.inventory.showInventory();
				System.out.println("If you would like to select an item to hold, enter the slot number \nor else hit any button again to close inventory.");
				choice = dir.next().charAt(0);
				if (choice != 'e') {
					choice -= 48; // correct slot number for ascii values
					if(choice >= 0 && choice <=foo.player.inventory.getInventorySize())
					{
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
			if (foo.tileHasItem(playerY, playerX)) {
				itemID = foo.getItemID(playerY, playerX);
				foo.player.inventory.addItemToInventory(itemID);
				System.out.println("Got item: " + foo.player.inventory.getItemName(itemID));
				System.out.println("Press e to equip");

				// remove item from tile
				foo.tiles[playerY][playerX].itemID = 0;
			}

			if (foo.tileHasMonster(playerY, playerX)) {	
				monsterID = foo.getMonsterID(playerY, playerX);
				Battle battle = new Battle(foo.player, monsterID);
				int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee

				// win
				if (xpGain > 0) {
					System.out.println("Player wins! Gain " + xpGain + " xp\n\n");
					foo.player.xp += xpGain; // award xp

					if (foo.player.xp >= foo.player.xpToNextLevel) {
						foo.player.levelUp();	
					}
					foo.tiles[playerY][playerX].monsterID = 0;

				} else if (xpGain == -1) { // ran
					System.out.println("Player ran");
					foo.tiles[playerY][playerX].monsterID = 0;
				} else { // loss
					System.out.println("Player loses! Back to start\n\n");
					currentPartition = 0;
					playerX = 2;
					playerY = 1;				 
				}
			}

			foo.printPartition(playerX, playerY);
			System.out.println("Level " + foo.player.level);
			System.out.println("Xp " + foo.player.xp);
		}
		dir.close();
	}
}
