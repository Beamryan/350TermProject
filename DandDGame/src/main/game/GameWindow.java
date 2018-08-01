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
	
	public static String SendWelcomeMessage() {

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
		mainFrame.setPreferredSize(new Dimension(1000, 750));

		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(750, 500));

		JPanel inventoryButtonPanel = new JPanel();
		inventoryButtonPanel.setPreferredSize(new Dimension(100, 500));
		
		JPanel textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(1000, 250));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(150, 500));

		JTextArea textArea = new JTextArea();
		textArea.setSize(new Dimension(1000, 250));
		textArea.setFont(textArea.getFont().deriveFont(24f));
		textArea.setAlignmentY(150);
		textArea.setAlignmentX(750);
		textArea.setEditable(false);
		textPanel.add(textArea);

		JButton inventoryButton1 = new JButton("1");
		JButton inventoryButton2 = new JButton("2");
		JButton inventoryButton3 = new JButton("3");
		JButton inventoryButton4 = new JButton("4");
		JButton inventoryButton5 = new JButton("5");
		JButton inventoryButton6 = new JButton("6");
		JButton inventoryButton7 = new JButton("7");
		JButton inventoryButton8 = new JButton("8");
		JButton inventoryButton9 = new JButton("9");
		
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
						textArea.setText(null);
						textArea.insert(panelPrompt,0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						 // remove item from tile
						foo.tiles[playerY][playerX].itemID = 0; 
					}
					else if(foo.doesTileHaveMonster()){
						textArea.setText(null);
						textArea.insert("Monster appeared! Battle it int th console!", 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						int monsterID = foo.getMonsterID();
						Battle battle = new Battle(foo.player, monsterID);
						int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
						foo.endBattle(xpGain);
					}
					else{
						textArea.setText(null);
						textArea.setText("Level: "+ foo.player.level + "\nXp: " + foo.player.xp);
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
						textArea.setText(null);
						textArea.insert(panelPrompt,0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						 // remove item from tile
						foo.tiles[playerY][playerX].itemID = 0; 
					}
					else if(foo.doesTileHaveMonster()){
						textArea.setText(null);
						textArea.insert("Monster appeared! Battle it in the console!", 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						int monsterID = foo.getMonsterID();
						Battle battle = new Battle(foo.player, monsterID);
						int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
						foo.endBattle(xpGain);
					}
					else{
						textArea.setText(null);
						textArea.insert("Level: "+ foo.player.level + "\nXp: " + foo.player.xp, 0);					
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
						textArea.setText(null);
						textArea.insert(panelPrompt,0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						 // remove item from tile
						foo.tiles[playerY][playerX].itemID = 0; 
					}
					else if(foo.doesTileHaveMonster()){
						textArea.setText(null);
						textArea.insert("Level: "+ foo.player.level + "\nXp: " + foo.player.xp, 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						int monsterID = foo.getMonsterID();
						Battle battle = new Battle(foo.player, monsterID);
						int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
						foo.endBattle(xpGain);
					}
					else{
						textArea.setText(null);
						textArea.insert("Level: "+ foo.player.level + "\nXp: " + foo.player.xp, 0);
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
						textArea.setText(null);
						textArea.insert(panelPrompt,0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						 // remove item from tile
						foo.tiles[playerY][playerX].itemID = 0; 
					}
					else if(foo.doesTileHaveMonster()){
						textArea.setText(null);
						textArea.insert("Level: "+ foo.player.level + "\nXp: " + foo.player.xp, 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						int monsterID = foo.getMonsterID();
						Battle battle = new Battle(foo.player, monsterID);
						int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
						foo.endBattle(xpGain);
					}
					else{
						textArea.setText(null);
						textArea.insert("Level: "+ foo.player.level + "\nXp: " + foo.player.xp, 0);
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
				foo.ManageInventory(textArea, foo.player);
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


		inventoryButton1.setPreferredSize(new Dimension(80, 45));
		inventoryButton1.setFont(new Font("Arial", Font.PLAIN, 12));
		inventoryButton1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				foo.SwapHoldingItem(1);
			}
		});
		inventoryButton2.setPreferredSize(new Dimension(80, 45));
		inventoryButton2.setFont(new Font("Arial", Font.PLAIN, 12));
		inventoryButton2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				foo.SwapHoldingItem(2);
			}
		});
		inventoryButton3.setPreferredSize(new Dimension(80, 45));
		inventoryButton3.setFont(new Font("Arial", Font.PLAIN, 12));
		inventoryButton3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				foo.SwapHoldingItem(3);
			}
		});
		inventoryButton4.setPreferredSize(new Dimension(80, 45));
		inventoryButton4.setFont(new Font("Arial", Font.PLAIN, 12));
		inventoryButton4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				foo.SwapHoldingItem(4);
			}
		});
		inventoryButton5.setPreferredSize(new Dimension(80, 45));
		inventoryButton5.setFont(new Font("Arial", Font.PLAIN, 12));
		inventoryButton5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				foo.SwapHoldingItem(5);
			}
		});
		inventoryButton6.setPreferredSize(new Dimension(80, 45));
		inventoryButton6.setFont(new Font("Arial", Font.PLAIN, 12));
		inventoryButton6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				foo.SwapHoldingItem(6);
			}
		});
		inventoryButton7.setPreferredSize(new Dimension(80, 45));
		inventoryButton7.setFont(new Font("Arial", Font.PLAIN, 12));
		inventoryButton7.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				foo.SwapHoldingItem(7);
			}
		});
		inventoryButton8.setPreferredSize(new Dimension(80, 45));
		inventoryButton8.setFont(new Font("Arial", Font.PLAIN, 12));
		inventoryButton8.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				foo.SwapHoldingItem(8);
			}
		});
		inventoryButton9.setPreferredSize(new Dimension(80, 45));
		inventoryButton9.setFont(new Font("Arial", Font.PLAIN, 12));
		inventoryButton9.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				foo.SwapHoldingItem(9);
			}
		});
		
		
		buttonPanel.add(upButton);
		buttonPanel.add(downButton);
		buttonPanel.add(leftButton);
		buttonPanel.add(rightButton);
		buttonPanel.add(inventoryButton);
//		buttonPanel.add(attackButton);
//		buttonPanel.add(shieldButton);
//		buttonPanel.add(restButton);
//		buttonPanel.add(fleeButton);
		
		inventoryButtonPanel.add(inventoryButton1);
		inventoryButtonPanel.add(inventoryButton2);
		inventoryButtonPanel.add(inventoryButton3);
		inventoryButtonPanel.add(inventoryButton4);
		inventoryButtonPanel.add(inventoryButton5);
		inventoryButtonPanel.add(inventoryButton6);
		inventoryButtonPanel.add(inventoryButton7);
		inventoryButtonPanel.add(inventoryButton8);
		inventoryButtonPanel.add(inventoryButton9);
		
		buttonPanel.setBackground(Color.WHITE);
		inventoryButtonPanel.setBackground(Color.WHITE);
		textPanel.setBackground(Color.WHITE);
		gamePanel.setBackground(Color.BLACK);

		mainFrame.add(gamePanel, BorderLayout.CENTER);
		mainFrame.add(textPanel, BorderLayout.PAGE_END);
		mainFrame.add(buttonPanel, BorderLayout.LINE_END);	
		mainFrame.add(inventoryButtonPanel, BorderLayout.LINE_START);

		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setVisible(true);	  
		PrintMap(gamePanel, foo);
		textArea.setText(SendWelcomeMessage());
		SwingUtilities.updateComponentTreeUI(mainFrame);
	}

}
