package main.game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;

public final class GameWindow {

	/**
	 * 
	 */
	private GameWindow() {

	}
	
	/**
	 * Prints the map to the GUI.
	 * @param gamePanel
	 * @param partition
	 */
	private static void PrintMap(JPanel gamePanel, Partition partition) {
		ClearMapPanel(gamePanel);
		GridLayout mapGrid = new GridLayout(10, 10);
		gamePanel.setLayout(mapGrid);
		for(int i = 0; i<10; i++) {
			for(int j = 0; j < 10; j++) {
				JLabel picture = null;
				BufferedImage image = null;
				if(partition.playerX == j && partition.playerY == i) {
					try {
						image = ImageIO.read(new File("reallyBadSprite.png")); //TODO: get warrior picture.
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {
					try {
						if(partition.tiles[i][j].type == SpaceType.Tree) {
							image = ImageIO.read(new File("Tree.png"));
						}
						else if(partition.tiles[i][j].type == SpaceType.EmptySpace) {
							image = ImageIO.read(new File("Road.png"));
						}
						else if(partition.tiles[i][j].type == SpaceType.Rock) {
							image = ImageIO.read(new File("Rock.png"));
						}
						else if(partition.tiles[i][j].type == SpaceType.Water) {
							image = ImageIO.read(new File("Water.png"));
						}
						else if(partition.tiles[i][j].type == SpaceType.Cleared) {
							image = ImageIO.read(new File("Road.png"));
						}
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}
				picture = new JLabel(new ImageIcon(image));
				picture.setSize(new Dimension(50, 50));
				gamePanel.add(picture);
			}
		}
	}
	
	private static void ClearMapPanel(JPanel gamePanel) {
		gamePanel.removeAll();
		gamePanel.revalidate();
		gamePanel.repaint();
	}
	
	private static String PrintPlayerStats(Partition foo) {
		String playerStats = "Level: " + foo.player.level + "     Experience: " + foo.player.xp + "     Exp To Next Lvl: " + foo.player.xpToNextLevel;
		playerStats += "     Health: " + foo.player.health + "     Strength: " + foo.player.strength + "     Speed: " + foo.player.speed;
		playerStats += "     Stamina: " + foo.player.stamina + "     Focus: " + foo.player.focus;
		
		return playerStats;
	}
	
	private static String SendWelcomeMessage() {

		String welcomeString = "";
		welcomeString += "Welcome to the strange world of being an engineering student!\n";
		welcomeString += "You will struggle to find the proper tools to defend yourself from the\n";
		welcomeString += "monster hiding, waiting for you to fall into their traps! Collect hidden items by\n";
		welcomeString += "exploring the building. Items will help you in your quest to defet the monsters.\n";
		welcomeString += "Press e for item management, and r for battle rules\n";
		welcomeString += "Start exploring!\n\n";

		return welcomeString;
	}  
	
//	private static void SelectCharacterPrompt(Character player) {
//		char choice;
//		do {
//		Scanner choiceSC = new Scanner(System.in);
//		System.out.println("Please select a character type:\n");
//		System.out.println("'W' = Warrior  'R' = Rogue  'M' = Mage \n");
//		choice = choiceSC.next().charAt(0);
//		}while((choice != 'W') || (choice != 'R') || (choice != 'M'));
//		
//		if(choice == 'W') {
//			System.out.println("The selected class was Warrior.\n" );
//			player = (Warrior) player;
//		} else if(choice == 'R') {
//			System.out.println("The selected class was Rogue.\n" );
//			player = (Rogue) player;
//		} else if(choice == 'M') {
//			System.out.println("The selected class was Mage.\n" );
//			player = (Mage) player;
//		}	
//	}


	/**
	 * 
	 * @param args arguments for main method.
	 * @throws IOException 
	 */
	public static void main (final String[] args) throws IOException {

		int currentPartition = 0;
		int playerX = 5;
		int playerY = 1;
		int xDim = 5;
		int yDim = 5;
		int width = 10;
		int length = 10;
		Warrior player = new Warrior();
		
		//player = SelectCharacterPrompt(player);

		Partition foo = new Partition(width, length, currentPartition, xDim, yDim, player, playerX, playerY);
		foo.updatePartition();

		JFrame mainFrame = new JFrame();
		mainFrame.setPreferredSize(new Dimension(1000, 800));

		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(750, 500));

		JPanel inventoryButtonPanel = new JPanel();
		inventoryButtonPanel.setPreferredSize(new Dimension(100, 500));
		
		JPanel textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(1000, 250));
		
		JPanel statPanel = new JPanel();
		statPanel.setPreferredSize(new Dimension(1000, 50));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(150, 500));
		
		JTextArea statTextArea = new JTextArea();
		statTextArea.setSize(new Dimension(1000, 50));
		statTextArea.setFont(statTextArea.getFont().deriveFont(16f));
		statTextArea.setEditable(false);
		statPanel.add(statTextArea);

		JTextArea storyTextArea = new JTextArea();
		storyTextArea.setSize(new Dimension(1000, 250));
		storyTextArea.setFont(storyTextArea.getFont().deriveFont(24f));
		storyTextArea.setEditable(false);
		textPanel.add(storyTextArea);
		
 		for(int i = 0; i < foo.player.inventory.inventorySize; i++) {
 			final int itemPosition = i;
 			JButton inventoryButton = new JButton("" + i);
 			inventoryButton.setPreferredSize(new Dimension(50, 50));
 			inventoryButton.setFont(new Font("Arial", Font.PLAIN, 12));
 			inventoryButton.addActionListener(new ActionListener()
 			{
 				public void actionPerformed(ActionEvent e)
 				{
 					foo.SwapHoldingItem(itemPosition);
 				}
 			});
 			inventoryButtonPanel.add(inventoryButton);
 		}
		
		JButton upButton = new JButton("Up");
		upButton.setPreferredSize(new Dimension(150, 85));
		upButton.setFont(new Font("Arial", Font.PLAIN, 24));
		upButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					foo.moveNorth(); 
					PrintMap(gamePanel, foo);
					if(foo.doesTileHaveItem()){
						int itemID = foo.getItemID(foo.playerY, foo.playerX);
						foo.player.inventory.addItemToInventory(itemID);
						String panelPrompt = "Got item: "+foo.player.inventory.getItemName(itemID);
						panelPrompt += "\nGo to inventory to equip";
						storyTextArea.setText(null);
						storyTextArea.insert(panelPrompt,0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						 // remove item from tile
						foo.tiles[playerY][playerX].itemID = 0; 
					}
					else if(foo.doesTileHaveMonster()){
						storyTextArea.setText(null);
						storyTextArea.insert("Monster appeared! Battle it using the console!", 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						int monsterID = foo.getMonsterID();
						Battle battle = new Battle(foo.player, monsterID);
						int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
						foo.endBattle(xpGain);
						PrintMap(gamePanel, foo);
					}
					else{
						statTextArea.setText(null);
						statTextArea.insert(PrintPlayerStats(foo), 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		JButton downButton = new JButton("Down");
		downButton.setPreferredSize(new Dimension(150, 85));
		downButton.setFont(new Font("Arial", Font.PLAIN, 24));
		downButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					foo.moveSouth(); 
					PrintMap(gamePanel, foo);
					if(foo.doesTileHaveItem()){
						int itemID = foo.getItemID(foo.playerY, foo.playerX);
						foo.player.inventory.addItemToInventory(itemID);
						String panelPrompt = "Got item: "+foo.player.inventory.getItemName(itemID);
						panelPrompt += "\nGo to inventory to equip";
						storyTextArea.setText(null);
						storyTextArea.insert(panelPrompt,0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						 // remove item from tile
						foo.tiles[playerY][playerX].itemID = 0; 
					}
					else if(foo.doesTileHaveMonster()){
						storyTextArea.setText(null);
						storyTextArea.insert("Monster appeared! Battle it using the console!", 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						int monsterID = foo.getMonsterID();
						Battle battle = new Battle(foo.player, monsterID);
						int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
						foo.endBattle(xpGain);
						PrintMap(gamePanel, foo);
					}
					else{
						statTextArea.setText(null);
						statTextArea.insert(PrintPlayerStats(foo), 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
					}	
				} catch(Exception ex) {
					ex.printStackTrace();
				}		 
			}
		});

		JButton leftButton = new JButton("Left");
		leftButton.setPreferredSize(new Dimension(150, 85));
		leftButton.setFont(new Font("Arial", Font.PLAIN, 24));
		leftButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					foo.moveWest(); 
					PrintMap(gamePanel, foo);
					if(foo.doesTileHaveItem()){
						int itemID = foo.getItemID(foo.playerY, foo.playerX);
						foo.player.inventory.addItemToInventory(itemID);
						String panelPrompt = "Got item: "+foo.player.inventory.getItemName(itemID);
						panelPrompt += "\nGo to inventory to equip";
						storyTextArea.setText(null);
						storyTextArea.insert(panelPrompt,0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						 // remove item from tile
						foo.tiles[playerY][playerX].itemID = 0; 
					}
					else if(foo.doesTileHaveMonster()){
						storyTextArea.setText(null);
						storyTextArea.insert("Monster appeared! Battle it using the console!", 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						int monsterID = foo.getMonsterID();
						Battle battle = new Battle(foo.player, monsterID);
						int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
						foo.endBattle(xpGain);
						PrintMap(gamePanel, foo);
					}
					else{
						statTextArea.setText(null);
						statTextArea.insert(PrintPlayerStats(foo), 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}		 
			}
		});

		JButton rightButton = new JButton("Right");
		rightButton.setPreferredSize(new Dimension(150, 85));
		rightButton.setFont(new Font("Arial", Font.PLAIN, 24));
		rightButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					foo.moveEast(); 
					PrintMap(gamePanel, foo);
					if(foo.doesTileHaveItem()){
						int itemID = foo.getItemID(foo.playerY, foo.playerX);
						foo.player.inventory.addItemToInventory(itemID);
						String panelPrompt = "Got item: "+foo.player.inventory.getItemName(itemID);
						panelPrompt += "\nGo to inventory to equip";
						storyTextArea.setText(null);
						storyTextArea.insert(panelPrompt,0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						 // remove item from tile
						foo.tiles[playerY][playerX].itemID = 0; 
					}
					else if(foo.doesTileHaveMonster()){
						storyTextArea.setText(null);
						storyTextArea.insert("Monster appeared! Battle it using the console!", 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						int monsterID = foo.getMonsterID();
						Battle battle = new Battle(foo.player, monsterID);
						int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
						foo.endBattle(xpGain);
						PrintMap(gamePanel, foo);
					}
					else{
						statTextArea.setText(null);
						statTextArea.insert(PrintPlayerStats(foo), 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.setPreferredSize(new Dimension(150, 85));
		inventoryButton.setFont(new Font("Arial", Font.PLAIN, 24));
		inventoryButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				foo.ManageInventory(storyTextArea, foo.player);
			}
		});

//		JButton attackButton = new JButton("Attack");
//		attackButton.setPreferredSize(new Dimension(250, 40));
//		attackButton.setFont(new Font("Arial", Font.PLAIN, 30));
//		attackButton.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				foo.battleMove.setChoice(BattleChoices.attack);
//			}
//		});
//
//		JButton shieldButton = new JButton("Shield");
//		shieldButton.setPreferredSize(new Dimension(250, 40));
//		shieldButton.setFont(new Font("Arial", Font.PLAIN, 30));
//		shieldButton.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				foo.battleMove.setChoice(BattleChoices.sheild);
//			}
//		});
//
//		JButton restButton = new JButton("Rest");
//		restButton.setPreferredSize(new Dimension(250, 40));
//		restButton.setFont(new Font("Arial", Font.PLAIN, 30));
//		restButton.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				foo.battleMove.setChoice(BattleChoices.rest);
//			}
//		});
//
//		JButton fleeButton = new JButton("Flee");
//		fleeButton.setPreferredSize(new Dimension(250, 40));
//		fleeButton.setFont(new Font("Arial", Font.PLAIN, 30));
//		fleeButton.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				foo.battleMove.setChoice(BattleChoices.flee);
//			}
//		});


	
		
		buttonPanel.add(upButton);
		buttonPanel.add(downButton);
		buttonPanel.add(leftButton);
		buttonPanel.add(rightButton);
		buttonPanel.add(inventoryButton);
//		buttonPanel.add(attackButton);
//		buttonPanel.add(shieldButton);
//		buttonPanel.add(restButton);
//		buttonPanel.add(fleeButton);
		
		buttonPanel.setBackground(Color.WHITE);
		inventoryButtonPanel.setBackground(Color.WHITE);
		textPanel.setBackground(Color.WHITE);
		statPanel.setBackground(Color.WHITE);
		gamePanel.setBackground(Color.BLACK);

		mainFrame.add(gamePanel, BorderLayout.CENTER);
		mainFrame.add(statPanel, BorderLayout.NORTH);
		mainFrame.add(textPanel, BorderLayout.PAGE_END);
		mainFrame.add(buttonPanel, BorderLayout.LINE_END);	
		mainFrame.add(inventoryButtonPanel, BorderLayout.LINE_START);

		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setVisible(true);	  
		PrintMap(gamePanel, foo);
		storyTextArea.setText(SendWelcomeMessage());
		statTextArea.insert(PrintPlayerStats(foo), 0);
		SwingUtilities.updateComponentTreeUI(mainFrame);
	}

}
