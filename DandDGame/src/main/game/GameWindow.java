package main.game;

import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
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
		
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(2000, 1000);

		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(1500, 800));
		
		JPanel textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(1500, 200));
		  
		JPanel statPanel = new JPanel();
		statPanel.setPreferredSize(new Dimension(250, 1000));
		  
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(250, 1000));
		
		// Add action listeners
		JButton upButton = new JButton("Up");
		upButton.setPreferredSize(new Dimension(250, 100));
		upButton.setFont(new Font("Arial", Font.PLAIN, 40));
		
		JButton downButton = new JButton("Down");
		downButton.setPreferredSize(new Dimension(250, 100));
		downButton.setFont(new Font("Arial", Font.PLAIN, 40));
		
		JButton leftButton = new JButton("Left");
		leftButton.setPreferredSize(new Dimension(250, 100));
		leftButton.setFont(new Font("Arial", Font.PLAIN, 40));
		
		JButton rightButton = new JButton("Right");
		rightButton.setPreferredSize(new Dimension(250, 100));
		rightButton.setFont(new Font("Arial", Font.PLAIN, 40));
		
		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.setPreferredSize(new Dimension(250, 100));
		inventoryButton.setFont(new Font("Arial", Font.PLAIN, 40));
		
		JButton attackButton = new JButton("Attack");
		attackButton.setPreferredSize(new Dimension(250, 100));
		attackButton.setFont(new Font("Arial", Font.PLAIN, 40));
		
		JButton shieldButton = new JButton("Shield");
		shieldButton.setPreferredSize(new Dimension(250, 100));
		shieldButton.setFont(new Font("Arial", Font.PLAIN, 40));
		
		JButton runButton = new JButton("Run");
		runButton.setPreferredSize(new Dimension(250, 100));
		runButton.setFont(new Font("Arial", Font.PLAIN, 40));
		
		buttonPanel.add(upButton);
		buttonPanel.add(downButton);
		buttonPanel.add(leftButton);
		buttonPanel.add(rightButton);
		buttonPanel.add(inventoryButton);
		buttonPanel.add(attackButton);
		buttonPanel.add(shieldButton);
		buttonPanel.add(runButton);
		
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		mainFrame.add(textPanel, BorderLayout.PAGE_END);
		mainFrame.add(statPanel, BorderLayout.WEST);
		mainFrame.add(buttonPanel, BorderLayout.EAST);	  
		
		mainFrame.pack();
		mainFrame.setVisible(true);
		  
		Partition.main(null);
	}

}
