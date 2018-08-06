package main.game;

import java.io.File;
import java.io.IOException;
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
import javax.swing.JOptionPane;

/**
 * Gui window created to run the game.
 */
public final class GameWindow {

	/**
	 * Game window constructor.
	 */
	private GameWindow() {

	}

	/**
	 * Prints the map to the GUI.
	 * @param gamePanel the panel
	 * @param partition game partition
	 */
	private static void printMap(final JPanel gamePanel, final Partition partition) {
		clearMapPanel(gamePanel);
		GridLayout mapGrid = new GridLayout(10, 10);
		gamePanel.setLayout(mapGrid);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				JLabel picture = null;
				BufferedImage image = null;
				if (partition.playerX == j && partition.playerY == i) {
					try {
						image = ImageIO.read(new File("reallyBadSprite.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						if (partition.tiles[i][j].type == SpaceType.Tree) {
							image = ImageIO.read(new File("Tree.png"));
						} else if (partition.tiles[i][j].type == SpaceType.EmptySpace) {
							image = ImageIO.read(new File("Road.png"));
						} else if (partition.tiles[i][j].type == SpaceType.Rock) {
							image = ImageIO.read(new File("Rock.png"));
						} else if (partition.tiles[i][j].type == SpaceType.Water) {
							image = ImageIO.read(new File("Water.png"));
						} else if (partition.tiles[i][j].type == SpaceType.Cleared) {
							image = ImageIO.read(new File("Road.png"));
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				try {
					if(image != null) {
						picture = new JLabel(new ImageIcon(image));
					} else {
						image = ImageIO.read(new File("Error.png"));
						picture = new JLabel(new ImageIcon(image));
					}
					picture.setSize(new Dimension(50, 50));
					gamePanel.add(picture);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Clears the games map panel.
	 * @param gamePanel the game panel
	 */
	private static void clearMapPanel(final JPanel gamePanel) {
		gamePanel.removeAll();
		gamePanel.revalidate();
		gamePanel.repaint();
	}

	/**
	 * Prints the player stats.
	 * @param foo the partition
	 * @return String the player stats
	 */
	private static String printPlayerStats(final Partition foo) {
		String playerStats = "Level: " + foo.player.level + "     Experience: " + foo.player.xp + "     Exp To Next Lvl: " + foo.player.xpToNextLevel;
		playerStats += "     Health: " + foo.player.health + "     Strength: " + foo.player.strength + "     Speed: " + foo.player.speed;
		playerStats += "     Stamina: " + foo.player.stamina + "     Focus: " + foo.player.focus;

		return playerStats;
	}

	/**
	 * Returns the welcome message.
	 * @return String the welcome message
	 */
	private static String sendWelcomeMessage() {

		String welcomeString = "";
		welcomeString += "Welcome to the strange world of being an engineering student!\n";
		welcomeString += "You will struggle to find the proper tools to defend yourself from the\n";
		welcomeString += "monster hiding, waiting for you to fall into their traps! Collect hidden items by\n";
		welcomeString += "exploring the building. Items will help you in your quest to defet the monsters.\n";
		welcomeString += "Start exploring!\n\n";

		return welcomeString;
	}  

	/**
	 * Runs the main game window.
	 * @param args arguments for main method.
	 * @throws IOException game play exception
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

		//player = SelectCharacterPrompt(player);

		Partition foo = new Partition(width, length, currentPartition, xDim, yDim, player, playerX, playerY);
		foo.updatePartition();

		JFrame mainFrame = new JFrame();
		mainFrame.setPreferredSize(new Dimension(1050, 700));

		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(750, 500));

		JPanel inventoryButtonPanel = new JPanel();
		inventoryButtonPanel.setPreferredSize(new Dimension(150, 500));

		JPanel textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(1000, 150));

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
		storyTextArea.setSize(new Dimension(1000, 150));
		storyTextArea.setFont(storyTextArea.getFont().deriveFont(16f));
		storyTextArea.setEditable(false);
		textPanel.add(storyTextArea);

		for (int i = 0; i < foo.player.inventory.inventorySize; i++) {
			final int itemPosition = i;
			JButton inventoryButton = new JButton("" + i);
			inventoryButton.setPreferredSize(new Dimension(50, 40));
			inventoryButton.setFont(new Font("Arial", Font.PLAIN, 12));
			inventoryButton.addActionListener(new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					foo.swapHoldingItem(itemPosition);
					String panelPrompt = "Current item: ";
					panelPrompt += foo.player.inventory.getItemName(foo.player.inventory.getCurrentItem());
					panelPrompt += "\nCurrent scaling: " + foo.player.scaling;
					storyTextArea.setText(null);
					storyTextArea.insert(panelPrompt, 0);
				}
			});
			inventoryButtonPanel.add(inventoryButton);
		}

		JButton upButton = new JButton("Up");
		upButton.setPreferredSize(new Dimension(150, 85));
		upButton.setFont(new Font("Arial", Font.PLAIN, 24));
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				try {
					foo.moveNorth(); 
					printMap(gamePanel, foo);
					if (foo.doesTileHaveItem()) {
						int itemID = foo.getItemID(foo.playerY, foo.playerX);
						if (foo.player.inventory.getNextEmptyInvntorySlot() == 0) {
							foo.player.inventory.addItemToInventory(itemID);
							foo.player.scaling = foo.player.inventory.getScaling();
						} else {
							foo.player.inventory.addItemToInventory(itemID);
						}

						String panelPrompt = "";
						if (itemID != 19) {
							panelPrompt = "Got item: " + foo.player.inventory.getItemName(itemID);
							panelPrompt += "\nGo to inventory to equip";
						} else {
							panelPrompt = "Got item: Pool floaty!";
							panelPrompt += "\nYou can now traverse water!";
						}
						storyTextArea.setText(null);
						storyTextArea.insert(panelPrompt, 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						foo.tiles[playerY][playerX].itemID = 0; 
					} else if (foo.doesTileHaveMonster()) {
						String monsterWarning = "Monster appeared! Battle it using the console!";
						JOptionPane.showMessageDialog(null, monsterWarning,
								"Hey!", JOptionPane.ERROR_MESSAGE);
						int monsterID = foo.getMonsterID();
						Battle battle = new Battle(foo.player, monsterID);
						int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
						foo.endBattle(xpGain);
						statTextArea.setText(null);
						statTextArea.insert(printPlayerStats(foo), 0);
						printMap(gamePanel, foo);
						SwingUtilities.updateComponentTreeUI(mainFrame);
					} else {
						statTextArea.setText(null);
						statTextArea.insert(printPlayerStats(foo), 0);
						String panelPrompt = "Current item: ";
						panelPrompt += foo.player.inventory.getItemName(foo.player.inventory.getCurrentItem());
						panelPrompt += "\nCurrent scaling: " + foo.player.scaling;
						storyTextArea.setText(null);
						storyTextArea.insert(panelPrompt, 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
					}
				} catch (final Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		JButton downButton = new JButton("Down");
		downButton.setPreferredSize(new Dimension(150, 85));
		downButton.setFont(new Font("Arial", Font.PLAIN, 24));
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				try {
					foo.moveSouth(); 
					printMap(gamePanel, foo);
					if (foo.doesTileHaveItem()) {
						int itemID = foo.getItemID(foo.playerY, foo.playerX);
						if (foo.player.inventory.getNextEmptyInvntorySlot() == 0) {
							foo.player.inventory.addItemToInventory(itemID);
							foo.player.scaling = foo.player.inventory.getScaling();
						} else {
							foo.player.inventory.addItemToInventory(itemID);
						}

						String panelPrompt = "";
						if (itemID != 19) {
							panelPrompt = "Got item: " + foo.player.inventory.getItemName(itemID);
							panelPrompt += "\nGo to inventory to equip";
						} else {
							panelPrompt = "Got item: Pool floaty!";
							panelPrompt += "\nYou can now traverse water!";
						}
						storyTextArea.setText(null);
						storyTextArea.insert(panelPrompt, 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						foo.tiles[playerY][playerX].itemID = 0; 
					} else if (foo.doesTileHaveMonster()) {
						String monsterWarning = "Monster appeared! Battle it using the console!";
						JOptionPane.showMessageDialog(null, monsterWarning,
								"Hey!", JOptionPane.ERROR_MESSAGE);
						int monsterID = foo.getMonsterID();
						Battle battle = new Battle(foo.player, monsterID);
						int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
						foo.endBattle(xpGain);
						statTextArea.setText(null);
						statTextArea.insert(printPlayerStats(foo), 0);
						printMap(gamePanel, foo);
						SwingUtilities.updateComponentTreeUI(mainFrame);
					} else {
						statTextArea.setText(null);
						statTextArea.insert(printPlayerStats(foo), 0);
						String panelPrompt = "Current item: ";
						panelPrompt += foo.player.inventory.getItemName(foo.player.inventory.getCurrentItem());
						panelPrompt += "\nCurrent scaling: " + foo.player.scaling;
						storyTextArea.setText(null);
						storyTextArea.insert(panelPrompt, 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
					}	
				} catch (final Exception ex) {
					ex.printStackTrace();
				}		 
			}
		});

		JButton leftButton = new JButton("Left");
		leftButton.setPreferredSize(new Dimension(150, 85));
		leftButton.setFont(new Font("Arial", Font.PLAIN, 24));
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				try {
					foo.moveWest(); 
					printMap(gamePanel, foo);
					if (foo.doesTileHaveItem()) {
						int itemID = foo.getItemID(foo.playerY, foo.playerX);
						if (foo.player.inventory.getNextEmptyInvntorySlot() == 0) {
							foo.player.inventory.addItemToInventory(itemID);
							foo.player.scaling = foo.player.inventory.getScaling();
						} else {
							foo.player.inventory.addItemToInventory(itemID);
						}
						String panelPrompt = "";
						if (itemID != 19) {
							panelPrompt = "Got item: " + foo.player.inventory.getItemName(itemID);
							panelPrompt += "\nGo to inventory to equip";
						} else {
							panelPrompt = "Got item: Pool floaty!";
							panelPrompt += "\nYou can now traverse water!";
						}
						storyTextArea.setText(null);
						storyTextArea.insert(panelPrompt, 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						// remove item from tile
						foo.tiles[playerY][playerX].itemID = 0; 
					} else if (foo.doesTileHaveMonster()) {
						String monsterWarning = "Monster appeared! Battle it using the console!";
						JOptionPane.showMessageDialog(null, monsterWarning,
								"Hey!", JOptionPane.ERROR_MESSAGE);
						int monsterID = foo.getMonsterID();
						Battle battle = new Battle(foo.player, monsterID);
						int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
						foo.endBattle(xpGain);
						statTextArea.setText(null);
						statTextArea.insert(printPlayerStats(foo), 0);
						printMap(gamePanel, foo);
						SwingUtilities.updateComponentTreeUI(mainFrame);
					} else {
						statTextArea.setText(null);
						statTextArea.insert(printPlayerStats(foo), 0);
						String panelPrompt = "Current item: ";
						panelPrompt += foo.player.inventory.getItemName(foo.player.inventory.getCurrentItem());
						panelPrompt += "\nCurrent scaling: " + foo.player.scaling;
						storyTextArea.setText(null);
						storyTextArea.insert(panelPrompt, 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}		 
			}
		});

		JButton rightButton = new JButton("Right");
		rightButton.setPreferredSize(new Dimension(150, 85));
		rightButton.setFont(new Font("Arial", Font.PLAIN, 24));
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				try {
					foo.moveEast(); 
					printMap(gamePanel, foo);
					if (foo.doesTileHaveItem()) {
						int itemID = foo.getItemID(foo.playerY, foo.playerX);
						if (foo.player.inventory.getNextEmptyInvntorySlot() == 0) {
							foo.player.inventory.addItemToInventory(itemID);
							foo.player.scaling = foo.player.inventory.getScaling();
						} else {
							foo.player.inventory.addItemToInventory(itemID);
						}
						String panelPrompt = "";
						if (itemID != 19) {
							panelPrompt = "Got item: " + foo.player.inventory.getItemName(itemID);
							panelPrompt += "\nGo to inventory to equip";
						} else {
							panelPrompt = "Got item: Pool floaty!";
							panelPrompt += "\nYou can now traverse water!";
						}
						storyTextArea.setText(null);
						storyTextArea.insert(panelPrompt, 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
						// remove item from tile
						foo.tiles[playerY][playerX].itemID = 0; 
					} else if (foo.doesTileHaveMonster()) {
						String monsterWarning = "Monster appeared! Battle it using the console!";
						JOptionPane.showMessageDialog(null, monsterWarning,
								"Hey!", JOptionPane.ERROR_MESSAGE);
						int monsterID = foo.getMonsterID();
						Battle battle = new Battle(foo.player, monsterID);
						int xpGain = battle.startBattle(); // returns 0 if loss, xp bonus if win, -1 if flee
						foo.endBattle(xpGain);
						statTextArea.setText(null);
						statTextArea.insert(printPlayerStats(foo), 0);
						printMap(gamePanel, foo);
						SwingUtilities.updateComponentTreeUI(mainFrame);
					} else {
						statTextArea.setText(null);
						statTextArea.insert(printPlayerStats(foo), 0);
						String panelPrompt = "Current item: ";
						panelPrompt += foo.player.inventory.getItemName(foo.player.inventory.getCurrentItem());
						panelPrompt += "\nCurrent scaling: " + foo.player.scaling;
						storyTextArea.setText(null);
						storyTextArea.insert(panelPrompt, 0);
						SwingUtilities.updateComponentTreeUI(mainFrame);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.setPreferredSize(new Dimension(150, 85));
		inventoryButton.setFont(new Font("Arial", Font.PLAIN, 24));
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				foo.manageInventory(storyTextArea, foo.player);
			}
		});

		buttonPanel.add(upButton);
		buttonPanel.add(downButton);
		buttonPanel.add(leftButton);
		buttonPanel.add(rightButton);
		buttonPanel.add(inventoryButton);

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
		printMap(gamePanel, foo);
		storyTextArea.setText(sendWelcomeMessage());
		statTextArea.insert(printPlayerStats(foo), 0);
		SwingUtilities.updateComponentTreeUI(mainFrame);
	}

}
