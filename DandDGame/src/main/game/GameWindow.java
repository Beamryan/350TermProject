package main.game;

import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JFrame;

public final class GameWindow {

	/**
	 * 
	 */
	private GameWindow() {

	}

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

		Partition foo = new Partition(width, length, currentPartition, xDim, yDim, player, playerX, playerY);
		foo.welcomeMessage();

		JFrame mainFrame = new JFrame();
		mainFrame.setPreferredSize(new Dimension(1250, 700));

		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(1000, 500));

		JPanel textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(1000, 200));

		JPanel statPanel = new JPanel();
		statPanel.setPreferredSize(new Dimension(125, 500));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(125, 500));

		JTextArea textArea = new JTextArea();
		textArea.setSize(new Dimension(1500, 300));
		textArea.setFont(textArea.getFont().deriveFont(14f));
		textPanel.add(textArea);

		JButton upButton = new JButton("Up");
		upButton.setPreferredSize(new Dimension(250, 40));
		upButton.setFont(new Font("Arial", Font.PLAIN, 30));
		upButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					foo.moveNorth(); 
					foo.doesTileHaveItem();
					foo.doesTileHaveMonster();

					foo.printPartition();
					System.out.println("Level " + foo.player.level);
					System.out.println("Xp " + foo.player.xp);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		JButton downButton = new JButton("Down");
		downButton.setPreferredSize(new Dimension(250, 40));
		downButton.setFont(new Font("Arial", Font.PLAIN, 30));
		downButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					foo.moveSouth(); 
					foo.doesTileHaveItem();
					foo.doesTileHaveMonster();

					foo.printPartition();
					System.out.println("Level " + foo.player.level);
					System.out.println("Xp " + foo.player.xp);
				} catch(Exception ex) {
					ex.printStackTrace();
				}		 
			}
		});

		JButton leftButton = new JButton("Left");
		leftButton.setPreferredSize(new Dimension(250, 40));
		leftButton.setFont(new Font("Arial", Font.PLAIN, 30));
		leftButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					foo.moveEast(); 
					foo.doesTileHaveItem();
					foo.doesTileHaveMonster();

					foo.printPartition();
					System.out.println("Level " + foo.player.level);
					System.out.println("Xp " + foo.player.xp);
				} catch(Exception ex) {
					ex.printStackTrace();
				}		 
			}
		});

		JButton rightButton = new JButton("Right");
		rightButton.setPreferredSize(new Dimension(250, 40));
		rightButton.setFont(new Font("Arial", Font.PLAIN, 30));
		rightButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					foo.moveWest(); 
					foo.doesTileHaveItem();
					foo.doesTileHaveMonster();

					foo.printPartition();
					System.out.println("Level " + foo.player.level);
					System.out.println("Xp " + foo.player.xp);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.setPreferredSize(new Dimension(250, 40));
		inventoryButton.setFont(new Font("Arial", Font.PLAIN, 30));
		inventoryButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Call the move inventory method
			}
		});

		JButton attackButton = new JButton("Attack");
		attackButton.setPreferredSize(new Dimension(250, 40));
		attackButton.setFont(new Font("Arial", Font.PLAIN, 30));
		attackButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Call the move attack method
			}
		});

		JButton shieldButton = new JButton("Shield");
		shieldButton.setPreferredSize(new Dimension(250, 40));
		shieldButton.setFont(new Font("Arial", Font.PLAIN, 30));
		shieldButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Call the move shield method
			}
		});

		JButton restButton = new JButton("Rest");
		restButton.setPreferredSize(new Dimension(250, 40));
		restButton.setFont(new Font("Arial", Font.PLAIN, 30));
		restButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Call the move run method
			}
		});

		JButton fleeButton = new JButton("Flee");
		fleeButton.setPreferredSize(new Dimension(250, 40));
		fleeButton.setFont(new Font("Arial", Font.PLAIN, 30));
		fleeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Call the move flee method
			}
		});

		buttonPanel.add(upButton);
		buttonPanel.add(downButton);
		buttonPanel.add(leftButton);
		buttonPanel.add(rightButton);
		buttonPanel.add(inventoryButton);
		buttonPanel.add(attackButton);
		buttonPanel.add(shieldButton);
		buttonPanel.add(restButton);
		buttonPanel.add(fleeButton);

		buttonPanel.setBackground(Color.GRAY);
		textPanel.setBackground(Color.WHITE);
		statPanel.setBackground(Color.GRAY);
		gamePanel.setBackground(Color.BLACK);

		mainFrame.add(gamePanel, BorderLayout.CENTER);
		mainFrame.add(textPanel, BorderLayout.PAGE_END);
		mainFrame.add(statPanel, BorderLayout.LINE_START);
		mainFrame.add(buttonPanel, BorderLayout.LINE_END);	  

		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setVisible(true);	  
	}

}
